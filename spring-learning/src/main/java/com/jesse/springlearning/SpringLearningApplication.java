package com.jesse.springlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringLearningApplication {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringApplication.run(SpringLearningApplication.class, args);
        //UtilComponent utilComponent = (UtilComponent) (ApplicationContextProvider.getBean("utilComponent"));
        //utilComponent.hello();
	}

}
