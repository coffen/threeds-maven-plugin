package com.ouer.threeds.plugin.bean.postman;

import java.util.ArrayList;
import java.util.List;

public class Folder extends BaseBean {
	
	private Integer owner;
	private String lastUpdatedBy;
	private Long lastRevision;
	
	private List<String> order = new ArrayList<String>();

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
