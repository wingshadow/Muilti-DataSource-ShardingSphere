package com.flyshadow.datasource.configuration;

import com.flyshadow.sharding.algorithm.PeriodPreciseShardingAlgorithm;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: ShardingDataSourceUtil
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/15 9:42
 */
@Slf4j
public class ShardingDataSourceUtil {
    public static DataSource makeDataSource(BaseDataSourceProp baseDataSourceProp){
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setDriverClassName(baseDataSourceProp.getDriverClassName());
        jdbcConfig.setJdbcUrl(baseDataSourceProp.getUrl());
        jdbcConfig.setUsername(baseDataSourceProp.getUsername());
        jdbcConfig.setPassword(baseDataSourceProp.getPassword());
        jdbcConfig.setMinimumIdle(baseDataSourceProp.getMinimumIdle());
        jdbcConfig.setMaximumPoolSize(baseDataSourceProp.getMaximumPoolSize());
        jdbcConfig.setAutoCommit(baseDataSourceProp.getAutoCommit());
        jdbcConfig.setIdleTimeout(baseDataSourceProp.getIdleTimeout());
        jdbcConfig.setPoolName(baseDataSourceProp.getPoolName());
        jdbcConfig.setMaxLifetime(baseDataSourceProp.getMaxLifetime());
        jdbcConfig.setConnectionTimeout(baseDataSourceProp.getConnectionTimeout());
        jdbcConfig.setConnectionTestQuery(baseDataSourceProp.getConnectionTestQuery());
        return new HikariDataSource(jdbcConfig);
    }

    public static DataSource shardingDataSource(BaseDataSourceProp dataSourceProp) throws SQLException {
        DataSource druidDataSource = makeDataSource(dataSourceProp);

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("database0", druidDataSource);


        TableRuleConfiguration periodRuleConfig = new TableRuleConfiguration(
                "t_order");
        periodRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                new PeriodPreciseShardingAlgorithm()));

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(periodRuleConfig);

        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration,
                new Properties());
    }

}
