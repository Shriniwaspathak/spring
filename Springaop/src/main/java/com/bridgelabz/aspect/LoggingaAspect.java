package com.bridgelabz.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

	
	@Aspect
	public class LoggingaAspect {
		
		@Before("execution(public String getName())")
		public void LoggingAdvice()
		{
			System.out.println("Advice run,Get Method Called");
		}


}

