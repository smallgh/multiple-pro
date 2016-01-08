package com.gaohan.web.util;

import com.google.common.io.Resources;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

public class HttpRequestHelper {
	
	public enum HttpHeader {
		
		ACCEPT("accept"),
		ACCEPT_LANGUAGE("accept-language"),
		ACCEPT_ENCODING("Accept-Encoding"),
		USER_AGENT("User-Agent"),
		XML_REQUESTED_WITH("X-Requested-With");

		private final String field;

		HttpHeader(String field) {
			this.field = field;
		}

		public String getHeaderFieldName() {
			return field;
		}
	}

	/**
	 * If the ajax request send by JQuery, 
	 * according to the header x-requested-with to judge the request is ajax request or not
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {

		String ajaxParameter = request.getHeader(HttpHeader.XML_REQUESTED_WITH.getHeaderFieldName());
		return !StringUtils.isEmpty(ajaxParameter);
	}
	
	public static boolean canReceivedJsonData(HttpServletRequest request){
		
		boolean isJsonDataReceived = false;
		
		String acceptType = request.getHeader(HttpHeader.ACCEPT.getHeaderFieldName());
		if(!acceptType.isEmpty()){
			isJsonDataReceived = acceptType.toLowerCase().contains("*/*") || acceptType.toLowerCase().contains("application/json");
		}
		return isJsonDataReceived;
	}
	
	public static String getRequestResourceLinkUrl(HttpServletRequest request){
		
		String path;
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		if (StringUtils.hasText(contextPath)) {
			if(contextPath.length() == uri.length())
			{
				path = "/";
			}else {
				path = uri.substring(contextPath.length() == 1 ? 1 : contextPath.length() + 1);
			}
        } else {
            path = uri.substring(1);
        }
        if (path.indexOf("?") > 0) {
            path = path.substring(0, path.indexOf("?"));
        }
        if (!StringUtils.hasText(path)) {
            path = "/";
        }
        return path;
    }

    public static final URL PUBLIC_PATH_URL = Resources.getResource("res");

}
