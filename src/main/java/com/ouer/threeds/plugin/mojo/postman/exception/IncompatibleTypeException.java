package com.ouer.threeds.plugin.mojo.postman.exception;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 不相符的Class异常
 * <br>
 * File Name: IncompatibleTypeException.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:33:06 
 * @version: v1.0
 *
 */
public class IncompatibleTypeException extends Exception {

	private static final long serialVersionUID = 5484865916899583821L;
	
	private String targetType; // 目标类名
	
	public String getTargetType() {
		return targetType;
	}
	
	public IncompatibleTypeException (String clazzName) {
		this.targetType = clazzName;
	}

}
