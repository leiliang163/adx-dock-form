package com.mjoys.advert.biz.bo;

import com.mjoys.common.wolf.model.DubboResult;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 10:01.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public interface IAdvertiserQIBo {

    /**
     * 向小米推送广告主.
     *
     * @param advId the adv id
     */
    void addAdvertiserForXiaoMi(Long advId);

    /**
     * 更新广告主资质审核状态
     */
    void updateAdvertiserQIStatus();

    /**
     * 新增创意
     */
    void addCreative();

    /**
     * 更新创意审核状态
     */
    void updateCreativeQIStatus();

    /**
     * 广告主资质审核接口
     *
     * @param advId the adv id
     * @param marketAdvId 流量市场侧的广告主ID
     * @return dubbo result
     */
    void updateAdvertiserForXiaomi(Long advId, String marketAdvId);
}
