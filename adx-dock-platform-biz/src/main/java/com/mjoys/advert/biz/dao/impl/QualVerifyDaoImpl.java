package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.IQualVerifyDao;
import com.mjoys.advert.biz.dto.QualVerifyDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/18 14:57.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("qualVerifyDao")
public class QualVerifyDaoImpl extends BaseDao implements IQualVerifyDao {

    @Override
    public int insert(QualVerifyDto qualVerifyDto) {

        return getAdsSqlSession().insert(getStamentNameSpace("insert"), qualVerifyDto);
    }

    @Override
    public int updatePassList(List<String> marketAdvIds) {
        if (CollectionUtils.isEmpty(marketAdvIds)) {
            return 0;
        }
        return getAdsSqlSession().update(getStamentNameSpace("updatePassList"), marketAdvIds);
    }

    @Override
    public int updateRejectList(List<Map<String, Object>> params) {
        if (CollectionUtils.isEmpty(params)) {
            return 0;
        }

        return getAdsSqlSession().update(getStamentNameSpace("updateRejectList"), params);
    }

    @Override
    public List<String> selectMarketAdvIds(Integer status, Integer market) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("status", status);
        param.put("market", market);

        return getAdsSqlSession().selectList(getStamentNameSpace("selectMarketAdvIds"), param);
    }

    @Override
    public List<QualVerifyDto> selectListByStatus(Long advId, int market, List<Integer> status) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("advId", advId);
        param.put("market", market);
        param.put("status", status);

        return getAdsSqlSession().selectList(getStamentNameSpace("selectListByStatus"), param);
    }

    @Override
    public List<QualVerifyDto> selectListByMarket(List<Long> advIds, int market) {
        if(CollectionUtils.isEmpty(advIds)){
            return ListUtils.EMPTY_LIST;
        }
        Map<String, Object> param = new HashMap<>(2);
        param.put("advIds", advIds);
        param.put("market", market);

        return getAdsSqlSession().selectList(getStamentNameSpace("selectListByMarket"), param);
    }

    @Override
    public int updateByMarketId(String marketAdvId, int status, String reason) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("marketAdvId", marketAdvId);
        param.put("status", status);
        param.put("reason", reason);

        return getAdsSqlSession().update(getStamentNameSpace("updateByMarketId"), param);
    }
}
