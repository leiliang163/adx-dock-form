package com.mjoys.advert.biz.remoteservice;

import com.mjoys.advert.api.remoteservice.RemoteQIBackendService;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiAdvertiser;
import com.mjoys.advert.biz.service.IAdvertiserService;
import com.mjoys.advert.biz.third.IXiaoMiQIService;
import com.mjoys.advert.biz.utils.cat.CatUtils;
import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import com.mjoys.common.wolf.model.DubboResult;
import cn.oasistech.pbinfo.Enums;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 16:44.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class RemoteQIBackendServiceImpl extends RemoteBaseService implements RemoteQIBackendService {

    @Autowired
    private IAdvertiserService advertiserQIService;

    @Autowired
    private IXiaoMiQIService xiaoMiQIService;

    @Override
    public DubboResult<Boolean> addAdvertiserForXiaoMi(Long advId) {
        CatUtils.log("addAdvertiserForXiaoMi");
        try {
            logger.info("addAdvertiserForXiaoMi begin, advId=[{}]", advId);

            // 1. 判断广告主是否上传过
            if (advertiserQIService.isSuccessOrAuditOfstatus(advId, Enums.Market.XIAOMI_VALUE)) {
                // 如果处于审核中或者审核通过， 不允许再次上传
                throw new InnerException(ErrorCode.E14001);
            }

            // 2. 构建上传所需信息
            XiaoMiAdvertiser xiaoMiAdvertiser = advertiserQIService.getAdvertiserDetailForXiaoMi(advId);

            // 3. 上传
            QualVerifyDto qualVerifyDto = xiaoMiQIService.addAdvertiser(xiaoMiAdvertiser);
            if (QualVerifyDto.STATUS_OF_PUSH_FAILED == qualVerifyDto.getStatus()) {
                if (StringUtils.isNotBlank(qualVerifyDto.getReason())) {
                    throw new InnerException(ErrorCode.E12005.getErrorCode(), qualVerifyDto.getReason());
                }

                throw new InnerException(ErrorCode.E12005);
            }
            // 4. 新增一条上传记录
            qualVerifyDto.setAdvId(advId);
            qualVerifyDto.setMarket(Enums.Market.XIAOMI_VALUE);

            advertiserQIService.insertQIRecord(qualVerifyDto);

            return DubboResult.successResult(true);
        } catch (Exception e) {
            return exceptionFailure(e);
        }
    }
}
