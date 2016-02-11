package com.ouer.threeds.plugintest;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.ouer.threeds.plugin.mojo.postman.PostmanMojo;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: Postman插件测试类
 * <br>
 * File Name: PostmanMojoTest.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:49:35 
 * @version: v1.0
 *
 */
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
