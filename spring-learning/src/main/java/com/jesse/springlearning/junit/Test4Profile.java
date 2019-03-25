package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.Config4Profile;
import com.jesse.springlearning.config.SpringConfig;
import com.jesse.springlearning.service.ColorService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

public class Test4Profile {

    @Test
    public void Test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        final Object bean = context.getBean("colorService");
        ColorService colorService = (ColorService) bean;
        System.out.println(colorService.toString());
    }

    /**
     * 使用profile
     * 1. 使用api的profile
     * 2. jvm启动时 通过spring.profiles.active="test"
     */
    @Test
    public void Test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("test");
        context.register(Config4Profile.class);
        context.refresh();


        final String[] beanNamesForType = context.getBeanNamesForType(DataSource.class);
        Arrays.stream(beanNamesForType).forEach(s -> System.out.println(s));

    }
}
