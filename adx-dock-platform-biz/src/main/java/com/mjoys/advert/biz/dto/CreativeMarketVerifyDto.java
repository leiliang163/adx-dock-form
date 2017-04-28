package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 11:01.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class CreativeMarketVerifyDto extends BaseDto {

    private static final long serialVersionUID        = 1468732344070278793L;

    /* 待审核 */
    public static final int   VERIFY_OF_PEDING_AUDIT  = 0;

    /* 推送中 */
    public static final int   VERIFY_OF_PUSH_IN       = 1;

    /* 审核通过 */
    public static final int   VERIFY_OF_PASS          = 2;

    /* 已驳回 */
    public static final int   VERIFY_OF_REFUSE        = 3;

    /* 已推送 */
    public static final int   VERIFY_OF_PUSH_ALREADY  = 4;

    /* 推送失败 */
    public static final int   VERIFY_OF_PUSH_FAILED   = 5;

    /* 人工驳回 */
    public static final int   VERIFY_OF_PERSON_REJECT = 6;

    /* 已删除 */
    public static final int   VERIFY_OF_DELETED       = -1;

    /* 流量市场端创意ID */
    private String            marketCreativeId;

    /* dsp端创意ID */
    private Long              creativeId;

    /* 审核ID */
    private Long              verifyId;

    /* 市场类型 */
    private int               market;

    /* 审核状态: 0：待审核；1：推送中; 2：审核通过; 3：已驳回; 4 已推送; 5 推送失败; 6 人工驳回; -1 已删除 */
    private int               verifyStatus;

    /* 拒登原因 */
    private String            refuseReason;

    /* 谷歌人工审核状态 0待审核， 1已通过 2拒登 */
    private int               goodleStatus;

    /* 谷歌过滤原因 */
    private String            gooleRefuseReason;

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public int getGoodleStatus() {
        return goodleStatus;
    }

    public void setGoodleStatus(int goodleStatus) {
        this.goodleStatus = goodleStatus;
    }

    public String getGooleRefuseReason() {
        return gooleRefuseReason;
    }

    public void setGooleRefuseReason(String gooleRefuseReason) {
        this.gooleRefuseReason = gooleRefuseReason;
    }

    public String getMarketCreativeId() {
        return marketCreativeId;
    }

    public void setMarketCreativeId(String marketCreativeId) {
        this.marketCreativeId = marketCreativeId;
    }

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }
}
