package com.flyshadow.datasource.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: MasterDataSourceProp
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/15 17:02
 */
@Component
@ConfigurationProperties(value = "spring.datasource.master")
public class MasterDataSourceProp extends BaseDataSourceProp {
}
