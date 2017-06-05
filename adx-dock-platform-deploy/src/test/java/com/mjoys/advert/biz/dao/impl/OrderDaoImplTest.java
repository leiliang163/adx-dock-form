package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.base.BaseJunit4Test;
import com.mjoys.advert.biz.dao.IOrderItemDao;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/6/2 16:56.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class OrderDaoImplTest extends BaseJunit4Test {

    @Autowired
    private IOrderItemDao iOrderItemDao;

    /**
     * Method: selectUserId(Long orderId)
     */
    @Test
    public void testSelectUserId() throws Exception {
        iOrderItemDao.selectUserId(1L);
        Assert.assertTrue(true);
    }
}
