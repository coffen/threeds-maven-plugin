package com.ouer.threeds.plugin.mojo.postman;

import java.util.List;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public interface Builder {
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException;
	
}
