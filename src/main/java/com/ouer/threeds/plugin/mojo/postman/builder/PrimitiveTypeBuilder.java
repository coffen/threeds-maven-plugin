package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.Arrays;
import java.util.List;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public class PrimitiveTypeBuilder extends AbstractBuilder {
	
	public final static String ALIAS = "primitive";
	
	public void register() {
		// do nothing
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (String.class.isAssignableFrom(clazz)) {
			return buildString(key);
		}
		else if (Byte.class.isAssignableFrom(clazz) || "byte".equals(clazz.getSimpleName())) {
			return buildByte(key);
		}
		else if (Short.class.isAssignableFrom(clazz) || "short".equals(clazz.getSimpleName())) {
			return buildShort(key);
		}
		else if (Character.class.isAssignableFrom(clazz) || "char".equals(clazz.getSimpleName())) {
			return buildChar(key);
		}		
		else if (Integer.class.isAssignableFrom(clazz) || "int".equals(clazz.getSimpleName())) {
			return buildInteger(key);
		}
		else if (Long.class.isAssignableFrom(clazz) || "long".equals(clazz.getSimpleName())) {
			return buildLong(key);
		}
		else if (Float.class.isAssignableFrom(clazz) || "float".equals(clazz.getSimpleName())) {
			return buildFloat(key);
		}
		else if (Double.class.isAssignableFrom(clazz) || "double".equals(clazz.getSimpleName())) {
			return buildDouble(key);
		}
		else if (Boolean.class.isAssignableFrom(clazz) || "boolean".equals(clazz.getSimpleName())) {
			return buildBoolean(key);
		}
		else {
			throw new IncompatibleTypeException(clazz.getName());
		}
	}
	
	private List<Data> buildString(String key) {
		return Arrays.asList(new Data[] { new Data(key, "text") });
	}
	
	private List<Data> buildByte(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0") });
	}
	
	private List<Data> buildShort(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0") });
	}
	
	private List<Data> buildChar(String key) {
		return Arrays.asList(new Data[] { new Data(key, "a") });
	}
	
	private List<Data> buildInteger(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0") });
	}
	
	private List<Data> buildLong(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0") });
	}
	
	private List<Data> buildFloat(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0.0") });
	}
	
	private List<Data> buildDouble(String key) {
		return Arrays.asList(new Data[] { new Data(key, "0.0") });
	}
	
	private List<Data> buildBoolean(String key) {
		return Arrays.asList(new Data[] { new Data(key, "true") });
	}
	
}