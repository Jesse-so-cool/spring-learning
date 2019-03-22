package com.jesse.springlearning.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("utilComponent")
@Scope("prototype")
public class UtilComponent {
    public void hello(){
        System.out.println("hello");
    }
}
