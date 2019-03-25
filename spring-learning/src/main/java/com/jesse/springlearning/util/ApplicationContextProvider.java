package com.jesse.springlearning.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 通过实现ApplicationContextAware 来注入applicationContext是spring提供给我们的方法
 * <p>
 * 原理是：
 * 通过ApplicationContextAwareProcessor这个后置处理器
 * 在bean创建之后、初始化之前调用postProcessBeforeInitialization
 * 通过判断是否是通过实现ApplicationContextAware的实现类，
 * 是的话，则调用setApplicationContext
 *
 * XXXAware，功能使用XXXProcessor
 */
@Component
public class ApplicationContextProvider
        implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware传入的：" + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("EmbeddedValueResolverAware传入的" + resolver);
        System.out.println("os.name:" + resolver.resolveStringValue("${os.name}"));
    }
}
