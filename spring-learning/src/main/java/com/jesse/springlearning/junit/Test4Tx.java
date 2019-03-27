package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.Config4Transaction;
import com.jesse.springlearning.config.SpringConfig;
import com.jesse.springlearning.service.ColorService;
import com.jesse.springlearning.service.TxService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4Tx {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4Transaction.class);

    @Test
    public void Test01() {
        final Object bean = context.getBean("txService");
        TxService colorService = (TxService) bean;
        colorService.insertUser();

        //手动抛异常
        int i = 2/0;
    }
}
