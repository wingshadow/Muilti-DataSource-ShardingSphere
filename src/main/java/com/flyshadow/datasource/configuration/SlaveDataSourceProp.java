package com.flyshadow.datasource.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: SlaveDataSourceProp
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/15 17:03
 */
@Component
@ConfigurationProperties(value = "spring.datasource.slave")
public class SlaveDataSourceProp extends BaseDataSourceProp {
}
