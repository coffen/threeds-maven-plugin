package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.ClassUtil;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 默认构造器，接口方法的直属参数在设置参数名时需注意基本类型（数组）类型和自定义参数类是不同的
 * 基本类型、数组、枚举都是以直属参数名作为Data的key值，而自定义参数类是以类变量名作为key值的
 * <br>
 * 例：public Result<Shop> shopInfo(@RequestParam String[] nicks, 
 * 			@RequestParam("files") MultipartFile[] files, TestParam testParam, Pageable pageable, 
 * 			HttpServletRequest request, HttpServletResponse response) throws Exception
 * <br>
 * nicks对应的Data { key : nicks, ... }
 * files对应的Data { key : files, ... }
 * testParam对应的Data { key : id, ... } { key : nick, ... } ...
 * <br>
 * File Name: DefaultBuilder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:04:44 
 * @version: v1.0
 *
 */
public class DefaultBuilder extends AbstractBuilder {

	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		List<Data> list = new ArrayList<Data>();
		if (ClassUtil.isPrimitiveType(clazz)) {
			list = findBuilder("primitive").build(clazz, key);
		}
		else if (clazz.isArray()) {
			Class<?> cType = clazz.getComponentType();
			if (ClassUtil.isPrimitiveType(cType) || MultipartFile.class.isAssignableFrom(cType)) {
				list = findBuilder("array").build(cType, key);
			}
		}
		else if (clazz.isEnum()) {
			list = findBuilder("enum").build(clazz, key);
		}
		else {
			if (MultipartFile.class.isAssignableFrom(clazz)) {
				list = findBuilder("MultipartFile").build(clazz, key);
			}
			else {
				list = findBuilder("domain").build(clazz, null);
			}
		}
		return list;
	}

	/**
	 * 注册基本构造器以外的自定义构造器，目前通过硬编码完成注册
	 */
	public void register() {
		new MultipartFileBuilder().register();
		new PageableBuilder().register();
	}

}
