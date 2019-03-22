package com.jesse.springlearning;

import com.jesse.springlearning.component.UtilComponent;
import com.jesse.springlearning.po.setterPO.StudentA;
import com.jesse.springlearning.util.ApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class SpringLearningApplication {

	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringApplication.run(SpringLearningApplication.class, args);
        UtilComponent utilComponent = (UtilComponent) (ApplicationContextProvider.getBean("utilComponent"));
        utilComponent.hello();
	}

}
