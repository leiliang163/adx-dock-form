package com.mjoys.advert.biz.model.third.xiaomi;

import com.alibaba.dubbo.common.json.JSONArray;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/17 17:55.<br/>
 * 功能描述 : 小米广告主对象.<br/>
 * 变更记录 : .<br/>
 */
public class XiaoMiAdvertiser implements Serializable {

    private static final long serialVersionUID = 1468732344070278793L;


    /**
     * 公司注册名称
     */
    private String companyName;

    /**
     * 营 业 执 照 注 册号
     */
    private String businessLicenseNum;

    /**
     * 营 业 执 照 扫 描件
     */
    private String businessLicensePic;

    /**
     * 网站名称
     */
    private String websiteName;

    /**
     * ICP备案截图
     */
    private String icpPic;

    /**
     * 网址地址
     */
    private String websiteAddress;

    /**
     * 税务登记证号
     */
    private String taxRegistryNum;

    /**
     * 税 务 登 记 证 扫
     */
    private String taxRegistryPic;

    /**
     * 不 能 超 出 给
     * 出行业范围
     * 行业
     */
    private int industry;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 电子邮件
     */
    private String contactsEmail;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 资质，特殊资质
     */
    private JSONArray qualifications;

    /**
     * 流量市场侧广告主ID.
     */
    private String            advId;

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum;
    }

    public String getBusinessLicensePic() {
        return businessLicensePic;
    }

    public void setBusinessLicensePic(String businessLicensePic) {
        this.businessLicensePic = businessLicensePic;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getIcpPic() {
        return icpPic;
    }

    public void setIcpPic(String icpPic) {
        this.icpPic = icpPic;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getTaxRegistryNum() {
        return taxRegistryNum;
    }

    public void setTaxRegistryNum(String taxRegistryNum) {
        this.taxRegistryNum = taxRegistryNum;
    }

    public String getTaxRegistryPic() {
        return taxRegistryPic;
    }

    public void setTaxRegistryPic(String taxRegistryPic) {
        this.taxRegistryPic = taxRegistryPic;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JSONArray getQualifications() {
        return qualifications;
    }

    public void setQualifications(JSONArray qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
