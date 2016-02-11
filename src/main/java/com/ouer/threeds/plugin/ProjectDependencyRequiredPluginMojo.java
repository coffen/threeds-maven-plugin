package com.ouer.threeds.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 需要依赖项目运行的插件抽象类
 * <br>
 * File Name: ProjectDependencyRequiredPluginMojo.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:48:35 
 * @version: v1.0
 *
 */
public abstract class ProjectDependencyRequiredPluginMojo extends AbstractMojo {
	
	@Parameter(defaultValue = "${project}", required = true)
	protected MavenProject project;
	
	protected ClassLoader projectLoader;
	
	protected void buildClassLoader() throws MojoExecutionException {
		if (projectLoader == null) {
			try {
				List<String> classpathElements = project.getCompileClasspathElements();
				classpathElements.add(project.getBuild().getOutputDirectory());
				classpathElements.add(project.getBuild().getTestOutputDirectory());
				URL urls[] = new URL[classpathElements.size()];

				for (int i = 0; i < classpathElements.size(); ++i) {
					urls[i] = new File((String) classpathElements.get(i)).toURI().toURL();
				}
				projectLoader = new URLClassLoader(urls, getClass().getClassLoader());
			} 
			catch (Exception e) {
				throw new MojoExecutionException("Couldn't create a project classloader.", e);
			}
		}
	}
	
}
