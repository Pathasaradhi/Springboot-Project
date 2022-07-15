package com.weshopify.ecom.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfigTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UsersDao usersDao = context.getBean(UsersDao.class);
		System.out.println(usersDao.getAllUserNames());
		for(String beanName:context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}
}
