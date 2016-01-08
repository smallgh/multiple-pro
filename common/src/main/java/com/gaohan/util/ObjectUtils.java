/**
 * 万色城基础服务系统
 * 2015年11月11日-上午11:11:04
 * 2015杭州万色城电子商务有限公司-版权所有
 * 
 */
package com.gaohan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象工具类
 * 
 * @author donghongli 2015年11月11日 上午11:11:04
 * @version 1.1.0
 * 
 */
public class ObjectUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(ObjectUtils.class);

	public static Map<String, String> ConvertObjToMap(Object obj) {
		Map<String, String> reMap = new HashMap<String, String>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(
							fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					if (o == null) {
						continue;
					}
					if (Modifier.isStatic(f.getModifiers())) {
						continue;
					}
					String oStr = "";
					if (o instanceof Double) {
						oStr = String.format("%.2f", o);
					} else if (o.getClass().isArray()) {
						continue;
					} else if (o instanceof Collection) {
						continue;
					} else {
						oStr = o.toString();
					}
					reMap.put(fields[i].getName(), oStr);
				} catch (NoSuchFieldException e) {
					logger.error("对象转Map异常", e);
				} catch (IllegalArgumentException e) {
					logger.error("对象转Map异常", e);
				} catch (IllegalAccessException e) {
					logger.error("对象转Map异常", e);
				}
			}
		} catch (SecurityException e) {
			logger.error("对象转Map异常", e);
		}
		return reMap;
	}

}
