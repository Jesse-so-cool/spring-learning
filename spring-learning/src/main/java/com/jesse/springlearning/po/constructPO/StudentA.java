package com.jesse.springlearning.po.constructPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StudentA {

    @Autowired
    private StudentB studentB ;

    public StudentB getStudentB() {
        return studentB;
    }

    public void setStudentB(StudentB studentB) {
        this.studentB = studentB;
    }

    public StudentA() {
    }

    public StudentA(StudentB studentB) {
        this.studentB = studentB;
    }

    @com.jesse.invoketime.annotation.Time
    public void say(){
        System.out.println("what!");
    }
}
