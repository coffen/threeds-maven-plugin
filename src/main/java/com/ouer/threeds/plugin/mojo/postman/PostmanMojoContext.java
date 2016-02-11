package com.ouer.threeds.plugin.mojo.postman;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import com.ouer.threeds.plugin.MojoContext;

public class PostmanMojoContext implements MojoContext {
	
	public Log log;
	
	public MavenProject mavenProject;
	
	public ClassLoader projectLoader;
	
	public Map<String, Object> configs;

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
