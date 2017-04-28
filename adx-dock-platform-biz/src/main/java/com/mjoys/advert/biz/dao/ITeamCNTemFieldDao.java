package com.mjoys.advert.biz.dao;

import com.mjoys.advert.biz.dto.TeamCNTemFieldDto;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:36.<br/>
 * 功能描述 : 创意字段定义表.<br/>
 * 变更记录 : .<br/>
 */
public interface ITeamCNTemFieldDao {

    /**
     * 根据创意ID列表批量查询创意字段信息
     *
     * @param creativeIds
     * @return
     */
    List<TeamCNTemFieldDto> selectByCreativeIds(List<Long> creativeIds);
}
