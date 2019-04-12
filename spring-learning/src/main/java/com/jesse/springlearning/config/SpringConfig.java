package com.jesse.springlearning.config;

import com.jesse.springlearning.condition.LinuxCondition;
import com.jesse.springlearning.condition.WindowsCondition;
import com.jesse.springlearning.dao.ColorDao;
import com.jesse.springlearning.po.Student;
import com.jesse.springlearning.service.ColorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * 取代xml 成为配置类
 *
 * --------------------------------------------------------------------
 *                          Spring容器创建过程
 * --------------------------------------------------------------------
 *
 * refresh()
 *      1）、prepareRefresh()
 *          1)、initPropertySources() 子类自定义属性设置的方法
 *          2）、getEnvironment().validateRequiredProperties() 校验设置属性
 *          3）、this.earlyApplicationEvents = new LinkedHashSet<>() 保存容器早起的事件
 *      2）、obtainFreshBeanFactory() 获取工厂
 *          1）、refreshBeanFactory() 刷新工厂
 *              -> beanFactory.setSerializationId()
 *          2）、getBeanFactory
 *              直接返回beanFactory
 *      3）、prepareBeanFactory(beanFactory)
 *          1）、在工厂设置类加载器、表达式解析器
 *          2）、给工厂添加ApplicationContextAwareProcessor
 *              并设置需要忽略的自动装配的接口(EnvironmentAware、ApplicationContextAware等Aware)
 *          3）、设置可以自动装配的 BeanFactory、ApplicationEventPublisher、ResourceLoader、ApplicationContext
 *          4）、给工厂添加 beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this))
 *          5）、给容器注册一些组件
 *               environment（ConfigurableEnvironment）
 *               systemProperties（Map<String, Object>）
 *               systemEnvironment（Map<String, Object>）
 *     4）、postProcessBeanFactory(beanFactory) BeanFactory准备工作完成后 后置处理
 *              空方法 子类自定义属性设置的方法
 *     5）、invokeBeanFactoryPostProcessors(beanFactory) 执行BeanFactoryPostProcessors
 *              BeanFactoryPostProcessors: BeanFactory后置处理器
 *                  BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor两个接口
 *                  1.获取所有的BeanDefinitionRegistryPostProcessor
 *                  2.依次执行实现了PriorityOrdered、Ordered接口和没有实现它们的  processor
 *                    postProcessor.postProcessBeanDefinitionRegistry(registry)  《注册各个config类下的bean信息到BeanDefinition》
 *                  3.获取所有的BeanFactoryPostProcessor
 *                  4.依次执行实现了PriorityOrdered、Ordered接口和没有实现它们的  processor
 *                    postProcessor.postProcessBeanFactory(beanFactory)
 *     6）、registerBeanPostProcessors(beanFactory) 注册BeanPostProcessor
 *              BeanPostProcessor的实现子接口:
 *                  1.DestructionAwareBeanPostProcessor
 *                  2.InstantiationAwareBeanPostProcessor
 *                  3.MergedBeanDefinitionPostProcessor
 *                  4.SmartInstantiationAwareBeanPostProcessor
 *                  5.BeanPostProcessor
 *              1）、获取所有BeanPostProcessor
 *              2）、依次注册了PriorityOrdered、Ordered接口、没有实现它们的、MergedBeanDefinitionPostProcessor
 *                  processor(beanFactory.addBeanPostProcessor(postProcessor);)
 *              3）、注册ApplicationListenerDetector :在bean初始化后 检查如果是listener的话 执行applicationContext.addApplicationListener
 *     7）、initMessageSource() 初始化MessageSource组件(做国际化、消息绑定、消息解析)
 *          1）、获取BeanFactory
 *          2）、查看容器是否有messageSource的组件
 *               有->this.applicationEventMulticaster beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class)
 *               没有->new DelegatingMessageSource()
 *                    beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
 *     8）、initApplicationEventMulticaster()  初始化事件派发器
 *          1）、获取BeanFactory
 *          2）、查看容器是否有applicationEventMulticaster的组件
 *               有->this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class)
 *               没有->new SimpleApplicationEventMulticaster()
 *                    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
 *     9）、onRefresh() 子类自定义的方法
 *     10）、registerListeners()
 *          1）、获取或有ApplicationListener
 *          2）、getApplicationEventMulticaster().addApplicationListenerBean
 *     11）、finishBeanFactoryInitialization(beanFactory)
 *           ->beanFactory.preInstantiateSingletons()
 *              1）、获取所有bean
 *                  <--开始遍历-->
 *              2）、获取Bean的定义信息
 *              3)、判断Bean是否抽象的、单实例的、懒加载的
 *                  是-> 1）、判断是否是实现FactoryBean接口的Bean
     *                       不是->getBean(beanName)->doGetBean(name, null, null, false)
 *                                先从缓存中取 this.singletonObjects.get(beanName)
 *                                   取到 -> bean = 该值
 *                                   取不到 -> 开始创建bean
 *                                              1）、markBeanAsCreated(beanName)
 *                                              2）、mbd = getMergedLocalBeanDefinition(beanName)
 *                                              3）、mbd.getDependsOn() 获取当前Bean依赖的其他Bean 如果有->先把依赖的bean创建出来
 *                                              4）、createBean(beanName, mbd, args)
 *                                                  1）、实例化之前 Object bean = resolveBeforeInstantiation(beanName, mbdToUse)
 *                                                      ->hasInstantiationAwareBeanPostProcessors()
 *                                                      ->applyBeanPostProcessorsBeforeInstantiation
 *                                                      bp instanceof InstantiationAwareBeanPostProcessor -> Object bean = bp.postProcessBeforeInstantiation(beanClass, beanName)
 *                                                      bean !=null -> processor.postProcessAfterInitialization(result, beanName)
 *                                                      ！！ 如果返回值不为空 -> return该返回值作为Bean
 *                                                      -----------就是说 在实例化之前可以使用InstantiationAwareBeanPostProcessor-----------------------
 *                                                      -----------将本该正常实例化的bean 用其他bean代替------------------------------------------------
 *                                                      -----------例如AOP的实现便是通过这个processor 将bean对象转换为一个代理对象-------------------------
 *                                                  2）、如果上边没有返回，则执行 Object beanInstance = doCreateBean(beanName, mbdToUse, args)
 *                                                       利用工厂方法(@Bean标注实例化方法等)或者对象构造器创建Bean实例
 *                                                       -------------此时bean已经创建完成------------------
 *                                                  3）、applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName)
 *                                                       bp instanceof MergedBeanDefinitionPostProcessor -> bp.postProcessMergedBeanDefinition(mbd, beanType, beanName)
 *                                                  4）、 ------注意这里会出现依赖传递问题----------------
 *                                                  为bean赋值  populateBean(beanName, mbd, instanceWrapper) 初始化bean
 *                                                          1.赋值之前:addSingletonFactory
 *                                                            拿到processor 若bp instanceof InstantiationAwareBeanPostProcessor -> bp.postProcessAfterInstantiation(bw.getWrappedInstance(), beanName)
 *                                                            再一次找出该processor bp.postProcessPropertyValues
 *                                                          2.应用Bean属性的值 利用setter方法进行赋值
 *                                                            applyPropertyValues(beanName, mbd, bw, pvs)
 *                                                  5）、Bean初始化 initializeBean(beanName, exposedObject, mbd)
 *                                                          1.[执行Aware接口的方法]invokeAwareMethods
 *                                                             BeanNameAware、BeanClassLoaderAware、BeanFactoryAware
 *                                                          2.[在初始化之前执行后置处理器]   processor.postProcessBeforeInitialization(result, beanName)
 *                                                          3.[执行初始化方法] invokeInitMethods(beanName, wrappedBean, mbd)
 *                                                              1>判断是否是InitializingBean的实现，执行接口规定的初始化afterPropertiesSet
 *                                                              2>找出所有初始化方法(@Bean initMethod属性指定的方法) mbd.getInitMethodName() 对应去执行
 *                                                          4.[在初始化之后执行后置处理器]   processor.postProcessAfterInitialization(result, beanName)
 *                                                 6）、 [注册Bean的销毁方法] registerDisposableBeanIfNecessary(beanName, bean, mbd)--未细看
 *                                              5）、将创建的Bean添加到缓存中 this.singletonObjects.put(beanName, singletonObject)
 *                                                   所谓的ioc容器 就是这些Map
 *              4）、所有Bean都创建完成以后 检查所有Bean是SmartInitializingSingleton接口
 *                     是 -> smartSingleton.afterSingletonsInstantiated()[注解@EventListener的原理]
 *     12）、finishRefresh()
 *          1) 初始化和生命周期有关的后置处理器 initLifecycleProcessor()
 *              LifecycleProcessor:
 *                  void onRefresh();
 *                  void onClose();
 *          2) getLifecycleProcessor().onRefresh()
 *          3) publishEvent(new ContextRefreshedEvent(this))
 *          4） LiveBeansView.registerApplicationContext(this)
 *--------------------------------------------------------------------
 *                                总结
 *--------------------------------------------------------------------
 *1）、Spring容器在启动，先保存Bean定义信息
 *      1）、xml注册bean
 *      2）、注解注册Bean
 *2）、Spring容器会在XX的情况下创建Bean
 *      1）、用到这个bean的时候 去创建
 *      2）、finishBeanFactoryInitialization 统一创建bean
 *3）、后置处理器
 *      1）每一个Bean创建完成 会使用处理器处理
 *         AutowiredAnnotationBeanPostProcessor 处理自动注入
 *         XXXPostProcessor
 *4）、事件驱动模型
 *      ApplicationListener 事件监听
 *      ApplicationEventMulticaster 事件派发
 *
 */
