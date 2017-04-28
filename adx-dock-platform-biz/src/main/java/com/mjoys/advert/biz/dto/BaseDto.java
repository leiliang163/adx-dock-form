package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by leiliang on 2017/3/30.
 */
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 1468732344070278793L;

    /** The id. */
    protected Long            id;

    /** 创建时间. */
    protected Date            timeCreate;

    /** 最新更新时间. */
    protected Date            timeModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
