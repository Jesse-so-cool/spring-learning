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
 *          6）、postProcessBeanFactory(beanFactory) BeanFactory准备工作完成后 后置处理
 *              空方法 子类自定义属性设置的方法
 *          7）、invokeBeanFactoryPostProcessors(beanFactory) 执行BeanFactoryPostProcessors
 *              BeanFactoryPostProcessors: BeanFactory后置处理器
 *                  BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessorl两个接口
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
