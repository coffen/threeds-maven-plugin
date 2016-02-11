package com.ouer.threeds.plugin.mojo.postman.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public class EnumBuilder extends AbstractBuilder {
	
	public final static String ALIAS = "enum";
	
	@Override
	public void register() {
		// do nothing
	}

	@Override
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (clazz == null || !clazz.isEnum()) {
			throw new IncompatibleTypeException("null");
		}
		
		List<Data> dataList = new ArrayList<Data>();
		try {
			Field[] fs = clazz.getDeclaredFields();
			for (Field f : fs) {
				if (f.getType() == clazz) {
					dataList.add(new Data(key, f.getName()));
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataList;
	}

}
