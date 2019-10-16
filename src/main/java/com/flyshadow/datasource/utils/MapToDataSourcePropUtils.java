package com.flyshadow.datasource.utils;

import com.flyshadow.datasource.configuration.BaseDataSourceProp;

import java.util.Map;

/**
 * @Title: MapToDataSourceProp
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/15 15:01
 */
public class MapToDataSourcePropUtils {
    public static BaseDataSourceProp getProp(Map<String, String> map) {
        BaseDataSourceProp baseDataSourceProp = new BaseDataSourceProp();
        baseDataSourceProp.setUrl(map.get("url"));
        baseDataSourceProp.setUsername(map.get("username"));
        baseDataSourceProp.setPassword(map.get("password"));
        baseDataSourceProp.setDriverClassName(map.get("driver-class-name"));
        baseDataSourceProp.setMinimumIdle(Integer.parseInt(map.get("minimum-idle")));
        baseDataSourceProp.setMaximumPoolSize(Integer.parseInt(map.get("maximum-pool-size")));
        baseDataSourceProp.setAutoCommit(Boolean.valueOf(map.get("auto-commit")));
        baseDataSourceProp.setIdleTimeout(Long.parseLong(map.get("idle-timeout")));
        baseDataSourceProp.setPoolName(map.get("pool-name"));
        baseDataSourceProp.setMaxLifetime(Long.parseLong(map.get("max-lifetime")));
        baseDataSourceProp.setConnectionTimeout(Long.parseLong(map.get("connection-timeout")));
        baseDataSourceProp.setConnectionTestQuery(map.get("connection-test-query"));
        return baseDataSourceProp;
    }
}
