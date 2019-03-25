package com.jesse.springlearning.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean的生命周期： Bean创建--初始化--销毁
 * <p>
 * Bean的后置处理器
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 初始化（like InitializingBean's {@code afterPropertiesSet}
     * or a custom init-method）之前执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "...postProcessBeforeInitialization...");
        return null;
    }

    /**
     * * 初始化（like InitializingBean's {@code afterPropertiesSet}
     * * 	      or a custom init-method）之后执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "...postProcessAfterInitialization...");
        return bean;
    }
}
