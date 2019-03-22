package com.jesse.springlearning.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 观察者模式
 */
@Component
public class TestListener  implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("监听器1 onApplicationEvent: "+event.getMsg());
    }
}
