package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.CreativeSeriesDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/17 15:30.<br/>
 * 功能描述 : 创意系列.<br/>
 * 变更记录 : .<br/>
 */
public interface ICreativeSeriesDao {

    /**
     * 查询出指定的类型的系列ID列表.
     *
     * @param ids  the ids
     * @param type the type
     *
     * @return the list
     */
    List<Long> selectIdsByType(List<Long> ids, Integer type);
}
