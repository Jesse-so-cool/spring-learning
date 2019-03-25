package com.jesse.springlearning.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.*;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * spring 提供 用来根据环境动态切换激活组件的功能
 */
@PropertySource("application.properties")
@Configuration
@ComponentScan(value = "com.jesse.springlearning")
public class Config4Profile implements EmbeddedValueResolverAware {

    /**
     * 或者使用@value
     */
    //@Value("db.username")
    private String username;

    private String password;

    private String driverClass;


    @Profile("test")
    @Bean
    public DataSource testDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl("test");
        return dataSource;
    }

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl("dev");
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.username = resolver.resolveStringValue("db.username");
        this.password = resolver.resolveStringValue("db.password");
        this.driverClass = resolver.resolveStringValue("db.driverClass");
    }
}
