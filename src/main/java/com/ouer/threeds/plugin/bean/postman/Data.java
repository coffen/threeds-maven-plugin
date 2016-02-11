package com.ouer.threeds.plugin.bean.postman;

public class Data {
	
	public final static String DATA_TYPE_TEXT = "text";
	public final static String DATA_TYPE_FILE = "file";
	
	private String key;
	private String value;
	private String type;
	private boolean enabled;
	
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
