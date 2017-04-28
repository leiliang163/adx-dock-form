package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:13.<br/>
 * 功能描述 : 创意审核表.<br/>
 * 变更记录 : .<br/>
 */
public class TeamCreativeVerifyDto {

    /* 创意审核ID */
    private Long   id;

    /* 广告主ID */
    private Long   advId;

    /* 系列ID */
    private Long   tId;

    /* 系列ID */
    private Long   sId;

    /* 创意模板ID */
    private Long   templateId;

    /* 创意ID */
    private Long   creativeId;

    /* 流量市场的模板ID */
    private String matchTemplateId;

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

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }

    public String getMatchTemplateId() {
        return matchTemplateId;
    }

    public void setMatchTemplateId(String matchTemplateId) {
        this.matchTemplateId = matchTemplateId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
