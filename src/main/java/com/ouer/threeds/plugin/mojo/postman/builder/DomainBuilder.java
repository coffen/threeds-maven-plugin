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
 * Project Name: 买到手抽筋
 * <br>
 * Description: 域对象类构造器
 * <br>
 * File Name: DomainBuilder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:16:12 
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
		
		// 首先找寻此类是否有特定的构造器（以类缩写名注册），如果查找不到再使用Domain构造器
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
	
	/**
	 * 递归调用构造类变量的Data，每进入下一级应当以当前key值作为下一级的基本路径
	 * <br>
	 * 例：Class A包含B变量（变量名b），Class B包含C变量（变量名c），如果A的key为a，则c的key为a.b.c
	 * 
	 * @param field 类变量
	 * @param key	当前层级的key值
	 * @return
	 * @throws IncompatibleTypeException
	 */
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
