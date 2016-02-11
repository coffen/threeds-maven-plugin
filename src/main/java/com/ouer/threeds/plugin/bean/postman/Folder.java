package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 目录Bean，当前项目以请求路径的第一节作为目录名称
 * 例：/shop/info，目录名称即shop
 * <br>
 * File Name: Folder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:42:10 
 * @version: v1.0
 *
 */
public class Folder extends BaseBean {
	
	private Integer owner;	// 所有者
	private String lastUpdatedBy;	// 最后更新作者
	private Long lastRevision;		// 最后修订时间
	
	private List<String> order = new ArrayList<String>();	// 接口排列顺序

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Long getLastRevision() {
		return lastRevision;
	}

	public void setLastRevision(Long lastRevision) {
		this.lastRevision = lastRevision;
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}
	
}
