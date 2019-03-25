package com.jesse.springlearning.po;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 初始化操作
 */
@Component
public class Student2 implements InitializingBean, DisposableBean {
    private String name;

    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student2() {
    }

    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 对象创建完成 并赋值好 开始调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("student2 afterPropertiesSet....");
    }

    /**
     * 对象销毁时 调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("student2 destroy....");
    }
}
