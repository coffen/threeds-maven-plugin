package com.ouer.threeds.plugin.mojo.postman;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: Class工具类
 * <br>
 * File Name: ClassUtil.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:34:30 
 * @version: v1.0
 *
 */
public class ClassUtil {
	
	public static boolean isPrimitiveType(Class<?> clazz) {
		if (clazz.isPrimitive()) {
			return true;
		}
		return Byte.class.isAssignableFrom(clazz)
			|| Character.class.isAssignableFrom(clazz)
			|| Short.class.isAssignableFrom(clazz)
			|| Integer.class.isAssignableFrom(clazz)
			|| Long.class.isAssignableFrom(clazz)
			|| Float.class.isAssignableFrom(clazz)
			|| Double.class.isAssignableFrom(clazz)
			|| Boolean.class.isAssignableFrom(clazz)
			|| String.class.isAssignableFrom(clazz);			
	}
	
	public static Type[] getGenericTypeFromField(Field field) {
		if (field == null) {
			return null;
		}
		Type genericType = field.getGenericType();
		if (genericType != null && genericType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)genericType;
			return pt.getActualTypeArguments();
		}
		return null;
	}
	
}
