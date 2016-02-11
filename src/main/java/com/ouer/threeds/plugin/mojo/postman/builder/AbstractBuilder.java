package com.ouer.threeds.plugin.mojo.postman.builder;

import java.util.HashMap;
import java.util.Map;

import com.ouer.threeds.plugin.mojo.postman.Builder;

public abstract class AbstractBuilder implements Builder {

	protected final static Map<String, Builder> builderMap = new HashMap<String, Builder>();
	
	static {
		builderMap.put(PrimitiveTypeBuilder.ALIAS, new PrimitiveTypeBuilder());
		builderMap.put(ArrayBuilder.ALIAS, new ArrayBuilder());
		builderMap.put(DomainBuilder.ALIAS, new DomainBuilder());
		builderMap.put(EnumBuilder.ALIAS, new EnumBuilder());
	}
	
	public abstract void register();
	
	public void setArrayLength(int count) {
		((ArrayBuilder)builderMap.get("array")).setSimulateCount(count);
	}
	
	protected Builder findBuilder(String clazzName) {
		return builderMap.get(clazzName);
	}

}
