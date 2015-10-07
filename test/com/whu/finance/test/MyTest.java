package com.whu.finance.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hold.book.service.StockIndustryService;

public class MyTest {
	private Logger logger=Logger.getLogger(MyTest.class);
	@Test
	public void stockindustryTest(){
		ApplicationContext context=new ClassPathXmlApplicationContext("file:WebContent/WEB-INF/applicationContext.xml");
		StockIndustryService stockIndustryService=(StockIndustryService) context.getBean("stockIndustryService");
		stockIndustryService.start();
	}

}
