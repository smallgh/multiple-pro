package com.gaohan.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class GsonUtil {
	private static Gson gson = new GsonBuilder().create();
	
	public  static Gson getGson(){
		return gson;
	}
	
	public <T> T fromJson(String json, Class<T> clazz) {
		if(StringUtils.isBlank(json))
			return null;
		
		return (T)gson.fromJson(json, clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String json, Type type) {
		if(StringUtils.isBlank(json))
			return null;
		
		return (T)gson.fromJson(json, type);
	}
	
	public String toJson(Object object) {
		return gson.toJson(object);
	}
}
