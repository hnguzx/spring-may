package pers.guzx.common.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: WuMing
 * @CreateDate: 2020/9/8 15:56
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
@Log4j2
@Component
public class GetBeanUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public static <T>T getSpringEntry(String beanId,Class<T>clazz){
		T cast = clazz.cast(getSpringEntry(beanId));
		return cast;
	}
	public static <T>T getSpringEntry(Class<T>clazz){
		T bean = applicationContext.getBean(clazz);
		return bean;
	}
	
	public static Object getSpringEntry(String name){
		Object bean = applicationContext.getBean(name);
		return bean;
		
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		GetBeanUtils.applicationContext = applicationContext;
	}
}
