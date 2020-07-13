package com.meipiao.ctrip.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * 用于实体对象之间同属性数据赋值
 * 
 * 
 * 
 * @author Administrator
 *
 */
@Slf4j
public class CopyUtil {
	/**
	 * 用于实体对象之间同属性数据赋值 (数据源和目标对象必须要有get和set方法)
	 * @param model 数据源
	 * @param model2 目标对象
	 */
	public static void util(Object model, Object model2) {
		// 获取所有属性
		Field[] field = model.getClass().getDeclaredFields();
		Field[] field2 = model2.getClass().getDeclaredFields();
		try {
			for (int j = 0; j < field.length; j++) { // model遍历属性
				String name = field[j].getName(); // 获取model属性名
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将model属性名首字母大写
				String type = field[j].getGenericType().toString(); // 获取model属性类型
				for (int i = 0; i < field2.length; i++) {
					String name2 = field2[i].getName(); // 获取model2属性名
					name2 = name2.substring(0, 1).toUpperCase() + name2.substring(1); // 将model属性名首字母大写
					String type2 = field2[i].getGenericType().toString(); // 获取model2属性类型
					if (name2.equals(name)) {
						if (type.equals("class java.lang.String") && type.equals(type2)) { // 判断类型
							Method m = model.getClass().getMethod("get" + name);// 获取getter方法
							String value = (String) m.invoke(model);// 用getter方法为model属性取值
							
							if (value != null) {
								Method m2 = model2.getClass().getMethod("set" + name, String.class);// 获取setter方法
								m2.invoke(model2, value);// 用setter方法为属性赋值
							}
						}
						// integer 类型的判断    
						if (type.equals("class java.lang.Integer") && type.equals(type2)) {
							Method m = model.getClass().getMethod("get" + name);
							Integer value = (Integer) m.invoke(model);
							
							if (value != null) {
								Method m2 = model2.getClass().getMethod("set" + name, Integer.class);
								m2.invoke(model2, value);
							}
						}
						//Double类型的判断   以及double型和BigDecimal之间的相互转换
						if (type.equals("class java.lang.Double") && type.equals(type2)
								|| type.equals("class java.math.BigDecimal") && type.equals(type2)
								|| type.equals("class java.lang.Double") && type2.equals("class java.math.BigDecimal")
								|| type2.equals("class java.lang.Double")
										&& type.equals("class java.math.BigDecimal")) {
							Method m = model.getClass().getMethod("get" + name);
							if (type.equals("class java.lang.Double") && type2.equals("class java.math.BigDecimal")) {
								Double value = (Double) m.invoke(model);
								Method m2 = model2.getClass().getMethod("set" + name, BigDecimal.class);
								if (value != null) {
									BigDecimal value2 = BigDecimal.valueOf(value);
									m2.invoke(model2, value2);
								}

							} else if (type2.equals("class java.lang.Double")
									&& type.equals("class java.math.BigDecimal")) {
								BigDecimal value = (BigDecimal) m.invoke(model);
								Method m2 = model2.getClass().getMethod("set" + name, Double.class);
								if (value != null) {
									Double value2 = value.doubleValue();
									m2.invoke(model2, value2);
								} 

							} else if(type2.equals("class java.lang.Double")&&type.equals(type2)){
								Double value = (Double) m.invoke(model);
								Method m2 = model2.getClass().getMethod("set" + name, Double.class);
								if (value != null) {
									m2.invoke(model2, value);
								} 
							}else{
								BigDecimal value = (BigDecimal) m.invoke(model);
								Method m2 = model2.getClass().getMethod("set" + name, BigDecimal.class);
								if (value != null) {
									m2.invoke(model2, value);
								}
							}
						}
						//布尔类型
						if (type.equals("class java.lang.Boolean") && type.equals(type2)) {
							Method m = model.getClass().getMethod("get" + name);
							Boolean value = (Boolean) m.invoke(model);
							
							if (value != null) {
								Method m2 = model2.getClass().getMethod("set" + name, Boolean.class);
								m2.invoke(model2, value);
							}
						}
						//Date型
						if (type.equals("class java.util.Date") && type.equals(type2)) {
							Method m = model.getClass().getMethod("get" + name);
							Date value = (Date) m.invoke(model);
							
							if (value != null) {
								Method m2 = model2.getClass().getMethod("set" + name, Date.class);
								m2.invoke(model2, value);
							}
						}
						//Long 类型的转换
						if (type.equals("class java.lang.Long") && type.equals(type2)) {
							Method m = model.getClass().getMethod("get" + name);
							Long value = (Long) m.invoke(model);
							
							if (value != null) {
								Method m2 = model2.getClass().getMethod("set" + name, Long.class);
								m2.invoke(model2, value);
							}
						}
						//long 类型和String类型的转换
						if (type.equals("class java.lang.Long") && type2.equals("class java.lang.String")) {
							Method m = model.getClass().getMethod("get" + name);
							Long value = (Long) m.invoke(model);
							
							if (value != null) {
								String vlaue2=value.toString();
								Method m2 = model2.getClass().getMethod("set" + name, String.class);
								m2.invoke(model2, vlaue2);
							}
						}
						//String 装换成Long类型
						if (type.equals("class java.lang.String") && type2.equals("class java.lang.Long")) {
							Method m = model.getClass().getMethod("get" + name);
							String value = (String) m.invoke(model);
							
							if (value != null) {
								try {
									Long vlaue2=Long.valueOf(value);
									Method m2 = model2.getClass().getMethod("set" + name, Long.class);
									m2.invoke(model2, vlaue2);
								} catch (Exception e) {
									log.error("异常:" + e);
								}
							}
						}
						
						//Integer 装换成String类型
						if (type.equals("class java.lang.Integer") && type2.equals("class java.lang.String")) {
							Method m = model.getClass().getMethod("get" + name);
							Integer value = (Integer) m.invoke(model);
							
							if (value != null) {
								String vlaue2=value.toString();
								Method m2 = model2.getClass().getMethod("set" + name, String.class);
								m2.invoke(model2, vlaue2);
							}
						}
						//String 装换成Integer类型
						if (type.equals("class java.lang.String") && type2.equals("class java.lang.Integer")) {
							Method m = model.getClass().getMethod("get" + name);
							String value = (String) m.invoke(model);
							
							if (value != null) {
								try {
									Integer vlaue2=Integer.valueOf(value);
									Method m2 = model2.getClass().getMethod("set" + name, Integer.class);
									m2.invoke(model2, vlaue2);
								} catch (Exception e) {
									log.error("异常:" + e);
								}
							}
						}
						//可以自己根据需要添加属性判断
						
						
					}

				}

			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
