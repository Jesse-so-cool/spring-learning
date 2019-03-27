package com.jesse.springlearning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * --------------------------spring扩展-----------------------------
 *
 * BeanPostProcessor: bean初始化前后   进行控制
 * BeanFactoryPostProcessor: BeanFactory初始化后 BeanDefinition已经有定义但还未创建时  进行控制
 *
 */
@ComponentScan(basePackages = "com.jesse.springlearning",excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com.jesse.springlearning.config.*"))
@Configuration
public class Config4Extend {
}
