package com.mjoys.advert.api.remoteservice;

import com.mjoys.common.wolf.model.DubboResult;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 12:05.<br/>
 * 功能描述 : 资质审核服务.<br/>
 * 变更记录 : .<br/>
 */
public interface RemoteQIBackendService {

    /**
     * 广告主资质审核接口
     *
     * @param advId 广告主ID
     * @return dubbo result
     */
    DubboResult<Boolean> addAdvertiserForXiaoMi(Long advId);

    /**
     * 广告主资质审核接口
     *
     * @param marketAdvId 流量市场侧的广告主ID
     * @return dubbo result
     */
    DubboResult<Boolean> updateAdvertiserForXiaomi(Long advId, String marketAdvId);

}
