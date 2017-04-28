/**
 * 文件名： ErrorCode.java 此类描述的是： 作者: leiliang 创建时间: 2016年3月23日 上午10:15:33
 */
package com.mjoys.advert.common.constants;

/**
 * 此类描述的是： 推啊core工程内部自定义错误码规范<br>
 * 错误码组成：ABCCC
 * <ul>
 * A：模块编号
 * <li>0：公用模块</li>
 * <li>1：资质审核模块</li>
 * </ul>
 * <ul>
 * BB：错误类型
 * <li>1 - 媒体级错误（参数错误）</li>
 * <li>2 - 业务级错误（service自身错误）</li>
 * <li>3 - 依赖级错误（service调用第三方服务错误）</li>
 * <li>4 - 交互级业务提醒（正常业务逻辑，非错误，需告知用户，如库存不足）</li>
 * </ul>
 * <ul>
 * CCC:具体错误码 举例
 * <li>通用的成功状态码：000</li>
 * <li>通用的未知错误码：999</li>
 * </ul>
 * <ul>
 * 业务前缀
 * <li>tuia-core：TC_</li>
 * </ul>
 *
 * @规范： http://cf.dui88.com/pages/viewpage.action?pageId=3544570
 * @汇总文档：http://cf.dui88.com/pages/viewpage.action?pageId=3560274
 */
public enum ErrorCode {

    // 公共模块00

    E00000("0", "成功"),

    E99999("99999", "未知错误"),

    /* 数据库错误. */
    E02001("02001", "数据库错误"),

    /* 参数错误. */
    E01002("01002", "参数错误"),

    /* IO异常 */
    E03003("03003", "IO异常"),

    /* 编码失败 */
    E02004("02004", "编码失败"),


    // 资质审核模块

    /* 广告主当前处于审核中或者已经审核通过 */
    E14001("14001", "广告主当前处于审核中或者已经审核通过"),

    /* 获取小米token失败 */
    E13002("13002", "获取小米token失败"),

    /* 广告主不存在 */
    E14003("14003", "广告主不存在"),

    /*  广告主资质信息不存在 */
    E14004("14004", "广告主资质信息不存在"),

    /*  提交广告主资质失败 */
    E12005("12005", "提交广告主资质失败"),

    /*  查询广告主审核状态失败 */
    E13006("13006", "查询广告主审核状态失败"),

    /*  推送创意审核失败 */
    E12006("12006", "推送创意审核失败"),
    ;

    /**
     * 错误码.
     */
    private String errorCode;

    /**
     * 中文描述.
     */
    private String desc;

    /**
     * The Constructor.
     *
     * @param errorCode the error code
     * @param desc      the desc
     */
    private ErrorCode(String errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
}
