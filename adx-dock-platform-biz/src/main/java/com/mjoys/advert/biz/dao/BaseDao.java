package com.mjoys.advert.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by leiliang on 2017/3/30.
 */
public class BaseDao {
    protected Logger logger                   = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("adsSqlSessionTemplate")
    protected SqlSessionTemplate adsSqlSession;

    @Autowired
    @Qualifier("mgrSqlSessionTemplate")
    protected SqlSessionTemplate mgrSqlSession;

    protected SqlSessionTemplate getMgrSqlSession() {
        return mgrSqlSession;
    }

    protected SqlSessionTemplate getAdsSqlSession() {
        return adsSqlSession;
    }

    protected String getStamentNameSpace(String method) {
        return getClass().getName() + "." + method;
    }
}
