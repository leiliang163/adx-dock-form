package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IQualificationDao;
import com.mjoys.advert.biz.dto.QualificationDto;
import org.springframework.stereotype.Repository;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:04.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("qualificationDao")
public class QualificationDaoImpl extends BaseDao implements IQualificationDao {

    @Override
    public QualificationDto selectByUserId(Long userId) {
        if(userId == null){
            return null;
        }

        return getAdsSqlSession().selectOne(getStamentNameSpace("selectByUserId"), userId);
    }
}
