package com.jesse.springlearning.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * --------------------声明式事务--------------------
 *
 * @EnableTransactionManagement 注册了两个组件
 * AutoProxyRegistrar:
 *      1）、注册了InfrastructureAdvisorAutoProxyCreator
 *             1）、利用后置处理器在对象创建之后 包装对象 返回代理对象
 *
 * ProxyTransactionManagementConfiguration.
 *      1）、利用@bean给容器注册事务增强器BeanFactoryTransactionAttributeSourceAdvisor
 *          1）、AnnotationTransactionAttributeSource 解析事务注解
 *          2）、TransactionInterceptor 事务拦截器 保存事务属性信息
 *              它是一个MethodInterceptor（和aop的一样 最终会调用invoke方法）
 *              invoke->invokeWithinTransaction
 *                  1)、先获取事务相关的属性
 *                  2）、再获取PlatformTransactionManager（如果没设定manager就直接容器按再获取PlatformTransactionManager类型取）
 *                  3）、方法执行
 *                      正常-->txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
 *                      异常-->txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
 */
@PropertySource("application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jesse.springlearning",excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com.jesse.springlearning.config"))
@Configuration
public class Config4Transaction implements EmbeddedValueResolverAware {
    private String username;

    private String password;

    private String driverClass;
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    /**
     * 注册事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.username = resolver.resolveStringValue("db.username");
        this.password = resolver.resolveStringValue("db.password");
        this.driverClass = resolver.resolveStringValue("db.driverClass");
    }
}
