package com.flyshadow;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Title: Application
 * @ProjectName sharding-master-slave
 * @Author FlyShadow
 * @Date 2019/10/16 14:27
 * @deprecated
 * 禁止springboot组件DataSourceAutoConfiguration，MybatisAutoConfiguration
 * 和ShardingSphere组件SpringBootConfiguration自动配置数据源。因为系统手动配置
 * 双数据源+读写分离+数据分片
 *
 */
@SpringBootApplication(exclude ={ DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class, SpringBootConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
