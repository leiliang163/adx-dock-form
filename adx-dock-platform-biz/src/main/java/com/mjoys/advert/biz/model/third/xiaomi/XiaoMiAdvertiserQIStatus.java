package com.mjoys.advert.biz.model.third.xiaomi;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:20.<br/>
 * 功能描述 : 广告主资质审核状态.<br/>
 * 变更记录 : .<br/>
 */
public class XiaoMiAdvertiserQIStatus {

    /**
     * 流量市场端广告主ID
     */
    private String marketAdvId;

    /**
     * 审核状态
     */
    private int status;

    /**
     * 驳回原因
     */
    private String rejectReason;

    public XiaoMiAdvertiserQIStatus(String marketAdvId, int status, String rejectReason) {
        this.marketAdvId = marketAdvId;
        this.status = status;
        this.rejectReason = rejectReason;
    }

    public XiaoMiAdvertiserQIStatus() {
    }

    public String getMarketAdvId() {
        return marketAdvId;
    }

    public void setMarketAdvId(String marketAdvId) {
        this.marketAdvId = marketAdvId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
