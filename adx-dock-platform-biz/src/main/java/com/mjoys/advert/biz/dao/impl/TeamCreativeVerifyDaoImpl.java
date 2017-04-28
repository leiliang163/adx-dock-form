package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.ITeamCreativeVerifyDao;
import com.mjoys.advert.biz.dto.TeamCreativeVerifyDto;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:22.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("teamCreativeVerifyDao")
public class TeamCreativeVerifyDaoImpl extends BaseDao implements ITeamCreativeVerifyDao {

    @Override
    public List<TeamCreativeVerifyDto> selectByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ListUtils.EMPTY_LIST;
        }

        return getMgrSqlSession().selectList(getStamentNameSpace("selectByIds"), ids);
    }

    @Override
    public List<Long> selectIdsByCreativeIds(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return ListUtils.EMPTY_LIST;
        }

        return getMgrSqlSession().selectList(getStamentNameSpace("selectIdsByCreativeIds"), creativeIds);
    }

    @Override
    public List<TeamCreativeVerifyDto> selectByCreativeIds(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return ListUtils.EMPTY_LIST;
        }

        return getMgrSqlSession().selectList(getStamentNameSpace("selectByCreativeIds"), creativeIds);
    }
}
