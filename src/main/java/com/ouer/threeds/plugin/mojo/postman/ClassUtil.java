package com.ouer.threeds.plugin.mojo.postman;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
