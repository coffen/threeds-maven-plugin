package com.ouer.threeds.plugin.mojo.postman.exception;

public class IncompatibleTypeException extends Exception {

	private static final long serialVersionUID = 5484865916899583821L;
	
	private String targetType; // 目标类型
	
	public String getTargetType() {
		return targetType;
	}
	
	public IncompatibleTypeException (String clazzName) {
		this.targetType = clazzName;
	}

}
