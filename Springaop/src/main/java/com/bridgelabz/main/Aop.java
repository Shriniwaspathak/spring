package com.bridgelabz.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgelabz.service.ShapeService;

public class Aop {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService=context.getBean("shapeService",ShapeService.class);
		System.out.println(shapeService.getCircle().getName());

	}


}
