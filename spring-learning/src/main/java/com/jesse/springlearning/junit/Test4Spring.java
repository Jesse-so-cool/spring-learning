package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.SpringConfig;
import com.jesse.springlearning.dao.ColorDao;
import com.jesse.springlearning.service.ColorService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4Spring {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    public void Test01() {
        final Object bean = context.getBean("colorService");
        ColorService colorService = (ColorService) bean;
        System.out.println(colorService.toString());
    }

    @Test
    public void Test02() {
        Object bean = context.getBean("colorService2");
        ColorService colorService = (ColorService) bean;
        System.out.println(colorService.toString());

        //Object bean1 = context.getBean("colorService");
        //ColorService colorService1 = (ColorService)bean1;
        //System.out.println(colorService1.toString());

        Object colorDao2 = context.getBean("colorDao2");
        ColorDao colorDao21 = (ColorDao) colorDao2;
        System.out.println(colorDao21.toString());
    }
}
