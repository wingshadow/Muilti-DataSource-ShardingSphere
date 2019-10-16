package com.flyshadow.datasource;

import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

/**
 * @Title: SqlSessionFactoryContainer
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 10:47
 */
public class SqlSessionFactoryContainer {
    private Map<String, SqlSessionFactory> sqlSessionFactorys;

    public SqlSessionFactoryContainer() {
    }

    public Map<String, SqlSessionFactory> getSqlSessionFactorys() {
        return sqlSessionFactorys;
    }

    public void setSqlSessionFactorys(Map<String, SqlSessionFactory> sqlSessionFactorys) {
        this.sqlSessionFactorys = sqlSessionFactorys;
    }
}
