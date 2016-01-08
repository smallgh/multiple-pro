package com.gaohan.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaohan.exception.BizException;
import com.gaohan.enums.BizResultCodeEnum;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class JacksonUtil {

	private static final ObjectMapper mapper          = new ObjectMapper();
	private static       boolean      isPrettyPrinter = true;
	private static final Logger LOGGER = Logger.getLogger(JacksonUtil.class);

	private JacksonUtil() {

	}
	
	/**
	 * Convert simple Object to json string
	 * @param obj
	 * @return String
	 */
	public static String toJson(Object obj) {

		String jsonString = null;
		Writer writer = new StringWriter();
		try {
			if (isPrettyPrinter){
				mapper.writerWithDefaultPrettyPrinter().writeValue(writer, obj);
			} else{
				mapper.writeValue(writer, obj);
			}
			jsonString = writer.toString();
		} catch (IOException exception) {
			throw new BizException(BizResultCodeEnum.ILLEGAL_ARGUMENT, exception);
		}
		return jsonString;
	}
	
	/**
	 * 
	 * <p>Convert json string to list<Object> </p>
	 * <pre>
	 * 		The json string must a list,
	 * </pre>
	 * @param jsonStr
	 * @param classType
	 * @return 
	 * List<T>
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> fromJsonToObjList(String jsonStr, Class<T[]> classType){
		ObjectMapper mapper = new ObjectMapper();
		T []objArray = null;
		try {
			objArray = (T[]) mapper.readValue(jsonStr, classType);
		} catch (IOException exception) {
			LOGGER.error(MessageFormat.format("Convert jsonStr:{0} to class:{1} failed", jsonStr,classType), exception);
			throw new BizException(BizResultCodeEnum.ILLEGAL_ARGUMENT, exception);
		}
		return Arrays.asList(objArray);
	}
	
	
	public static <T> T fromJsonToObj(String jsonStr, Class<T> classType){
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj =  mapper.readValue(jsonStr, classType);
		} catch (IOException exception) {
			LOGGER.error(MessageFormat.format("Convert jsonStr:{0} to class:{1} failed", jsonStr,classType), exception);
			throw new BizException(BizResultCodeEnum.ILLEGAL_ARGUMENT, exception);
		}
		return obj;
	}
	

}
