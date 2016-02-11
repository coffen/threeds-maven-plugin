package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 接口集合Bean
 * <br>
 * File Name: Collection.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:34:14 
 * @version: v1.0
 *
 */
public class Collection extends BaseBean {
	
	private Integer owner = null;		// 所有者
	private Long timestamp = null;		// 创建时间
	private String remoteLink = null;	// ?
	
	private List<String> order = new ArrayList<String>();	// 顺序，放置不属于任何Folder的Request的Id
	
	private List<Folder> folders = new ArrayList<Folder>();	// 目录
	private List<Request> requests = new ArrayList<Request>();	// 请求接口
	
	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemoteLink() {
		return remoteLink;
	}
	
	public void setRemoteLink(String remoteLink) {
		this.remoteLink = remoteLink;
	}
	
	public List<String> getOrder() {
		return order;
	}
	
	public void setOrder(List<String> order) {
		this.order = order;
	}
	
	public List<Folder> getFolders() {
		return folders;
	}
	
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	
	public List<Request> getRequests() {
		return requests;
	}
	
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}	
	
}
