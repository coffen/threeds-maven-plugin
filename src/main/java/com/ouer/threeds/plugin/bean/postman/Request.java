package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 接口Bean
 * <br>
 * File Name: Request.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:45:55 
 * @version: v1.0
 *
 */
public class Request extends BaseBean {
	
	public final static String REQUEST_METHOD_GET = "GET";
	public final static String REQUEST_METHOD_POST = "POST";
	
	public final static String REQUEST_DATAMODE_FORM_DATA = "params";
	public final static String REQUEST_DATAMODE_X_WWW_FORM_URLENCODED = "urlencoded";
	public final static String REQUEST_DATAMODE_RAW = "raw";
	public final static String REQUEST_DATAMODE_BINARY = "binary";
	
	public final static String REQUEST_CURRENTHELPER = "normal";
	
	private String collectionId;		// 集合Id
	private String folder;				// 目录Id
	
	private String currentHelper;		// ?
	private String dataMode;			// 请求数据类型
	private String descriptionFormat;	// 描述格式
	private String headers;				// 请求头（未处理）
	private String method;				// 请求方式
	private String preRequestScript;	// 预处理脚本（Javascript）
	private String rawModeData;			// 未经处理数据
	private String test;				// 测试脚本（Javascript）
	private Long time;					// 创建时间
	private String url;					// 请求路径
	private String version;				// 版本

	private Object helperAttributes;	// ?
	private List<Data> data = new ArrayList<Data>();							// 参数
	private List<Response> responses = new ArrayList<Response>();				// 应答
	private Map<String, String> pathVariables = new HashMap<String, String>();	// 动态参数
	
	public String getCollectionId() {
		return collectionId;
	}
	
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getCurrentHelper() {
		return currentHelper;
	}

	public void setCurrentHelper(String currentHelper) {
		this.currentHelper = currentHelper;
	}

	public String getDataMode() {
		return dataMode;
	}

	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

	public String getDescriptionFormat() {
		return descriptionFormat;
	}

	public void setDescriptionFormat(String descriptionFormat) {
		this.descriptionFormat = descriptionFormat;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public Map<String, String> getPathVariables() {
		return pathVariables;
	}
	
	public void setPathVariables(Map<String, String> pathVariables) {
		this.pathVariables = pathVariables;
	}

	public String getPreRequestScript() {
		return preRequestScript;
	}

	public void setPreRequestScript(String preRequestScript) {
		this.preRequestScript = preRequestScript;
	}

	public String getRawModeData() {
		return rawModeData;
	}

	public void setRawModeData(String rawModeData) {
		this.rawModeData = rawModeData;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Object getHelperAttributes() {
		return helperAttributes;
	}

	public void setHelperAttributes(Object helperAttributes) {
		this.helperAttributes = helperAttributes;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

}
