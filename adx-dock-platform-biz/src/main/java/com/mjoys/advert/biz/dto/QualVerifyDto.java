package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 9:35.<br/>
 * 功能描述 : 广告主审核记录.<br/>
 * 变更记录 : .<br/>
 */
public class QualVerifyDto {

    /* 审核中*/
    public static final int STATUS_OF_AUDIT = 1;

    /* 推送失败中*/
    public static final int STATUS_OF_PUSH_FAILED = 2;

    /* 审核拒绝*/
    public static final int STATUS_OF_REFUSE = 3;

    /* 审核通过*/
    public static final int STATUS_OF_PASS = 4;

    /**
     * 我们平台侧的广告主ID
     */
    private Long advId;

    /**
     * 流量市场侧的广告主ID
     */
    private String marketAdvId;

    /**
     * 流量市场标记
     */
    private int market;

    /**
     * 审核状态
     */
    private int status;

    /**
     * 审核拒绝原因
     */
    private String reason;

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getMarketAdvId() {
        return marketAdvId;
    }

    public void setMarketAdvId(String marketAdvId) {
        this.marketAdvId = marketAdvId;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
