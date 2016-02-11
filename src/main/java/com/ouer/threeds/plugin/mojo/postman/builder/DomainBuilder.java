package com.ouer.threeds.plugin.mojo.postman.builder;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.Builder;
import com.ouer.threeds.plugin.mojo.postman.ClassUtil;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

/**
 * <p>
 * Project Name: 火鸟淘宝开放服务模拟系统
 * <br>
 * Description: 域模型实例生产器
 * <br>
 * File Name: DomainBuilder.java
 * <br>
 * Copyright: Copyright (C) 2014 All Rights Reserved.
 * <br>
 * Company: 杭州清柳科技有限公司
 * <br>
 * @author 李可夫
 * @create time：2014-11-17 23:38:26
 * @version: v1.0
 * 
 */
public class DomainBuilder extends AbstractBuilder {
	
	public final static String ALIAS = "domain";
	
	public void register() {
		// do nothing		
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (clazz == null) {
			throw new IncompatibleTypeException("null");
		}
		
		if (clazz.isEnum()) {
			return findBuilder("enum").build(clazz, key);
		}
		
		Builder designated = findBuilder(clazz.getSimpleName());
		if (designated != null) {
			return designated.build(clazz, key);
		}
		
		List<Data> dataList = new ArrayList<Data>();
		String keyExpr = normalizeRequestKey(key);
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor pd : pds) {
				Method method = pd.getWriteMethod();
				Field field = FieldUtils.getField(clazz, pd.getName(), true);
				if (method == null || field == null) {
					continue;
				}

				dataList.addAll(buildField(field, keyExpr + pd.getName()));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	private List<Data> buildField(Field field, String key) throws IncompatibleTypeException {
		List<Data> list = new ArrayList<Data>();
		Class<?> type = field.getType();
		if (ClassUtil.isPrimitiveType(type)) {
			list.addAll(findBuilder("primitive").build(type, key));
		}
		else if (type.isArray() || List.class.isAssignableFrom(type)) {
			Class<?> figure = null;
			if (type.isArray()) {
				figure = type.getComponentType();
			}
			else {
				Type[] typeArr = ClassUtil.getGenericTypeFromField(field);
				if (typeArr != null && typeArr.length > 0) {
					figure = (Class<?>)typeArr[0];
				}
			}
			if (figure != null) {
				list.addAll(findBuilder("array").build(figure, key));
			}
		}
		else {
			list.addAll(build(type, key));
		}
		return list;
	}
	
	/**
	 * Key键值规范化（增加“.”）
	 * 
	 * @param key
	 * @return
	 */
	private String normalizeRequestKey(String key) {
		return StringUtils.isBlank(key) ? "" : key + ".";
	}
	
}
