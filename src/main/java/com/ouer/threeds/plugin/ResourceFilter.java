package com.ouer.threeds.plugin;

import java.io.File;
import java.io.FilenameFilter;

import org.codehaus.plexus.util.StringUtils;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 文件过滤器
 * <br>
 * File Name: ResourceFilter.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:48:47 
 * @version: v1.0
 *
 */
public class ResourceFilter implements FilenameFilter {
	
	private String type;
	
	public ResourceFilter(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public boolean accept(File dir, String name) {
		if (StringUtils.isBlank(name)) {
			return true;
		}
		if (new File(dir, name).isDirectory()) {
			return true;
		}
		return StringUtils.isBlank(type) ? false : name.toLowerCase().endsWith("." + type.toLowerCase());
	}

}
