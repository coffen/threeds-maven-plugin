package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

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
