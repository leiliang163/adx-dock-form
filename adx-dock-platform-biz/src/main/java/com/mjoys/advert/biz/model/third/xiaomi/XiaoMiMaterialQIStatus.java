package com.mjoys.advert.biz.model.third.xiaomi;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:41.<br/>
 * 功能描述 : 小米素材审核状态.<br/>
 * 变更记录 : .<br/>
 */
public class XiaoMiMaterialQIStatus {
    /**
     * 小米侧创意 ID
     */
    private String materialId;

    /**
     * 侧创意 ID
     */
    private Long creativeId;

    /**
     * 模板 ID
     */
    private String templateId;

    /**
     * 审核状态码
     */
    private int status;

    /**
     * 审核不通过原因
     */
    private String rejectReason;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
