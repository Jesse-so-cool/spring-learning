package com.jesse.springlearning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 步骤：
 *     1）、写一个监听器(ApplicationListener实现类)来监听某个事件(ApplicationEvent及其子类)
 *     2）、加到容器
 *     3）、只要容器有相关事件发布了 监听器就会执行
 *     4）、也可以自己发布一个事件，手动触发监听器  context.publishEvent
 *
 * 执行过程（容器创建）:
 *      1）、initApplicationEventMulticaster() 往容器中注册一个广播器
 *             容器钟查找id为applicationEventMulticaster 的bean
 *             ->有 直接getBean 可以手动注册 通过SimpleApplicationEventMulticaster.setTaskExecutor 提供异步和同步的executor
 *             ->没有 new SimpleApplicationEventMulticaster(beanFactory)去创建一个
 *      2）、registerListeners()
 *              1）、取出监听器 getBeanNamesForType(ApplicationListener.class, true, false);
 * 			    2）、监听器加到广播器中 getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *      3）、finishRefresh()
 *           ->publishEvent()
 *           发布事件 getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
 *
 * 使用注解@EventListener来监听：
 *      1）、使用EventListenerMethodProcessor处理这个注解
 *      2）、EventListenerMethodProcessor实现了SmartInitializingSingleton接口
 *      3）、SmartInitializingSingleton的  afterSingletonsInstantiated  方法: 在regular的单实例 实例化结束后调用
 *      4）、在容器创建过程中的finishBeanFactoryInitialization(beanFactory)
 *           ->beanFactory.preInstantiateSingletons()
 *              1）、遍历bean去getBean
 *              2）、遍历bean 如果存在SmartInitializingSingleton的子类 执行afterSingletonsInstantiated
 *                   ->processBean(beanName, type) 遍历bean 查找是否有@EventListener注解
 *                   ->有 new一个listener  执行context.addApplicationListener(applicationListener)
 *                   ->没有 跳过
 *      5）、容器创建过程中 finishRefresh()
 *              1）、publishEvent(new ContextRefreshedEvent(this));
 */
@ComponentScan(basePackages = "com.jesse.springlearning.listener")
@Configuration
public class Config4Listener {




}
