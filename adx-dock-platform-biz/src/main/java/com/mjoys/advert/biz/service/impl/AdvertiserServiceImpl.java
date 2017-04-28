package com.mjoys.advert.biz.service.impl;

import com.mjoys.advert.biz.dao.IQualAttachmentDao;
import com.mjoys.advert.biz.dao.IQualVerifyDao;
import com.mjoys.advert.biz.dao.IQualificationDao;
import com.mjoys.advert.biz.dao.IUserDao;
import com.mjoys.advert.biz.dto.QualAttachmentDto;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import com.mjoys.advert.biz.dto.QualificationDto;
import com.mjoys.advert.biz.dto.UserDto;
import com.mjoys.advert.biz.model.third.xiaomi.XiaoMiAdvertiser;
import com.mjoys.advert.biz.service.IAdvertiserService;
import com.mjoys.advert.biz.utils.StringTool;
import com.mjoys.advert.common.constants.ErrorCode;
import com.mjoys.advert.common.exception.InnerException;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:40.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Service("advertiserQIService")
public class AdvertiserServiceImpl extends BaseService implements IAdvertiserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IQualificationDao qualificationDao;

    @Autowired
    private IQualAttachmentDao qualAttachmentDao;

    @Autowired
    private IQualVerifyDao qualVerifyDao;


    @Override
    public XiaoMiAdvertiser getAdvertiserDetailForXiaoMi(Long advId) {
        // 1.查询广告主基本信息
        UserDto userDto = userDao.selectById(advId);
        if (userDto == null) {
            // 用户不存在
            throw new InnerException(ErrorCode.E14003);
        }

        // 2.广告主资质信息
        QualificationDto qualificationDto = qualificationDao.selectByUserId(advId);
        if (qualificationDto == null) {
            // 广告主资质信息不存在
            throw new InnerException(ErrorCode.E14004);
        }

        // 3.广告主特殊资质信息
        List<QualAttachmentDto> qualAttachmentDtos = qualAttachmentDao.selectListByAdvId(advId);

        // 4.构造结果
        XiaoMiAdvertiser xiaoMiAdvertiser = new XiaoMiAdvertiser();
        xiaoMiAdvertiser.setAddress(userDto.getAddress());
        xiaoMiAdvertiser.setCompanyName(userDto.getCompany());
        xiaoMiAdvertiser.setContactPerson(userDto.getContactPerson());
        xiaoMiAdvertiser.setContactsEmail(userDto.getEmail());
        xiaoMiAdvertiser.setMobilePhone(userDto.getMobilePhone());
        xiaoMiAdvertiser.setTelephone(userDto.getTelephone());


        xiaoMiAdvertiser.setBusinessLicenseNum(qualificationDto.getBusinessLicenseNum());
        xiaoMiAdvertiser.setBusinessLicensePic(qualificationDto.getBusinessLicenseImagePath());
        xiaoMiAdvertiser.setIcpPic(qualificationDto.getIcpImagePath());
        xiaoMiAdvertiser.setIndustry(qualificationDto.getXiaomiIndustry());
        xiaoMiAdvertiser.setTaxRegistryNum(qualificationDto.getTaxRegistryNum());
        xiaoMiAdvertiser.setTaxRegistryPic(qualificationDto.getTaxRegistryImagePath());
        xiaoMiAdvertiser.setWebsiteAddress(qualificationDto.getSiteURL());
        xiaoMiAdvertiser.setWebsiteName(qualificationDto.getSiteName());

        // 5.如果广告主具有特殊资质，则把特殊资质列表塞入结果
        if (CollectionUtils.isNotEmpty(qualAttachmentDtos)) {

            JSONArray qualifications = new JSONArray();

            for (QualAttachmentDto qualAttachmentDto : qualAttachmentDtos) {
                JSONObject jsonObject = new JSONObject();
                qualifications.add(jsonObject);

                jsonObject.put("key", qualAttachmentDto.getId());
                jsonObject.put("urls", StringTool.getStringListByStr(qualAttachmentDto.getAttachmentName()));
            }

            xiaoMiAdvertiser.setQualifications(qualifications);
        }

        return xiaoMiAdvertiser;
    }

    @Override
    public List<String> selectMarketAdvIds(Integer status, Integer market) {
        return qualVerifyDao.selectMarketAdvIds(status, market);
    }

    @Override
    public boolean isSuccessOrAuditOfstatus(Long advId, int market) {

        // 审核中、审核通过
        List<Integer> status = new ArrayList<>();
        status.add(QualVerifyDto.STATUS_OF_AUDIT);
        status.add(QualVerifyDto.STATUS_OF_PASS);

        if (CollectionUtils.isEmpty(qualVerifyDao.selectListByStatus(advId, market, status))) {
            return false;
        }
        return true;
    }

    @Override
    public int updatePassList(List<String> marketAdvIds) {
        return qualVerifyDao.updatePassList(marketAdvIds);
    }

    @Override
    public int updateRejectList(List<Map<String, Object>> params) {
        return qualVerifyDao.updateRejectList(params);
    }

    @Override
    public int insertQIRecord(QualVerifyDto qualVerifyDto) {
        return qualVerifyDao.insert(qualVerifyDto);
    }
}
