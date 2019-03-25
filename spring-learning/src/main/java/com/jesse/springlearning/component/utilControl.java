package com.jesse.springlearning.component;

import com.jesse.springlearning.listener.TestEvent;
import com.jesse.springlearning.po.Student;
import com.jesse.springlearning.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class utilControl {

    @Autowired
    private UtilComponent utilComponent;

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @RequestMapping(value = "/hello")
    public String test(){
        UtilComponent utilComponent = (UtilComponent) (applicationContextProvider.getBean("utilComponent"));
        utilComponent.hello();
        this.utilComponent.hello();

        /**
         * 观察者模式
         * 广播后 订阅了这个事件的监听器都会被执行
         */
        ApplicationContextProvider.getApplicationContext().publishEvent(new TestEvent(this, "test"));

        return "hello";
    }

    @RequestMapping(value = "/bean/{name}")
    public String getBean(@PathVariable String name) {
        Object bean = ApplicationContextProvider.getBean(name);
        Student a = (Student) bean;
        return a.toString();
    }

    @RequestMapping(value = "/allBean")
    public String getBeans() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("-------所有bean---------");
        Arrays.stream(beanDefinitionNames).forEach(s -> System.out.println(s));
        return beanDefinitionNames.toString();
    }
}
