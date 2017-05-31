package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.ICreativeSeriesDao;
import com.mjoys.advert.biz.dto.CreativeSeriesDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/17 16:31.<br/>
 * 功能描述 : 创意系列.<br/>
 * 变更记录 : .<br/>
 */
@Repository("creativeSeriesDao")
public class CreativeSeriesDaoImpl extends BaseDao implements ICreativeSeriesDao {

    @Override
    public List<Long> selectIdsByType(List<Long> ids, Integer type) {
        if (CollectionUtils.isEmpty(ids)) {
            return ListUtils.EMPTY_LIST;
        }

        Map<String, Object> param = new HashMap<>(2);
        param.put("ids", ids);
        param.put("type", type);

        return getMgrSqlSession().selectList(getStamentNameSpace("selectIdsByType"), param);
    }
}
