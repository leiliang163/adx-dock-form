package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:36.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class TeamCNTemFieldDto {

    /* 创意ID*/
    private Long creativeId;

    /* 创意字段名称*/
    private String fieldName;

    /* 创意字段值*/
    private String fieldValue;

    public Long getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Long creativeId) {
        this.creativeId = creativeId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
