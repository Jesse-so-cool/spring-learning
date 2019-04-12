package com.jesse.springlearning.junit;

import com.jesse.springlearning.config.Config4Extend;
import com.jesse.springlearning.config.Config4Listener;
import com.jesse.springlearning.po.Student;
import com.jesse.springlearning.po.constructPO.StudentA;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class Test4Listener {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config4Listener.class);

    @Test
    public void Test01() {
        final Object studentA = context.getBean("studentA");
        final StudentA studentA1 = (StudentA) studentA;
        studentA1.say();
        //context.close();
    }
}
