package com.mjoys.advert.biz.bo.impl;

import cn.oasistech.pbinfo.Enums;
import com.mjoys.advert.biz.bo.IAdvertiserQIBo;
import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.model.third.xiaomi.*;
import com.mjoys.advert.biz.service.IAdvertiserService;
import com.mjoys.advert.biz.service.ICreativeService;
import com.mjoys.advert.biz.service.impl.BaseService;
import com.mjoys.advert.biz.third.IXiaoMiQIService;
import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import com.mjoys.common.wolf.cat.CatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 10:02.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Service("advertiserQIBo")
public class AdvertiserQIBoImpl extends BaseService implements IAdvertiserQIBo {

    @Autowired
    private IXiaoMiQIService   xiaoMiQIService;

    @Autowired
    private ICreativeService   creativeService;

    @Autowired
    private IAdvertiserService advertiserService;

    @Override
    public void addAdvertiserForXiaoMi(Long advId) {

        // 1. 判断广告主是否上传过
        if (advertiserService.isSuccessOrAuditOfStatus(advId, Enums.Market.XIAOMI_VALUE)) {
            // 如果处于审核中或者审核通过， 不允许再次上传
            throw new InnerException(ErrorCode.E14001);
        }

        // 2. 构建上传所需信息
        XiaoMiAdvertiser xiaoMiAdvertiser = advertiserService.getAdvertiserDetailForXiaoMi(advId);
        logger.info("addAdvertiser begin, the XiaoMiAdvertiser=[{}]", xiaoMiAdvertiser);

        // 3. 上传
        QualVerifyDto qualVerifyDto = xiaoMiQIService.addAdvertiser(xiaoMiAdvertiser);
        if (QualVerifyDto.STATUS_OF_PUSH_FAILED == qualVerifyDto.getStatus()) {
            if (StringUtils.isNotBlank(qualVerifyDto.getReason())) {
                throw new InnerException(ErrorCode.E12005.getErrorCode(),
                                         qualVerifyDto.getReason());
            }

            throw new InnerException(ErrorCode.E12005);
        }
        // 4. 新增一条上传记录
        qualVerifyDto.setAdvId(advId);
        qualVerifyDto.setMarket(Enums.Market.XIAOMI_VALUE);

        advertiserService.insertQIRecord(qualVerifyDto);

    }

    @Override
    public void updateAdvertiserQIStatus() {

        // 1.去审核记录表中查询处于审核中的记录
        List<String> marketAdvIds = advertiserService.selectMarketAdvIds(QualVerifyDto.STATUS_OF_AUDIT,
                                                                         Enums.Market.XIAOMI_VALUE);
        logger.info("待审核的广告主列表={}", marketAdvIds);

        if (CollectionUtils.isEmpty(marketAdvIds)) {
            return;
        }

        // 2.去流量市场查询当前的审核状态
        List<XiaoMiAdvertiserQIStatus> adQIStatuses = xiaoMiQIService.queryAdvertiserQIStatus(marketAdvIds);

        // 3.构造推送成功和推送失败的广告主记录
        List<String> passAdvIds = new ArrayList<>(adQIStatuses.size());
        List<Map<String, Object>> refuseAdvs = new ArrayList<>(adQIStatuses.size());

        for (XiaoMiAdvertiserQIStatus adQIStatuse : adQIStatuses) {
            int status = adQIStatuse.getStatus();

            if (QualVerifyDto.STATUS_OF_PASS == status) {
                passAdvIds.add(adQIStatuse.getMarketAdvId());

            } else if (QualVerifyDto.STATUS_OF_REFUSE == status) {

                Map<String, Object> refuseAdv = new HashMap<>();
                refuseAdv.put("marketAdvId", adQIStatuse.getMarketAdvId());
                refuseAdv.put("reason", adQIStatuse.getRejectReason());

                refuseAdvs.add(refuseAdv);
            }
        }

        // 4.把流量市场的最新审核状态同步到我们的审核记录表中
        logger.info("审核通过的广告主列表={}", passAdvIds);
        advertiserService.updatePassList(passAdvIds);
        logger.info("审核拒绝的广告主列表={}", refuseAdvs);
        advertiserService.updateRejectList(refuseAdvs);
    }

    @Override
    public void addCreative() {

        // 1. 查询带推送的创意列表(verify状态为：推送中)
        List<XiaoMiMaterialDetail> xiaoMiMaterials = creativeService.getMaterialsForXiaoMi();
        logger.info("待推送的创意列表={}", xiaoMiMaterials);

        if (CollectionUtils.isEmpty(xiaoMiMaterials)) {
            return;
        }

        // 2. 创意向小米adx提交,并且更新推送状态
        creativeService.updateVerifyStatus(xiaoMiQIService.addMaterial(xiaoMiMaterials));
    }

    @Override
    public void updateCreativeQIStatus() {

        // 1. 查询待审核的创意
        List<String> adxCreativeIds = creativeService.getAdxCreativeIds(Enums.Market.XIAOMI_VALUE,
                                                                        CreativeMarketVerifyDto.VERIFY_OF_PUSH_FAILED);
        logger.info("待审核的创意Id列表={}", adxCreativeIds);

        creativeService.updateVerifyStatus(xiaoMiQIService.queryMaterialQIStatus(adxCreativeIds));
    }

    @Override
    public void updateAdvertiserForXiaomi(Long advId, String marketAdvId) {

        // 2. 构建上传所需信息
        XiaoMiAdvertiser xiaoMiAdvertiser = advertiserService.getAdvertiserDetailForXiaoMi(advId);
        xiaoMiAdvertiser.setAdvId(marketAdvId);

        // 3. 上传
        logger.info("updateAdvertiser begin, the XiaoMiAdvertiser=[{}]", xiaoMiAdvertiser);

        QualVerifyDto qualVerifyDto = xiaoMiQIService.updateAdvertiser(xiaoMiAdvertiser);
        if (QualVerifyDto.STATUS_OF_PUSH_FAILED == qualVerifyDto.getStatus()) {
            if (StringUtils.isNotBlank(qualVerifyDto.getReason())) {
                throw new InnerException(ErrorCode.E12005.getErrorCode(),
                                         qualVerifyDto.getReason());
            }

            throw new InnerException(ErrorCode.E12005);
        }
        // 4. 修改一条上传记录
        qualVerifyDto.setAdvId(advId);
        qualVerifyDto.setMarket(Enums.Market.XIAOMI_VALUE);

        advertiserService.updateByMarketId(marketAdvId, qualVerifyDto.getStatus(),
                                           qualVerifyDto.getReason());

    }
}
