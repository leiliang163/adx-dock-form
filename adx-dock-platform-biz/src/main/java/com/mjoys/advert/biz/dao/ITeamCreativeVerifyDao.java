package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.TeamCreativeVerifyDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:12.<br/>
 * 功能描述 : 创意审核表.<br/>
 * 变更记录 : .<br/>
 */
public interface ITeamCreativeVerifyDao {

    /**
     * 根据创意审核ID列表批量查询审核信息
     * @param ids
     * @return
     */
    List<TeamCreativeVerifyDto> selectByIds(List<Long> ids);

    /**
     * 批量查询审核ID，通过创意ID列表
     *
     * @param creativeIds
     * @return
     */
    List<Long> selectIdsByCreativeIds(List<Long> creativeIds);

    /**
     * 通过创意ID列表批量查询审核信息
     *
     * @param creativeIds
     * @return
     */
    List<TeamCreativeVerifyDto> selectByCreativeIds(List<Long> creativeIds);
}
