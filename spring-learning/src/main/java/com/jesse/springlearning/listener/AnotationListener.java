package com.jesse.springlearning.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnotationListener {

    /**
     * 监听所有
     */
    @EventListener(classes = {ApplicationEvent.class})
    public void listen01(ApplicationEvent event){
        System.out.println("AnotationListener....listen01 "+event);
    }

    /**
     * 监听ContextClosedEvent事件
     */
    @EventListener(classes = {ContextClosedEvent.class})
    public void listen02(ApplicationEvent event){
        System.out.println("AnotationListener....listen02 "+event);
    }
}
