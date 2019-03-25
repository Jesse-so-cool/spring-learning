package com.jesse.springlearning.config;

import com.jesse.springlearning.aop.LogAspects;
import com.jesse.springlearning.aop.Math;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 定义一个日志切面类
 * <p>
 * EnableXXX是spring中常用来开启某个功能的注解
 *
 * @EnableAspectJAutoProxy 注解作用是为了注册 AnnotationAwareAspectJAutoProxyCreator
 * 这个bean父类实现了BeanFactoryAware和SmartInstantiationAwareBeanPostProcessor
 * 下边分析 这个注解最终是如何起作用的
 * <p>
 * registerBeanPostProcessors:
 * 1)、先获取ioc容器中已经被定义了的需要创建对象的所有BeanPostProcessor
 * 2)、给容器中加上别的BeanPostProcessor
 * 3)、优先注册实现了PriorityOrdered接口的BeanPostProcessor
 * 4)、接着注册实现了Ordered接口的BeanPostProcessor
 * 5)、注册没显现上面这些接口的BeanPostProcessor     注册:先get相应的processor的bean（过程中第一次调用ioc容器会去创建） 找到bean后调用beanFactory.addBeanPostProcessor(postProcessor) 步骤完成
 * 1）、创建bean实例
 * 2）、populateBean 给bean属性赋值
 * 3）、invokeAwareMethods： 处理实现了XXXAware接口的Bean
 * 4）、processor.postProcessBeforeInitialization
 * 5）、invokeInitMethods 执行初始化方法
 * 6）、processor.postProcessAfterInitialization
 * -------------------以上是AnnotationAwareAspectJAutoProxyCreator的创建过程------------------
 *
 * finishBeanFactoryInitialization ： 完成BeanFactory初始化工作，创建剩下的单实例Bean
 * 1）、遍历所有bean 一次调用getBean()
 * 2）、getBean
 *      1）、从缓存查找、找不到就createBean
 *      2）、createBean
 *          1）、resolveBeforeInstantiation 	先尝试用后置处理器返回对象
 *          【BeanPostProcessor是在Bean对象创建完成初始化前后调用】
 *          【InstantiationAwareBeanPostProcessor是在创建Bean实例之前 先尝试用后置处理器返回对象】
 *          此处结论：AnnotationAwareAspectJAutoProxyCreator在任何bean创建之前会有一个拦截 因为他是InstantiationAwareBeanPostProcessor后置处理器
 *              1)、查看AnnotationAwareAspectJAutoProxyCreator中postProcessBeforeInstantiation方法(每一次bean调用 都会调用该方法)   下面只关心Math、LogAspects的创建
 *                  1）、判断当前bean是否在AdviseBeans中
 *                  2）、判断bean是不是切面类型的（@Aspects）
 *                  3）、是否需要跳过
 *                      Math都不符合 -> 获取增强器（LogAspects中的四个方法）
 *                      最后只是返回普通Math对象出去
 *              2）、查看AnnotationAwareAspectJAutoProxyCreator中postProcessAfterInitialization方法  创建完实例之后
 *                  1）、获取增强器（LogAspects中的四个方法）
 *                  2）、给增强器排序
 *                  3）、保存bean到adviseBean中
 *                  4）、获取增强器 保存到proxyFactory
 *                  5）、创建代理对象
 *                      JdkDynamicAopProxy jdk动态代理（实现了接口的话）
 *                      ObjenesisCglibAopProxy  cglib动态代理
 *                  6）、math这个bean 在此时会被返回cglib动态代理  增强了的 对象
 *          2）、如果上边的尝试获取bean失败的话 调用doCreateBean->createBeanInstance->populateBean 和上边的creaeBean是一样的
 *
 *
 */
@ComponentScan("com.jesse.springlearning")
@Configuration
@EnableAspectJAutoProxy
public class Config4AOP {
    @Bean
    public Math math() {
        return new Math();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
