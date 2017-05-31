package com.mjoys.advert.common.constants;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:01.<br/>
 * 功能描述 : 外部接口url枚举类.<br/>
 * 变更记录 : .<br/>
 */
public enum ThirdUrlEnums {

    /**
     * 小米权限认证接口
     */
    XIAOMI_AUTH_TOKEN("/v1/token/get"),
    /**
     * 小米广告主资质审核接口
     */
    XIAOMI_ADD_ADVERTISER("/audit/v1/advertiser/add"),
    /**
     * 小米广告主资质审核接口
     */
    XIAOMI_UPDATE_ADVERTISER("/audit/v1/advertiser/update"),
    /**
     * 小米查询广告主资质审核状态接口
     */
    XIAOMI_QUERY_ADVERTISER_QI("/audit/v1/advertiser/query"),
    /**
     * 小米创意审核接口
     */
    XIAOMI_ADD_CREATIVE("/audit/v1/creative/add"),
    /**
     * 小米查询创意审核状态接口
     */
    XIAOMI_QUERY_CREATIVE_QI("/audit/v1/creative/query"),;

    private String thirdUlr;


    private ThirdUrlEnums(String thirdUlr) {
        this.thirdUlr = thirdUlr;
    }

    public String getThirdUlr() {
        return thirdUlr;
    }
}
