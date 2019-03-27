package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.Config4Extend;
import com.jesse.springlearning.config.Config4Listener;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class Test4Listener {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4Listener.class);

    @Test
    public void Test01() {
            context.close();
    }
}
