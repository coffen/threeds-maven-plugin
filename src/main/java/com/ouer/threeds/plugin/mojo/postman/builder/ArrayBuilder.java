package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.ClassUtil;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public class ArrayBuilder extends AbstractBuilder {
	
	public final static String ALIAS = "array";
	
	protected static int DEFAULT_SIMULATE_COUNT = 3;
	
	private int simulateCount = DEFAULT_SIMULATE_COUNT;
	
	public void register() {
		// do nothing		
	}
	
	protected void setSimulateCount(int count) {
		simulateCount = count < 1 ? DEFAULT_SIMULATE_COUNT : count;
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (clazz == null || StringUtils.isBlank(key)) {
			throw new IncompatibleTypeException("null");
		}
		if (ClassUtil.isPrimitiveType(clazz)) {
			return buildPrimitiveArray(clazz, key);
		}
		else {
			List<Data> list = new ArrayList<Data>();
			for (int i = 0; i < simulateCount; i++) {
				list.addAll(findBuilder("domain").build(clazz, buildArrayIndex(key, i)));
			}
			return list;
		}
	}
	
	private List<Data> buildPrimitiveArray(Class<?> clazz, String key) throws IncompatibleTypeException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < simulateCount; i++) {
			List<Data> dataList = findBuilder("primitive").build(clazz, key);
			if (dataList != null && dataList.size() > 0) {
				sb.append(i == 0 ? "" : ",").append(dataList.get(0).getValue());
			}
		}
		return Arrays.asList(new Data[] { new Data(key, sb.toString()) });
	}
	
	private String buildArrayIndex(String arrVariable, int index) {
		return arrVariable + "[" + index + "]";
	}
	
}
