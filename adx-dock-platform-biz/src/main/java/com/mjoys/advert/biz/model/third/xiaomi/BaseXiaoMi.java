package com.mjoys.advert.biz.model.third.xiaomi;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:35.<br/>
 * 功能描述 : 小米公共响应结果.<br/>
 * 变更记录 : .<br/>
 */
public class BaseXiaoMi {

    /* 全部成功 */
    public static final String SUCCESS = "0";

    /* 部分成功 */
    public static final String PARTIAL_SUCCESS = "1";

    /* 批量推送创意审核：最多5个 */
    public static final int MAX_OF_ADD_CREATIVE_COUNT = 5;

    /* 批量查询创意审核：最多20个 */
    public static final int MAX_OF_QUERY_CREATIVE_COUNT = 20;

    /* 返回码 */
    private String code;

    /* 失败描述 */
    private String msg;

    /* 失败描述 */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
