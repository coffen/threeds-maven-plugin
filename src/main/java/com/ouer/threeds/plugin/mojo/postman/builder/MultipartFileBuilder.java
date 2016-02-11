package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

public class MultipartFileBuilder extends AbstractBuilder {
	
	private Pattern arrayIndexRegExp = Pattern.compile("\\[\\d+\\]$");
	
	public void register() {
		builderMap.put("MultipartFile", this);
	}
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException {
		if (!MultipartFile.class.isAssignableFrom(clazz) || StringUtils.isBlank(key)) {
			throw new IncompatibleTypeException(clazz.getName());
		}
		String expr = trimArrayIndex(key);
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
