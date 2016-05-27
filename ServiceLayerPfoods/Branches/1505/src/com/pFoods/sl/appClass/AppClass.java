package com.pFoods.sl.appClass;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public enum AppClass {

	INSTANCE;
	
	private AbstractApplicationContext applicationContext;
	
	public AbstractApplicationContext getInstance(){
		return applicationContext;
	}
	
	
	public AbstractApplicationContext getAppContext(){
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		applicationContext.registerShutdownHook();
		return applicationContext;		
	}
}
