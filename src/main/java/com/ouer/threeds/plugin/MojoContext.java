package com.ouer.threeds.plugin;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public interface MojoContext {
	
	public Log getLog();
	
	public MavenProject getMavenProject();
	
	public ClassLoader getProjectLoader();
	
	public Map<String, Object> getConfigs();
	
	public Object getConfig(String key);
	
}
