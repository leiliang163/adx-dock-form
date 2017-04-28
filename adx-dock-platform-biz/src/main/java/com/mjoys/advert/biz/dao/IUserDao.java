package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.UserDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:18.<br/>
 * 功能描述 : 用户.<br/>
 * 变更记录 : .<br/>
 */
public interface IUserDao {

    /**
     * 查询用户信息
     *
     * @param id the id
     *
     * @return user dto
     */
    UserDto selectById(Long id);


}
