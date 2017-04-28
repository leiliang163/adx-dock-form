package com.mjoys.advert.biz.bo;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 10:01.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public interface IAdvertiserQIBo {

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
}
