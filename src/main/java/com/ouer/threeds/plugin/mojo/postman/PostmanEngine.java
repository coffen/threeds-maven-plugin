package com.ouer.threeds.plugin.mojo.postman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.plexus.util.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.ouer.threeds.plugin.MojoContext;
import com.ouer.threeds.plugin.bean.postman.Collection;
import com.ouer.threeds.plugin.bean.postman.Folder;
import com.ouer.threeds.plugin.bean.postman.Request;
import com.ouer.threeds.plugin.mojo.postman.builder.DefaultBuilder;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: Postman脚本构造引擎
 * <br>
 * File Name: PostmanEngine.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:34:56 
 * @version: v1.0
 *
 */
public class PostmanEngine {
	
	private PostmanMojoContext mojoContext;
	
	private DefaultBuilder builder;
	
	public PostmanEngine(PostmanMojoContext context) {
		this.mojoContext = context;
		
		initBuilder(mojoContext);
	}
	
	public Collection build(MojoContext ctx, List<String> clazzList) {
		if (ctx == null || clazzList == null || clazzList.size() == 0) {
			return null;
		}
		
		Map<String, Map<String, Class<?>>> requestMapping = new HashMap<String, Map<String, Class<?>>>();
		Map<String, String[]> duplicateRequest = new HashMap<String, String[]>();
		accumulate(clazzList, requestMapping, duplicateRequest);
		
		Collection c = buildCollection();
		
		Map<String, Folder> folderMap = buildFolders(duplicateRequest);
		c.getFolders().addAll(folderMap.values());
		
		buildRequests(requestMapping, duplicateRequest, c, folderMap);
		
		return c;
	}
	
	// 初始化构造器
	private void initBuilder(MojoContext ctx) {
		if (ctx == null) {
			return;
		}
		builder = new DefaultBuilder();
		builder.register();
		
		Object value = mojoContext.getConfig("analogLength");
		Integer analogLength = value == null ? 3 : (Integer)value;
		builder.setArrayLength(analogLength);
		
	}
	
	/**
	 * 加载指定包名内实现Controller或RestController的类，解析获取请求及其参数列表
	 * 
	 * @param list				接口类列表
	 * @param requestMapping	请求集合
	 * @param duplicateRequest	多路径的请求集合（部分接口方法有多个请求路径）
	 */
	private void accumulate(List<String> list, Map<String, Map<String, Class<?>>> requestMapping, Map<String, String[]> duplicateRequest) {
		if (list == null || list.size() == 0 || requestMapping == null || duplicateRequest == null) {
			return;
		}
		ClassLoader classLoader = mojoContext.getProjectLoader();
		Object config = mojoContext.getConfig("ignoreDeprecated");
		Boolean ignoreDeprecated = config != null ? (Boolean)config : true;
		for (String clazz : list) {
			try {
				Class<?> c = classLoader.loadClass(clazz);
				if (ignoreDeprecated && c.isAnnotationPresent(Deprecated.class)) {
					continue;
				}
				if (c.isAnnotationPresent(Controller.class) || c.isAnnotationPresent(RestController.class)) {
					SpringmvcModule module = new SpringmvcModule(c, ignoreDeprecated);
					requestMapping.putAll(module.getRequestMappings());
					duplicateRequest.putAll(module.getDuplicateRequest());
				}
			}
			catch (Exception e) {
				mojoContext.getLog().error("Class解析错误：" + clazz, e);
			}
		}
	}
	
	// 创建集合
	private Collection buildCollection() {
		Collection collection = new Collection();
		collection.setId(UUID.randomUUID().toString());
		collection.setName(mojoContext.getMavenProject().getName());
		collection.setDescription(mojoContext.getMavenProject().getDescription());
		collection.setTimestamp(new DateTime().getMillis());
		collection.setRemoteLink("");
		collection.setOwner(0);
		return collection;
	}
	
	// 创建目录，以请求路径的第一节（即“/”符号分隔的多节）作为目录名称
	private Map<String, Folder> buildFolders(Map<String, String[]> duplicateRequest) {
		Map<String, Folder> folders = new HashMap<String, Folder>();
		Object needFolder = mojoContext.getConfig("needFolder");
		if (needFolder != null && !(Boolean)needFolder) {
			return folders;
		}
		Iterator<String> it = duplicateRequest.keySet().iterator();
		while(it.hasNext()) {
			String url = getFirstLayer(it.next());
			if (StringUtils.isNotBlank(url)) {
				Folder f = new Folder();
				f.setId(UUID.randomUUID().toString());
				f.setName(url);
				f.setDescription("");
				f.setOwner(0);
				folders.put(url, f);
			}
		}
		return folders;
	}
	
	// 创建请求
	private void buildRequests(Map<String, Map<String, Class<?>>> requestMapping, Map<String, String[]> duplicateRequest, Collection collection, Map<String, Folder> folders) {
		if (collection == null || requestMapping == null || requestMapping.size() == 0) {
			return;
		}
		Object value = mojoContext.getConfig("contextPath");
		String contextPath = value == null ? "localhost" : (String)value;
		for (String path : requestMapping.keySet()) {
			if (StringUtils.isBlank(path)) {
				continue;
			}
			Request r = new Request();
			r.setId(UUID.randomUUID().toString());
			r.setCollectionId(collection.getId());
			r.setName(path);
			r.setMethod(Request.REQUEST_METHOD_POST);
			r.setHeaders("");
			r.setDataMode(Request.REQUEST_DATAMODE_FORM_DATA);
			r.setPreRequestScript("");
			r.setUrl(contextPath + path);
			r.setTime(new DateTime().getMillis());
			r.setVersion(mojoContext.getMavenProject().getVersion());
			r.setCurrentHelper(Request.REQUEST_CURRENTHELPER);
			
			String[] duplicated = duplicateRequest.get(path);
			r.setDescription("Same Handler: " + (duplicated == null || duplicated.length < 2 ? "" : Arrays.toString(duplicated)));
			
			String url = getFirstLayer(path);
			if (folders != null && folders.containsKey(url)) {
				r.setFolder(folders.get(url).getId());
				folders.get(url).getOrder().add(r.getId());
			}
			else {
				collection.getOrder().add(r.getId());
			}
			
			buildDatas(requestMapping.get(path), r);
			
			collection.getRequests().add(r);
		}
	}
	
	// 构造请求参数
	private void buildDatas(Map<String, Class<?>> paramsMapping, Request request) {
		if (request == null || paramsMapping == null || paramsMapping.size() == 0) {
			return;
		}
		for (String paramName : paramsMapping.keySet()) {
			Class<?> clazz = paramsMapping.get(paramName);
			try {
				request.getData().addAll(builder.build(clazz, paramName));
			}
			catch (Exception e) {
				mojoContext.getLog().error("参数类型处理失败：" + request.getUrl() + "[" + paramName +"]", e);
			}
		}
	}
	
	private String getFirstLayer(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		String str = url;
		if (str.startsWith("/")) {
			str = str.substring(1);
		}
		String[] arr = str.split("/");
		if (arr.length > 1 && StringUtils.isNotBlank(arr[0]) && StringUtils.isNotBlank(arr[1])) {
			return arr[0];
		}
		return null;
	}

}
