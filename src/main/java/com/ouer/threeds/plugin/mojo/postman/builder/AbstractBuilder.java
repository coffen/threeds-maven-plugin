package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.HashMap;
import java.util.Map;

import com.ouer.threeds.plugin.mojo.postman.Builder;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 抽象构造器，提供各种构造器的查找方式
 * <br>
 * File Name: AbstractBuilder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 19:55:49 
 * @version: v1.0
 *
 */
public abstract class AbstractBuilder implements Builder {

	protected final static Map<String, Builder> builderMap = new HashMap<String, Builder>();
	
	// 注册4种基本构造器: 基本类型构造器、数组构造器、枚举构造器、对象构造器
	static {
		builderMap.put(PrimitiveTypeBuilder.ALIAS, new PrimitiveTypeBuilder());
		builderMap.put(ArrayBuilder.ALIAS, new ArrayBuilder());
		builderMap.put(DomainBuilder.ALIAS, new DomainBuilder());
		builderMap.put(EnumBuilder.ALIAS, new EnumBuilder());
	}
	
	public abstract void register();
	
	/**
	 * 设置Array/List的模拟次数
	 * 
	 * @param count
	 */
	public void setArrayLength(int count) {
		((ArrayBuilder)builderMap.get("array")).setSimulateCount(count);
	}
	
	protected Builder findBuilder(String clazzName) {
		return builderMap.get(clazzName);
	}

}
