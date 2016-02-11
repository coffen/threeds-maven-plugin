package com.ouer.threeds.plugin.bean.postman;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 基类
 * <br>
 * File Name: BaseBean.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:32:36 
 * @version: v1.0
 *
 */
public class BaseBean {
	
	private String id;		// Id
	private String name;	// 名称
	private String description;	// 描述
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
