package com.jesse.springlearning.aop;


import com.jesse.invoketime.annotation.Time;

//@Time
public class Math {

    @Time
    public int div(int x, int y) {
        System.out.println("方法正在执行");
        return x / y;
    }
}
