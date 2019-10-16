package com.flyshadow.common;

import com.flyshadow.consts.DataSouceNameConsts;
import com.flyshadow.datasource.SqlSessionDaoSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: AbstractBaseService
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:27
 */
@Slf4j
@Component
public abstract class AbstractBaseServiceImpl {

    @Resource
    private SqlSessionDaoSupport sqlSessionDaoSupport;

    public static <T> String sqlId(String sqlId, Class<T> clazz) {
        return clazz.getName() + "Mapper." + sqlId;
    }

    public <T> T getSqlMapper(Class<T> daoClass) {
        return sqlSessionDaoSupport.getSqlMapper(daoClass);
    }

    public <T> T getSqlMapper(Class<T> daoClass, String dsContext) {
        return sqlSessionDaoSupport.getSqlMapper(daoClass, dsContext);
    }

    public final SqlSession getDataSourceSession(String dataSourName){
        return sqlSessionDaoSupport.getSqlSession(dataSourName);
    }

    public final SqlSession getMasterSession(){
        return sqlSessionDaoSupport.getSqlSession(DataSouceNameConsts.MASTER_DATASOURCE);
    }

    public final SqlSession getSlaveSesson(){
        return sqlSessionDaoSupport.getSqlSession(DataSouceNameConsts.SLAVE_DATASOURCE);
    }
}
