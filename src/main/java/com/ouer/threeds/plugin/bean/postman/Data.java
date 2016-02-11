package com.ouer.threeds.plugin.bean.postman;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 接口参数Bean
 * <br>
 * File Name: Data.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:39:26 
 * @version: v1.0
 *
 */
public class Data {
	
	public final static String DATA_TYPE_TEXT = "text";
	public final static String DATA_TYPE_FILE = "file";
	
	private String key;			// 参数名称
	private String value;		// 参数值
	private String type;		// 参数类型，分为两种类型：file（上传文件）和text（文本域）
	private boolean enabled;	// 是否有效
	
	public Data(String key) {
		this.key = key;
		this.type = DATA_TYPE_TEXT;
		this.enabled = true;
	}
	
	public Data(String key, String value) {
		this(key);		
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
