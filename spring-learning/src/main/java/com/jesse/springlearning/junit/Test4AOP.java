package com.jesse.springlearning.junit;

import com.jesse.springlearning.aop.Math;
import com.jesse.springlearning.config.Config4AOP;
import com.jesse.springlearning.config.Config4Profile;
import com.jesse.springlearning.config.SpringConfig;
import com.jesse.springlearning.service.ColorService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

public class Test4AOP {

    @Test
    public void Test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4AOP.class);
        Object bean = context.getBean("math");
        Math math = (Math) bean;
        math.div(1, 0);
    }

}
