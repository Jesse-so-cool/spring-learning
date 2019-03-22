package com.jesse.springlearning.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener2 implements ApplicationListener<TestEvent2> {
    @Override
    public void onApplicationEvent(TestEvent2 event) {
        System.out.println("监听器2 onApplicationEvent: "+event.getMsg());
    }
}
