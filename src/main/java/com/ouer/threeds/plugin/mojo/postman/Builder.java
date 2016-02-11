package com.ouer.threeds.plugin.mojo.postman;

import java.util.List;

import com.ouer.threeds.plugin.bean.postman.Data;
import com.ouer.threeds.plugin.mojo.postman.exception.IncompatibleTypeException;

/**
 * <p>
 * Project Name: 买到手抽筋
 * <br>
 * Description: 构造器接口
 * <br>
 * File Name: Builder.java
 * <br>
 * Copyright: Copyright (C) 2015 All Rights Reserved.
 * <br>
 * Company: 杭州偶尔科技有限公司
 * <br>
 * @author 穷奇
 * @create time：2016-02-11 20:33:49 
 * @version: v1.0
 *
 */
public interface Builder {
	
	public List<Data> build(Class<?> clazz, String key) throws IncompatibleTypeException;
	
}
