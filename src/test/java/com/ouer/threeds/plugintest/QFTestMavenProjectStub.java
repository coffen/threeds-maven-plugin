package com.ouer.threeds.plugintest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
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

import com.ouer.threeds.plugin.ResourceFilter;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 测试用MavenProject
 * <br>
 * File Name: QFTestMavenProjectStub.java
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
public class QFTestMavenProjectStub extends MavenProjectStub {

	private Build build;

    public QFTestMavenProjectStub() {
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
    	String libFile = getBuild().getDirectory() + "\\" + getModel().getArtifactId() + "-" + (getModel().getVersion() == null ? getModel().getParent().getVersion() : getModel().getVersion()) + "\\WEB-INF\\lib";
    	File f = new File(libFile);
    	if (f != null && f.exists()) {
    		File[] libs = f.listFiles(new ResourceFilter("jar"));
    		if (libs != null && libs.length > 0) {
    			for (File lib : libs) {
        			addCompileSourceRoot(lib.getAbsolutePath());
    			}
    		}
    	}
    	
    	
    	// addCompileSourceRoot(new File(testRoot, "src/main/java").getAbsolutePath());
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
		repo.setUrl("http://nexus.ixiaopu.com:8081/nexus/content/groups/public-c2c/");

		return Collections.singletonList(repo);
	}

	public Properties getProperties() {
		return getModel().getProperties();
	}

	public Reporting getReporting() {
		return getModel().getReporting();
	}

	public String getProjetPath() {
		return "E:\\Git\\qf-restapi\\qf-restapi-product";
	}

}
