package com.ouer.threeds.plugin;

import java.io.File;
import java.io.FilenameFilter;

import org.codehaus.plexus.util.StringUtils;

public class ResourceFilter implements FilenameFilter {
	
	private String type;
	
	public ResourceFilter(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public boolean accept(File dir, String name) {
		return StringUtils.isBlank(name) || StringUtils.isBlank(type) ? false : name.toLowerCase().endsWith("." + type.toLowerCase());
	}

}
