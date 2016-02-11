package com.ouer.threeds.plugin.mojo.postman;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: Springmvc模型，封装Springmvc的请求数据类型
 * <br>
 * File Name: SpringmvcModule.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:46:58 
 * @version: v1.0
 *
 */
public class SpringmvcModule {
	
	private final Class<?> clazz;
	
	private final boolean ignoreDeprecated;
	
	private final Map<String, Map<String, Class<?>>> requestMapping = new HashMap<String, Map<String, Class<?>>>();
	
	private final Map<String, String[]> duplicateRequest = new HashMap<String, String[]>();
	
	private final LocalVariableTableParameterNameDiscoverer paramDiscoverer = new LocalVariableTableParameterNameDiscoverer();
	
	private final List<Class<?>> excludeClazzList = Arrays.asList(HttpServletRequest.class, HttpServletResponse.class, Errors.class, ModelMap.class, Model.class, ExtendedModelMap.class);
	
	public SpringmvcModule(Class<?> clazz, boolean ignoreDeprecated) throws Exception {
		if (clazz == null) {
			throw new Exception();
		}
		this.clazz = clazz;
		this.ignoreDeprecated = ignoreDeprecated;
		
		init();
	}
	
	public Map<String, Map<String, Class<?>>> getRequestMappings() {
		return requestMapping;
	}
	
	public Map<String, String[]> getDuplicateRequest() {
		return duplicateRequest;
	}
	
	private void init() {
		List<String> basePath = getBaseRequestPath();
		String fullPath = null;
		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {
			if (!(m.isAnnotationPresent(RequestMapping.class))) {
				continue;
			}
			if (ignoreDeprecated && m.isAnnotationPresent(Deprecated.class)) {
				continue;
			}
			List<String> methodPath = getRequestPathFromMethod(basePath != null && basePath.size() > 0 ? basePath.get(0) : null, m);
			Map<String, Class<?>> paramMapping = getParameterFromMethod(m);
			if (methodPath.size() > 0) {
				fullPath = methodPath.get(0);
				duplicateRequest.put(fullPath, methodPath.toArray(new String[0]));
			}
			else {
				if (basePath.size() == 0) {
					continue;
				}
				else {
					fullPath = basePath.get(0);
					duplicateRequest.put(fullPath, basePath.toArray(new String[0]));
				}
			}
			requestMapping.put(fullPath, paramMapping);
		}
	}
	
	/**
	 * 解析Controller基本路径
	 * 
	 * @return
	 */
	private List<String> getBaseRequestPath() {
		List<String> list = new ArrayList<String>();
		if (clazz.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping rm = clazz.getAnnotation(RequestMapping.class);
			if (rm.value() != null && rm.value().length > 0) {
				String[] pathArr = rm.value();
				for (int i = 0; i < pathArr.length; i++) {
					String bPath = normalizeRequestPath(pathArr[i]);
					if (StringUtils.isNotBlank(bPath)) {
						list.add(bPath);
					}
				} 
			}
		}
		return list;
	}
	
	/**
	 * 解析接口方法的请求路径
	 * 
	 * @param method 待解析方法
	 * @return
	 */
	private List<String> getRequestPathFromMethod(String root, Method method) {
		String r = StringUtils.isBlank(root) ? "" : root.trim();
		List<String> list = new ArrayList<String>();
		if (method.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping rm = method.getAnnotation(RequestMapping.class);
			if (rm.value() != null && rm.value().length > 0) {
				String[] pathArr = rm.value();
				for (int i = 0; i < pathArr.length; i++) {
					String bPath = normalizeRequestPath(pathArr[i]);
					if (StringUtils.isNotBlank(bPath)) {
						list.add(r + bPath);
					}
				} 
			}
		}
		return list;
	}
	
	/**
	 * 解析接口方法的参数
	 * 
	 * @param method 待解析方法
	 * @return
	 */
	private Map<String, Class<?>> getParameterFromMethod(Method method) {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        String[] paramNames = paramDiscoverer.getParameterNames(method);
        Parameter[] paramObjs = method.getParameters();
        String param = null;
        outer:
        for (int i = 0; i < paramObjs.length; i++) {
        	for (Class<?> clazz : excludeClazzList) {
        		if (clazz.isAssignableFrom(paramObjs[i].getType())) {
        			continue outer;
        		}
        	}
        	param = paramNames[i];
        	if (paramObjs[i].isAnnotationPresent(RequestParam.class)) {
        		RequestParam rp = paramObjs[i].getAnnotation(RequestParam.class);
        		if (StringUtils.isNotBlank(rp.value())) {
        			param = rp.value();
        		}
        	}
        	map.put(param, paramObjs[i].getType());
        }
        return map;
	}
	
	/**
	 * Url规范化
	 * 
	 * @param path
	 * @return
	 */
	private String normalizeRequestPath(String path) {
		if (StringUtils.isBlank(path)) {
			return path;
		}
		path = path.replaceAll("(/)+", "/");
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if(path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		return path;
	}
	
}
