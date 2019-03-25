package com.jesse.springlearning.config;

import com.jesse.springlearning.condition.LinuxCondition;
import com.jesse.springlearning.condition.WindowsCondition;
import com.jesse.springlearning.po.Student;
import org.springframework.context.annotation.*;

/**
 * 取代xml 成为配置类
 */
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

}
