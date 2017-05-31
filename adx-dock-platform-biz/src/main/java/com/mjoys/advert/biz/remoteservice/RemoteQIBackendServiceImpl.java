package com.mjoys.advert.biz.remoteservice;

import com.mjoys.advert.api.remoteservice.RemoteQIBackendService;
import com.mjoys.advert.biz.bo.IAdvertiserQIBo;
import com.mjoys.common.wolf.cat.CatUtils;
import com.mjoys.common.wolf.model.DubboResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 16:44.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class RemoteQIBackendServiceImpl extends RemoteBaseService implements RemoteQIBackendService {

    @Autowired
    private IAdvertiserQIBo advertiserQIBo;

    @Override
    public DubboResult<Boolean> addAdvertiserForXiaoMi(Long advId) {
        try {
            advertiserQIBo.addAdvertiserForXiaoMi(advId);
            return DubboResult.successResult(true);
        } catch (Exception e) {
            CatUtils.log("addAdvertiserForXiaoMi");
            return exceptionFailure(e);
        }
    }

    @Override
    public DubboResult<Boolean> updateAdvertiserForXiaomi(Long advId, String marketAdvId) {
        try {
            advertiserQIBo.updateAdvertiserForXiaomi(advId, marketAdvId);
            return DubboResult.successResult(true);
        } catch (Exception e) {
            CatUtils.log("updateAdvertiserForXiaomi");
            return exceptionFailure(e);
        }
    }
}
