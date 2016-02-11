package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 用于构造Pageable的Data，包括page、size、sort三个参数
 * <br>
 * File Name: PageableBuilder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:29:18 
 * @version: v1.0
 *
 */
public class PageableBuilder extends AbstractBuilder {
	
	public void register() {
		builderMap.put("Pageable", this);
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (!Pageable.class.isAssignableFrom(clazz)) {
			throw new IncompatibleTypeException(clazz.getName());
		}
		return Arrays.asList(new Data[] { new Data("page", "1"), new Data("size", "10"), new Data("sort") });
	}

}
