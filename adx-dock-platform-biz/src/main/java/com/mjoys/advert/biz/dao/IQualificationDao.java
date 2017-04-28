package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.QualificationDto;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:19.<br/>
 * 功能描述 : 资质审核表.<br/>
 * 变更记录 : .<br/>
 */
public interface IQualificationDao {

    /**
     * 查询广告主的资质信息
     * @param userId
     * @return
     */
    QualificationDto selectByUserId(Long userId);
}
