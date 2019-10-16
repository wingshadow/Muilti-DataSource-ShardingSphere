package com.flyshadow.datasource.configuration;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Title: BaseDataSourceProp
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/15 11:03
 */
@Setter
@Getter
public class BaseDataSourceProp implements Serializable {
    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int minimumIdle;
    private int maximumPoolSize;
    private Boolean autoCommit;
    private long idleTimeout;
    private String poolName;
    private long maxLifetime;
    private long connectionTimeout;
    private String connectionTestQuery;
}
