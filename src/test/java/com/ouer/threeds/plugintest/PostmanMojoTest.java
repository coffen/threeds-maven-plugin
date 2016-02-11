package com.ouer.threeds.plugintest;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.ouer.threeds.plugin.mojo.postman.PostmanMojo;

public class PostmanMojoTest {

	@Rule
	public MojoRule rule = new MojoRule();

	@Rule
	public TestResources resources = new TestResources();

	@Test
	public void testBosProject() throws Exception {
		File projectCopy = this.resources.getBasedir("");
		File pom = new File(projectCopy, "pom.xml");
		Assert.assertNotNull(pom);
		Assert.assertTrue(pom.exists());
		
		PostmanMojo mojo = (PostmanMojo)rule.lookupMojo("postman", pom);		
		
		mojo.execute();
	}

}