@ComponentScan(value = "com.jesse.springlearning")
@Configuration
public class SpringConfig {

    /**
     * student类不写component的情况
     * 启动则将该bean放入容器
     *
     * @return
     */
    @Scope("singleton")
    @Bean("student")
    public Student student() {
        System.out.println("将singleton bean放入spring容器。。。");
        return new Student("jesse", 18);
    }

    /**
     * student类不写component的情况
     * 启动不将该bean放入容器 只有真正使用时才会开始放入spring 容器
     *
     * @return
     */
    @Scope("prototye")
    @Bean("studentPrototype")
    public Student studentPrototype() {
        System.out.println("将prototye bean放入spring容器。。。");
        return new Student("jesse", 18);
    }

    @Lazy
    @Bean("studentLazy")
    public Student studentLazy() {
        System.out.println("将lazy bean放入spring容器。。。");
        return new Student("jesse", 18);
    }


    @Conditional(LinuxCondition.class)
    @Bean("os")
    public Student student3() {
        return new Student("linus", 49);
    }

    @Conditional(WindowsCondition.class)
    @Bean("os")
    public Student student2() {
        return new Student("bill", 63);
    }

    @Bean
    public ColorDao colorDao2() {
        return new ColorDao("2");
    }

    @Bean
    public ColorService colorService2(@Qualifier("colorDao2") ColorDao ytr) {
        ColorService colorService = new ColorService();
        colorService.setColorDao2(ytr);
        return colorService;
    }
}
