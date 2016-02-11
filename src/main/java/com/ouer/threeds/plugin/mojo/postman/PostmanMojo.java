package com.ouer.threeds.plugin.mojo.postman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ouer.threeds.plugin.ProjectDependencyRequiredPluginMojo;
import com.ouer.threeds.plugin.ResourceFilter;
import com.ouer.threeds.plugin.bean.postman.Collection;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 用于生成基于Springmvc（3.0以上）的postman测试脚本
 * <br>
 * File Name: PostmanMojo.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:46:32 
 * @version: v1.0
 *
 */
@Mojo(name = "postman", requiresProject = true, requiresDependencyResolution = ResolutionScope.RUNTIME)
@Execute(phase = LifecyclePhase.TEST)
public class PostmanMojo extends ProjectDependencyRequiredPluginMojo {
	
	public final static Pattern PKG_REGEX = Pattern.compile("[a-zA-Z_]\\w*(\\.[a-zA-Z_]\\w*)*");
	
	@Parameter(property = "pkg", required = true)
	private String pkg;	// 接口类包名
	
	@Parameter(property = "contextPath", required = true)
	private String contextPath;	// 测试服务URL，默认：localhost
	
	@Parameter(property = "needFolder", defaultValue = "true")
	private Boolean needFolder;	// 是否构建目录，默认：是
	
	@Parameter(property = "ignoreDeprecated", defaultValue = "true")
	private Boolean ignoreDeprecated;	// 是否忽略有@Deprecated注解的类或方法，默认：是
	
	@Parameter(property = "analogLength", defaultValue = "3")
	private Integer analogLength;		// 数组模拟次数，默认：3
	
	private static ResourceFilter clazzFilter = new ResourceFilter("class");
		
	public String getPkg() {
		return pkg;
	}
	
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	
	public String getContextPath() {
		return contextPath;
	}
	
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Boolean getNeedFolder() {
		return needFolder;
	}
	
	public void setNeedFolder(Boolean needFolder) {
		this.needFolder = needFolder;
	}
	
	public Boolean getIgnoreDeprecated() {
		return ignoreDeprecated;
	}
	
	public void setIgnoreDeprecated(Boolean ignoreDeprecated) {
		this.ignoreDeprecated = ignoreDeprecated;
	}
	
	public Integer getAnalogLength() {
		return analogLength;
	}
	
	public void setAnalogLength(Integer analogLength) {
		this.analogLength = analogLength;
	}

	public void execute() throws MojoExecutionException, MojoFailureException {
		buildClassLoader();
		
		getLog().info("开始构建Postman脚本: " + project.getName() + "/" + pkg);

		List<String> clazzList = scanApiPackage();
		PostmanEngine engine = new PostmanEngine(buildMojoContext());
		try {
			Collection c = engine.build(buildMojoContext(), clazzList);
			File output = new File(project.getBuild().getDirectory() + "/" + c.getName() + ".json.postman_collection");
			FileWriter writer = new FileWriter(output);
			JSON.writeJSONStringTo(c, writer, SerializerFeature.PrettyFormat);
			getLog().info("构建Postman脚本成功");
		}
		catch (IOException e) {
			getLog().info("构建Postman脚本失败", e);
		}
	}
	
	/**
	 * 扫描资源文件
	 * 
	 * @return
	 */
	private List<String> scanApiPackage() {
		if (StringUtils.isBlank(pkg)) {
			getLog().info("错误：传入参数pkg为空，请检查");
			return null;
		}
		if (!PKG_REGEX.matcher(pkg).matches()) {
			getLog().error("错误：不是有效的pkg参数");
			return null;
		}
		if (StringUtils.isBlank(contextPath)) {
			getLog().error("错误：服务地址为空");
			return null;
		}
		String apiPath = pkg.replace(".", "/");
		try {
			Enumeration<URL> resourceUrls = projectLoader.getResources(apiPath);
			List<String> result = new ArrayList<String>();
			while(resourceUrls.hasMoreElements()) {
				result.addAll(scanClassFileSystem(pkg, new File(resourceUrls.nextElement().toURI())));
			}
			return result;
		}
		catch (Exception e) {
			getLog().error("错误：Api路径访问失败");
		}		
		return null;
	}
	
	/**
	 * 扫描文件系统
	 * 
	 * @return
	 */
	private List<String> scanClassFileSystem(String pkg, File file) {
		List<String> list = new ArrayList<String>();
		if (file != null) {
			try {
				if (file.exists() && file.canRead()) {
					if (file.isDirectory()) {
						File[] subFiles = file.listFiles(clazzFilter);
						for (File sub : subFiles) {
							list.addAll(scanClassFileSystem(pkg, sub));
						}
					}
					else {
						String fname = file.getName();
						if (clazzFilter.accept(file, fname)) {
							list.add(pkg + "." + fname.substring(0, fname.length() - 6));
						}
					}
				}
			}
			catch (Exception e) {
				getLog().error("错误：" + file.getAbsolutePath() + " 访问失败");
			}
		}
		return list;
	}
	
	/**
	 * 组装参数
	 */
	public PostmanMojoContext buildMojoContext() {
		PostmanMojoContext context = new PostmanMojoContext();
		context.setMavenProject(project);
		context.setProjectLoader(projectLoader);
		context.setLog(getLog());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pkg", pkg);
		map.put("contextPath", contextPath);
		map.put("needFolder", needFolder);
		map.put("ignoreDeprecated", ignoreDeprecated);
		map.put("analogLength", analogLength);
		context.setConfigs(map);
		
		return context;
	}

}
