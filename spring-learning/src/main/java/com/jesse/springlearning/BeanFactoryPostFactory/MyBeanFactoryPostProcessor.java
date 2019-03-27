package com.jesse.springlearning.BeanFactoryPostFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor........postProcessBeanFactory");
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        final int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
    }
}
