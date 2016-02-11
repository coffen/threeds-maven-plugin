package com.ouer.threeds.plugin.mojo.postman;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import com.ouer.threeds.plugin.MojoContext;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: Postman插件上下文
 * <br>
 * File Name: PostmanMojoContext.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:45:57 
 * @version: v1.0
 *
 */
public class PostmanMojoContext implements MojoContext {
	
	public Log log;	// 日志器
	
	public MavenProject mavenProject;	// mavenProject
	
	public ClassLoader projectLoader;	// maven类加载器
	
	public Map<String, Object> configs;	// 插件配置信息

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public MavenProject getMavenProject() {
		return mavenProject;
	}

	public void setMavenProject(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public ClassLoader getProjectLoader() {
		return projectLoader;
	}

	public void setProjectLoader(ClassLoader projectLoader) {
		this.projectLoader = projectLoader;
	}
	
	public Map<String, Object> getConfigs() {
		return configs;
	}
	
	public void setConfigs(Map<String, Object> configs) {
		this.configs = configs;
	}
	
	public Object getConfig(String key) {
		return configs == null ? null : configs.get(key);
	}

}
