package com.lanx.app.lbs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;

import com.lanx.app.lbs.core.domain.LBSLocation;

public class ReflectFieldTest {
	public static void testReflect(Object model) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		Field[] field = getDeclaredFields(model);//model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			String name = field[j].getName(); // 获取属性的名字

			System.out.print("attribute name:" + name + "  ");
			
			Type clazzType = field[j].getGenericType();
			String type = clazzType.toString(); // 获取属性的类型
			if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
				String value = (String) m.invoke(model); // 调用getter方法获取属性值
				//if (value != null) {
					System.out.println("attribute value:" + value);
				//}
			}
			
			if (type.equals("class java.lang.Integer")) {
				Method m = model.getClass().getMethod( "get" + name.substring(0, 1).toUpperCase() + name.substring(1));
				Integer value = (Integer) m.invoke(model);
				//if (value != null) {
					System.out.println("attribute value:" + value);
				//}
			}
			
			if (type.equals("class java.lang.Short")) {
				Method m = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
				Short value = (Short) m.invoke(model);
				//if (value != null) {
					System.out.println("attribute value:" + value);
				//}
			}
			if (type.equals("class java.lang.Double") || type.equals("double")) {
				Method m = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase()+ name.substring(1));
				Double value = (Double) m.invoke(model);
				//if (value != null) {
					System.out.println("attribute value:" + value);
				//}
			}
			
			if (type.equals("class java.lang.Boolean")) {
				Method m = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase()+ name.substring(1));
				Boolean value = (Boolean) m.invoke(model);
				//if (value != null) {
					System.out.println("attribute value:" + value);
				//}
			}
			if (type.equals("class java.util.Date")) {
				Method m = model.getClass().getMethod("get" + name.substring(0, 1).toUpperCase()+ name.substring(1));
				Date value = (Date) m.invoke(model);
				//if (value != null) {
					System.out.println("attribute value:"+ (value != null ? value.toLocaleString():null));
				//}
			}
		}
	}

	//added by ramboo myself
	public static Field[] getDeclaredFields(Object object){
		Class<?> clazz = object.getClass() ;
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				Field field[] = clazz.getDeclaredFields() ;
				return field ;
			} catch (Exception e) {
			//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
			//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
			
			} 			
		}
		
		return null;
	}	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LBSLocation lbsCoreBean = new LBSLocation();
		lbsCoreBean.setId(1000);
		lbsCoreBean.setUpdatetime(new Date());
		
		lbsCoreBean.setPid("891220A");
		lbsCoreBean.setCitycode("010");
		
		try {
			testReflect(lbsCoreBean);
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
