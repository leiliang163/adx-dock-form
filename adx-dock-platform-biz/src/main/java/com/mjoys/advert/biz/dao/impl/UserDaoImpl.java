package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IUserDao;
import com.mjoys.advert.biz.dto.UserDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 16:47.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements IUserDao {

    @Override
    public UserDto selectById(Long id) {
        if (id == null) {
            return null;
        }
        return getAdsSqlSession().selectOne(getStamentNameSpace("selectById"), id);
    }
}
