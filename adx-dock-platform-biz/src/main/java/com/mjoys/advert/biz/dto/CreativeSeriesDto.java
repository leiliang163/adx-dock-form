package com.mjoys.advert.biz.dto;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/17 15:31.<br/>
 * 功能描述 : 创意系列.<br/>
 * 变更记录 : .<br/>
 */
public class CreativeSeriesDto extends BaseDto {

    /**
     * 原生创意.
     */
    public static final int STATUS_OF_NATIVE = 3;
    /**
     * 系列名称.
     */
    private String          name;
    /**
     * 分类.
     */
    private String          gategory;
    /**
     * 状态（-1删除 0停用 1启用）.
     */
    private int             status;
    /**
     * 适合的流量类型,逗号分隔 pc,app,wap.
     */
    private String          fitFlowType;
    /**
     * 适用平台（1天猫 2淘宝 3其他），逗号分隔.
     */
    private String          fitPlatform;
    /**
     * 系列类型（1 静态系列 2 动态系列 3 原生系列）.
     */
    private int             type;
    /**
     * 是否过滤商品图片尺寸.
     */
    private int             isFilterGoodsStandaro;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGategory() {
        return gategory;
    }

    public void setGategory(String gategory) {
        this.gategory = gategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFitFlowType() {
        return fitFlowType;
    }

    public void setFitFlowType(String fitFlowType) {
        this.fitFlowType = fitFlowType;
    }

    public String getFitPlatform() {
        return fitPlatform;
    }

    public void setFitPlatform(String fitPlatform) {
        this.fitPlatform = fitPlatform;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsFilterGoodsStandaro() {
        return isFilterGoodsStandaro;
    }

    public void setIsFilterGoodsStandaro(int isFilterGoodsStandaro) {
        this.isFilterGoodsStandaro = isFilterGoodsStandaro;
    }
}
