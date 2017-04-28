package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:11.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class QualAttachmentDto {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 广告主ID
     */
    private Long advId;

    /**
     * 附件名称
     */
    private String attachmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
