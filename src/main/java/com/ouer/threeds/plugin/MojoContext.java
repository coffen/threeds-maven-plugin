package com.ouer.threeds.plugin;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 插件上下文接口
 * <br>
 * File Name: MojoContext.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:47:53 
 * @version: v1.0
 *
 */
public interface MojoContext {
	
	public Log getLog();
	
	public MavenProject getMavenProject();
	
	public ClassLoader getProjectLoader();
	
	public Map<String, Object> getConfigs();
	
	public Object getConfig(String key);
	
}
