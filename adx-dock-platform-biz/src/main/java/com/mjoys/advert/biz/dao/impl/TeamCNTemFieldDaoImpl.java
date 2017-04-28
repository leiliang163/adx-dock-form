package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.ITeamCNTemFieldDao;
import com.mjoys.advert.biz.dto.TeamCNTemFieldDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 14:41.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("teamCNTemFieldDao")
public class TeamCNTemFieldDaoImpl extends BaseDao implements ITeamCNTemFieldDao {

    @Override
    public List<TeamCNTemFieldDto> selectByCreativeIds(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return ListUtils.EMPTY_LIST;
        }
        return getMgrSqlSession().selectList(getStamentNameSpace("selectByCreativeIds"), creativeIds);
    }
}
