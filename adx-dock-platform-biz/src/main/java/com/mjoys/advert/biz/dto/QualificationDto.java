package com.mjoys.advert.biz.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 16:56.<br/>
 * 功能描述 : 资质审核表.<br/>
 * 变更记录 : .<br/>
 */
public class QualificationDto {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站URL
     */
    private String siteURL;

    /**
     * 营 业 执 照 注 册号
     */
    private String businessLicenseNum;

    /**
     * 营 业 执 照 扫 描件
     */
    private String businessLicenseImagePath;

    /**
     * ICP 备案截图
     */
    private String icpImagePath;

    /**
     * 税务登记证号
     */
    private String taxRegistryNum;

    /**
     * 税 务 登 记 证扫描件
     */
    private String taxRegistryImagePath;

    /**
     * 小米行业
     */
    private int xiaomiIndustry;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum;
    }

    public String getTaxRegistryNum() {
        return taxRegistryNum;
    }

    public void setTaxRegistryNum(String taxRegistryNum) {
        this.taxRegistryNum = taxRegistryNum;
    }

    public String getBusinessLicenseImagePath() {
        return businessLicenseImagePath;
    }

    public void setBusinessLicenseImagePath(String businessLicenseImagePath) {
        this.businessLicenseImagePath = businessLicenseImagePath;
    }

    public String getIcpImagePath() {
        return icpImagePath;
    }

    public void setIcpImagePath(String icpImagePath) {
        this.icpImagePath = icpImagePath;
    }

    public String getTaxRegistryImagePath() {
        return taxRegistryImagePath;
    }

    public void setTaxRegistryImagePath(String taxRegistryImagePath) {
        this.taxRegistryImagePath = taxRegistryImagePath;
    }

    public int getXiaomiIndustry() {
        return xiaomiIndustry;
    }

    public void setXiaomiIndustry(int xiaomiIndustry) {
        this.xiaomiIndustry = xiaomiIndustry;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
