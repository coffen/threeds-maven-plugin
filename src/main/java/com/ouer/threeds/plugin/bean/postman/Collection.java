package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.List;

public class Collection extends BaseBean {
	
	private Integer owner = null;
	private Long timestamp = null;
	private String remoteLink = null;
	
	private List<String> order = new ArrayList<String>();
	
	private List<Folder> folders = new ArrayList<Folder>();
	private List<Request> requests = new ArrayList<Request>();	
	
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
