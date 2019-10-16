package com.flyshadow.datasource;

import com.flyshadow.datasource.configuration.MasterDataSourceProp;
import com.flyshadow.datasource.configuration.ShardingDataSourceUtil;
import com.flyshadow.datasource.configuration.SlaveDataSourceProp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: DataSourceConfig
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 10:01
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Resource
    private MasterDataSourceProp masterDataSourceProp;

    @Resource
    private SlaveDataSourceProp slaveDataSourceProp;

    @Bean(name = "master")
    public DataSource master() throws SQLException {
        return ShardingDataSourceUtil.shardingDataSource(masterDataSourceProp);
    }

    @Bean(name = "slave")
    public DataSource slave() throws SQLException {
        return ShardingDataSourceUtil.shardingDataSource(slaveDataSourceProp);

    }

    @Bean
    public SqlSessionFactory masterFactory(@Qualifier(
            "master") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().
                getResource("classpath:mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    public SqlSessionFactory slaveFactory(@Qualifier(
            "slave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().
                getResource("classpath:mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager masterTransactionManager(@Qualifier(
            "master") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "maserTransactionInterceptor")
    public TransactionInterceptor masterTransactionInterceptor(@Qualifier(
            "transactionManager") DataSourceTransactionManager manager) {
        TransactionInterceptor interceptor = new TransactionInterceptor();
        interceptor.setTransactionManager(manager);

        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED," +
                "-Exception");
        interceptor.setTransactionAttributes(transactionAttributes);
        return interceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy =
                new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*Service");
        transactionAutoProxy.setInterceptorNames("maserTransactionInterceptor");
        return transactionAutoProxy;
    }

    @Bean
    public SqlSessionFactoryContainer sqlSessionFactoryContainer(@Qualifier(
            "masterFactory") SqlSessionFactory master, @Qualifier(
            "slaveFactory") SqlSessionFactory slave) {
        SqlSessionFactoryContainer container = new SqlSessionFactoryContainer();
        Map<String, SqlSessionFactory> sqlSessionFactorys = new HashMap<>();
        sqlSessionFactorys.put("master", master);
        sqlSessionFactorys.put("slave", slave);
        container.setSqlSessionFactorys(sqlSessionFactorys);
        return container;
    }

    @Bean
    public SqlSessionDaoSupport sqlSessionDaoSupport(@Qualifier(
            "sqlSessionFactoryContainer") SqlSessionFactoryContainer container){
        SqlSessionDaoSupport sessionDaoSupport = new SqlSessionDaoSupport();
        sessionDaoSupport.setSqlSessionFactoryContainer(container);
        return sessionDaoSupport;
    }
}