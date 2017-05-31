package com.mjoys.advert.biz.dto;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:11.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class QualAttachmentDto extends BaseDto {

    /**
     * 广告主ID
     */
    private Long   advId;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件URL
     */
    private String attachmentUrl;

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

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }
}
