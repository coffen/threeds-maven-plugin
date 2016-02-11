package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.ClassUtil;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public class DefaultBuilder extends AbstractBuilder {

	@Override
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
			list = findBuilder("domain").build(clazz, null);
		}
		return list;
	}

	@Override
	public void register() {
		new MultipartFileBuilder().register();
		new PageableBuilder().register();
	}

}
