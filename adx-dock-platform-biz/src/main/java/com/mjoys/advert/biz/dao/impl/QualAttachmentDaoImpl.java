package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IQualAttachmentDao;
import com.mjoys.advert.biz.dto.QualAttachmentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 17:14.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("qualAttachmentDao")
public class QualAttachmentDaoImpl extends BaseDao implements IQualAttachmentDao {

    @Override
    public List<QualAttachmentDto> selectListByAdvId(Long advId) {

        return getAdsSqlSession().selectList(getStamentNameSpace("selectListByAdvId"), advId);
    }
}
