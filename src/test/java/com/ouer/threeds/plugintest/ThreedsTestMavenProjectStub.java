package com.ouer.threeds.plugintest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Reporting;
import org.apache.maven.model.Repository;
import org.apache.maven.model.Resource;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 测试用MavenProject
 * <br>
 * File Name: ThreedsTestMavenProjectStub.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:50:09 
 * @version: v1.0
 *
 */
public class ThreedsTestMavenProjectStub extends MavenProjectStub {

	private Build build;

    public ThreedsTestMavenProjectStub() {
        File testProjectDir = new File(getProjetPath() + "/");

        MavenXpp3Reader pomReader = new MavenXpp3Reader();
        Model model;

        try {
            File pomFile = new File(testProjectDir, "pom.xml");
            model = pomReader.read(new InputStreamReader(new FileInputStream(pomFile), "UTF-8"));
            setModel(model);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        setGroupId(model.getGroupId());
        setArtifactId(model.getArtifactId());
        setVersion(model.getVersion());
        setName(model.getName());
        setUrl(model.getUrl());
        setPackaging(model.getPackaging());

        build = new Build();
        Resource resource = new Resource();

        build.setFinalName(model.getArtifactId());
        build.setDirectory(getBasedir().getAbsolutePath() + "/target");
        
        build.setSourceDirectory(testProjectDir + "/src/main/java");
        resource.setDirectory(testProjectDir + "/src/main/resources");
        build.setResources(Collections.singletonList(resource));
        build.setOutputDirectory(getBasedir().getAbsolutePath() + "/target/classes");

        build.setTestSourceDirectory(testProjectDir + "/src/test/java");
        resource = new Resource();
        resource.setDirectory(testProjectDir + "/src/test/resources");
        build.setTestResources(Collections.singletonList(resource));
        build.setTestOutputDirectory(getBasedir().getAbsolutePath() + "/target/test-classes");

        setBuild(build);
        
        buildCompileSourceRoot();

        Reporting reporting = new Reporting();

        reporting.setOutputDirectory(getBasedir().getAbsolutePath() + "/target/site");

        getModel().setReporting(reporting);
    }
    
    public void buildCompileSourceRoot() {
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-common\\1.0.0\\threeds-common-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\mybatis\\mybatis\\3.2.6\\mybatis-3.2.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ixiaopu\\qiniu-sdk\\1.0.0\\qiniu-sdk-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-web\\4.0.3.RELEASE\\spring-web-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\json\\org.json\\chargebee-1.0\\org.json-chargebee-1.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\commons\\commons-lang3\\3.3.2\\commons-lang3-3.3.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\javax\\validation\\validation-api\\1.1.0.Final\\validation-api-1.1.0.Final.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-baichuan\\1.0.0\\threeds-baichuan-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\com.taobao.sdk\\1.0\\com.taobao.sdk-1.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-dal\\1.0.0\\threeds-dal-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\alibaba\\druid\\1.0.15\\druid-1.0.15.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\mybatis\\mybatis-spring\\1.2.2\\mybatis-spring-1.2.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-jdbc\\4.0.3.RELEASE\\spring-jdbc-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\data\\spring-data-commons\\1.7.1.RELEASE\\spring-data-commons-1.7.1.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\mysql\\mysql-connector-java\\5.1.30\\mysql-connector-java-5.1.30.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-message\\1.0.0\\threeds-message-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\alibaba\\dubbo\\2.8.4\\dubbo-2.8.4.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\io\\netty\\netty\\3.7.0.Final\\netty-3.7.0.Final.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-pool\\commons-pool\\1.6\\commons-pool-1.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\curator\\curator-framework\\2.5.0\\curator-framework-2.5.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\curator\\curator-client\\2.5.0\\curator-client-2.5.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\redis\\clients\\jedis\\2.2.0\\jedis-2.2.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-dto\\1.0.0\\threeds-dto-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-weixin-model\\1.0.0\\threeds-weixin-model-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\me\\chanjar\\weixin-java-mp\\1.3.2\\weixin-java-mp-1.3.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\me\\chanjar\\weixin-java-common\\1.3.2\\weixin-java-common-1.3.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\thoughtworks\\xstream\\xstream\\1.4.7\\xstream-1.4.7.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\httpcomponents\\fluent-hc\\4.2\\fluent-hc-4.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\three\\three-file-model\\1.0\\three-file-model-1.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-push-model\\1.0.0\\threeds-push-model-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\xiaomi\\push\\MiPush_SDK_Server_2_2_15\\2.0\\MiPush_SDK_Server_2_2_15-2.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-service\\1.0.0\\threeds-service-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-push-api\\1.0.0\\threeds-push-api-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\three\\three-file-api\\1.0\\three-file-api-1.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-weixin-api\\1.0.0\\threeds-weixin-api-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-context-support\\4.0.3.RELEASE\\spring-context-support-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\qf\\xpay-java-sdk\\1.0.0\\xpay-java-sdk-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\google\\code\\gson\\gson\\2.3.1\\gson-2.3.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\zookeeper\\zookeeper\\3.4.3\\zookeeper-3.4.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\jline\\jline\\0.9.94\\jline-0.9.94.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\jboss\\netty\\netty\\3.2.2.Final\\netty-3.2.2.Final.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\101tec\\zkclient\\0.5\\zkclient-0.5.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\ouer\\threeds\\threeds-utils\\1.0.0\\threeds-utils-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-core\\4.0.3.RELEASE\\spring-core-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\javax\\servlet\\javax.servlet-api\\3.0.1\\javax.servlet-api-3.0.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\hibernate\\hibernate-validator\\5.1.0.Final\\hibernate-validator-5.1.0.Final.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\jboss\\logging\\jboss-logging\\3.1.3.GA\\jboss-logging-3.1.3.GA.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\fasterxml\\classmate\\1.0.0\\classmate-1.0.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\javax\\el\\el-api\\2.2\\el-api-2.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\glassfish\\web\\el-impl\\2.2\\el-impl-2.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\aspectj\\aspectjrt\\1.7.4\\aspectjrt-1.7.4.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\aspectj\\aspectjweaver\\1.8.3\\aspectjweaver-1.8.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-aop\\4.0.3.RELEASE\\spring-aop-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\aopalliance\\aopalliance\\1.0\\aopalliance-1.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-beans\\4.0.3.RELEASE\\spring-beans-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\security\\spring-security-core\\3.2.3.RELEASE\\spring-security-core-3.2.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-expression\\4.0.3.RELEASE\\spring-expression-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\security\\spring-security-config\\3.2.3.RELEASE\\spring-security-config-3.2.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\security\\spring-security-web\\3.2.3.RELEASE\\spring-security-web-3.2.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-context\\4.0.3.RELEASE\\spring-context-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-tx\\4.0.3.RELEASE\\spring-tx-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\springframework\\spring-webmvc\\4.0.3.RELEASE\\spring-webmvc-4.0.3.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\thymeleaf\\thymeleaf\\2.1.2.RELEASE\\thymeleaf-2.1.2.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\ognl\\ognl\\3.0.6\\ognl-3.0.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\thymeleaf\\thymeleaf-spring4\\2.1.2.RELEASE\\thymeleaf-spring4-2.1.2.RELEASE.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\nz\\net\\ultraq\\thymeleaf\\thymeleaf-layout-dialect\\1.2.3\\thymeleaf-layout-dialect-1.2.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\alibaba\\fastjson\\1.2.6\\fastjson-1.2.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.3.3\\jackson-annotations-2.3.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.3.3\\jackson-core-2.3.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.3.3\\jackson-databind-2.3.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-lang\\commons-lang\\2.3\\commons-lang-2.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-collections\\commons-collections\\3.2\\commons-collections-3.2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-beanutils\\commons-beanutils\\1.7.0\\commons-beanutils-1.7.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-validator\\commons-validator\\1.3.1\\commons-validator-1.3.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-digester\\commons-digester\\1.6\\commons-digester-1.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\xml-apis\\xml-apis\\1.0.b2\\xml-apis-1.0.b2.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-codec\\commons-codec\\1.6\\commons-codec-1.6.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-io\\commons-io\\2.4\\commons-io-2.4.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-fileupload\\commons-fileupload\\1.3.1\\commons-fileupload-1.3.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\log4j\\log4j\\1.2.12\\log4j-1.2.12.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\google\\guava\\guava\\18.0\\guava-18.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\slf4j\\jcl-over-slf4j\\1.7.5\\jcl-over-slf4j-1.7.5.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\joda-time\\joda-time\\2.3\\joda-time-2.3.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-servlets\\8.0.1.v20110908\\jetty-servlets-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-continuation\\8.0.1.v20110908\\jetty-continuation-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-client\\8.0.1.v20110908\\jetty-client-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-http\\8.0.1.v20110908\\jetty-http-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-io\\8.0.1.v20110908\\jetty-io-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\eclipse\\jetty\\jetty-util\\8.0.1.v20110908\\jetty-util-8.0.1.v20110908.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\javassist\\javassist\\3.20.0-GA\\javassist-3.20.0-GA.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\slf4j\\slf4j-api\\1.7.5\\slf4j-api-1.7.5.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\slf4j\\log4j-over-slf4j\\1.7.5\\log4j-over-slf4j-1.7.5.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\ch\\qos\\logback\\logback-core\\1.0.13\\logback-core-1.0.13.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\commons\\commons-collections4\\4.0\\commons-collections4-4.0.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\com\\caucho\\hessian\\4.0.38\\hessian-4.0.38.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\commons-httpclient\\commons-httpclient\\3.1\\commons-httpclient-3.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\httpcomponents\\httpmime\\4.4.1\\httpmime-4.4.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\httpcomponents\\httpclient\\4.4.1\\httpclient-4.4.1.jar");
		addCompileSourceRoot("E:\\Maven\\repository\\org\\apache\\httpcomponents\\httpcore\\4.4\\httpcore-4.4.jar");
    }

	public Build getBuild() {
		return build;
	}

	public File getBasedir() {
		File basedir = new File(getProjetPath() + "/");

		if (!basedir.exists()) {
			basedir.mkdirs();
		}

		return basedir;
	}

	public List<String> getCompileSourceRoots() {
		File src = new File(getProjetPath() + "src/main/java");
		return Collections.singletonList(src.getAbsolutePath());
	}

	public List<String> getTestCompileSourceRoots() {
		File test = new File(getProjetPath() + "src/test/java");
		return Collections.singletonList(test.getAbsolutePath());
	}

	public List<Artifact> getCompileArtifacts() {
		Artifact junit = new DefaultArtifact("junit", "junit", VersionRange.createFromVersion("4.12"), Artifact.SCOPE_TEST, "jar", null, new DefaultArtifactHandler("jar"), false);
		junit.setFile(new File("junit/junit/4.12/junit-4.12.jar"));

		return Collections.singletonList(junit);
	}

	public List<Artifact> getTestArtifacts() {
		Artifact junit = new DefaultArtifact("junit", "junit", VersionRange.createFromVersion("4.12"), Artifact.SCOPE_TEST, "jar", null, new DefaultArtifactHandler("jar"), false);
		junit.setFile(new File("junit/junit/4.12/junit-4.12.jar"));

		return Collections.singletonList(junit);
	}

	public List<Repository> getRepositories() {
		Repository repo = new Repository();
		repo.setId("central");
		repo.setName("central");
		repo.setUrl("http://115.236.11.98:8081/nexus/content/groups/public");

		return Collections.singletonList(repo);
	}

	public Properties getProperties() {
		return getModel().getProperties();
	}

	public Reporting getReporting() {
		return getModel().getReporting();
	}

	public String getProjetPath() {
		return "E:\\Git\\threeds-server\\bos";
	}

}
