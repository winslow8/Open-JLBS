package com.lanx.app.lbs.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.lanx.app.lbs.util.LBSConstants.Domain;

/**
 * <p>Java反射的工具类</p>
 * 
 * @author Ramboo
 * @date 2013-09-23
 * */
public final class ReflectUtils {
	private static final Log logger = LogFactory.getLog(ReflectUtils.class);
	
	/**
	 * 将泛型形参给出的类中设置的属性值转换为Map形式的键值对
	 * t一般是pojo类
	 * 
	 * @param params
	 * @param t
	 */
	public static <T extends Object> void flushParams(Map<String, Object> params,T t) {
		if(params == null || t == null)
			return;
		
		Class<?> clazz = t.getClass() ;
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields() ;
				
				for (int j = 0; j < fields.length; j++) { // 遍历所有属性
					String name = fields[j].getName(); // 获取属性的名字
					Object value = null;
					
					if(logger.isDebugEnabled())
						logger.debug(ReflectUtils.class + "method flushParams attribute name:" + name + "  ");
						
					if(!Domain.serialVersionUID.equals(name)){
						Method method = t.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
						value = method.invoke(t);

						if(logger.isDebugEnabled())
							logger.debug(ReflectUtils.class + "attribute value:" + value);
						
						if(value != null)
							params.put(name, value);
					}
				}
			} catch (Exception e) {
				//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
				if(logger.isDebugEnabled())
					logger.debug(ReflectUtils.class + " Exception here !");				
			} 	
		}
	}
	
	/**
	 * 将Map形式的键值对中的值转换为泛型形参给出的类中的属性值
	 * t一般代表pojo类
	 * 
	 * @param t
	 * @param params
	 */
	public static <T extends Object> void flushObject(T t, Map<String, Object> params) {
		if(params == null || t == null)
			return;
		
		Class<?> clazz = t.getClass() ;
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields() ;
				
				for(int i = 0 ; i< fields.length;i++){
					String name = fields[i].getName(); // 获取属性的名字
					
					if(logger.isDebugEnabled())
						logger.debug(ReflectUtils.class + "method flushObject attribute name:" + name + "  ");
					
					Object value = params.get(name);
					if(value != null && !"".equals(value)){
						//注意下面这句，不设置true的话，不能修改private类型变量的值
						fields[i].setAccessible(true);
						fields[i].set(t, value);
					}
				}
			}catch(Exception e){}
		}
	}	
}
