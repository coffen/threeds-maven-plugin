package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request extends BaseBean {
	
	public final static String REQUEST_METHOD_GET = "GET";
	public final static String REQUEST_METHOD_POST = "POST";
	
	public final static String REQUEST_DATAMODE_FORM_DATA = "params";
	public final static String REQUEST_DATAMODE_X_WWW_FORM_URLENCODED = "urlencoded";
	public final static String REQUEST_DATAMODE_RAW = "raw";
	public final static String REQUEST_DATAMODE_BINARY = "binary";
	
	public final static String REQUEST_CURRENTHELPER = "normal";
	
	private String collectionId;
	private String folder;
	
	private String currentHelper;
	private String dataMode;	
	private String descriptionFormat;
	private String headers;
	private String method;	
	private String preRequestScript;
	private String rawModeData;
	private String test;
	private Long time;
	private String url;
	private String version;

	private Object helperAttributes;
	private List<Data> data = new ArrayList<Data>();
	private List<Response> responses = new ArrayList<Response>();
	private Map<String, String> pathVariables = new HashMap<String, String>();
	
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
