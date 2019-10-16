package com.flyshadow.datasource;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Title: SqlSessionDaoSupport
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 10:47
 */
@Setter
@Getter
public class SqlSessionDaoSupport {

    private String defaultDataSource = "master";

    private Map<String, SqlSession> sqlSessions;

    public SqlSession getDefaultSqlSession(){
        return sqlSessions.get(defaultDataSource);
    }

    public SqlSession getMaster(String master){
        return sqlSessions.get(master);
    }

    public SqlSession getSlave(String slave){
        return sqlSessions.get(slave);
    }

    public final SqlSession getSqlSession(String dataSourceName) {
        if (StringUtils.isNotBlank(dataSourceName)) {
            return sqlSessions.get(dataSourceName);
        }
        return sqlSessions.get(defaultDataSource);
    }

    public final <T> T getSqlMapper(Class<T> paramClass) {
        return getDefaultSqlSession().getMapper(paramClass);
    }

    public final <T> T getSqlMapper(Class<T> paramClass, String dsContext) {
        return getSqlSession(dsContext).getMapper(paramClass);
    }

    public void setSqlSessionFactoryContainer(SqlSessionFactoryContainer container){
        sqlSessions = new HashMap<>();
        Map<String, SqlSessionFactory> sessionFactoryMap = container.getSqlSessionFactorys();
        Iterator<Map.Entry<String, SqlSessionFactory>> iterator =
                sessionFactoryMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, SqlSessionFactory> entry = iterator.next();
            String key = entry.getKey();
            SqlSessionFactory factory = entry.getValue();
            SqlSessionTemplate sqlSessionTemplate =
                    new SqlSessionTemplate(factory);
            sqlSessions.put(key,sqlSessionTemplate);
        }
    }
}
