package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IOrderDao;
import org.springframework.stereotype.Repository;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 16:47.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDao implements IOrderDao {

    @Override
    public Long selectUserId(Long orderId) {
        return getAdsSqlSession().selectOne(getStamentNameSpace("selectUserId"), orderId);
    }
}
