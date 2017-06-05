package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IOrderItemDao;
import org.springframework.stereotype.Repository;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 16:47.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("orderItemDao")
public class OrderItemDaoImpl extends BaseDao implements IOrderItemDao {

    @Override
    public Long selectUserId(Long orderId) {
        return getAdsSqlSession().selectOne(getStamentNameSpace("selectUserId"), orderId);
    }

}
