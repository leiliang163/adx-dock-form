package com.mjoys.advert.biz.dao;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:18.<br/>
 * 功能描述 : 用户.<br/>
 * 变更记录 : .<br/>
 */
public interface IOrderDao {

    /**
     * 查询用户信息
     *
     * @param orderId the order id
     *
     * @return user dto
     */
    Long selectUserId(Long orderId);


}
