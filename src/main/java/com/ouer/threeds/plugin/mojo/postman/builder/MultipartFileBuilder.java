package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 用于构造MultipartFile的Data
 * <br>
 * File Name: MultipartFileBuilder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:26:17 
 * @version: v1.0
 *
 */
public class MultipartFileBuilder extends AbstractBuilder {
	
	private Pattern arrayIndexRegExp = Pattern.compile("\\[\\d+\\]$");
	
	public void register() {
		builderMap.put("MultipartFile", this);
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (!MultipartFile.class.isAssignableFrom(clazz) || StringUtils.isBlank(key)) {
			throw new IncompatibleTypeException(clazz.getName());
		}
		String expr = trimArrayIndex(key);	// Multipart数组不采用param[0]这种方式，去掉数组索引
		Data data = new Data(expr);
		data.setType(Data.DATA_TYPE_FILE);
		return Arrays.asList(new Data[] { data });
	}
	
	public String trimArrayIndex(String key) {
		Matcher m = arrayIndexRegExp.matcher(key);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		return sb.toString();
	}
	
}
