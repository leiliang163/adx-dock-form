package com.mjoys.advert.biz.dao.impl;

import com.mjoys.advert.biz.dao.BaseDao;
import com.mjoys.advert.biz.dao.ICreativeMarketVerifyDao;
import com.mjoys.advert.biz.dto.CreativeMarketVerifyDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/19 11:20.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Repository("creativeMarketVerifyDao")
public class CreativeMarketVerifyDaoImlpl extends BaseDao implements ICreativeMarketVerifyDao {

    @Override
    public List<CreativeMarketVerifyDto> selectList(Integer market, Integer verifyStatus) {
        Map<String, Object> prarm = new HashMap<>(2);
        prarm.put("market", market);
        prarm.put("verifyStatus", verifyStatus);

        return getMgrSqlSession().selectList(getStamentNameSpace("selectList"), prarm);
    }

    @Override
    public List<String> selectAdxCreativeIds(Integer market, Integer verifyStatus) {
        Map<String, Object> prarm = new HashMap<>(2);
        prarm.put("market", market);
        prarm.put("verifyStatus", verifyStatus);

        return getMgrSqlSession().selectList(getStamentNameSpace("selectAdxCreativeIds"), prarm);
    }

    @Override
    public int updateVerifyStatus(List<CreativeMarketVerifyDto> creativeVerifyList) {
        if (CollectionUtils.isEmpty(creativeVerifyList)) {
            return 0;
        }

        return getMgrSqlSession().update(getStamentNameSpace("updateVerifyStatus"),
                                         creativeVerifyList);
    }
}
