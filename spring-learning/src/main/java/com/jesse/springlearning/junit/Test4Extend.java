package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.Config4Extend;
import com.jesse.springlearning.config.Config4Transaction;
import com.jesse.springlearning.service.TxService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4Extend {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4Extend.class);

    @Test
    public void Test01() {

    }
}
