package com.gaohan.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 工具类，发送POST请求
 * 
 * @author Zou, Qiming
 */
public final class HttpPostUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpPostUtil.class);

    private HttpPostUtil() {
    }

    public static final String post(String url, Map<String, Object> params) throws ClientProtocolException, IOException{
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String body = null;
        LOGGER.info("ERP POST请求开始：url={}", url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.close();

        return body;
    }

    private static final HttpPost postForm(String url, Map<String, Object> params) throws UnsupportedEncodingException{

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        for (Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        
        LOGGER.info("ERP Post数据：{}", nvps.toString());

        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        
        return httpost;
    }

    private static final String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost) throws ClientProtocolException, IOException {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static final String paseResponse(HttpResponse response) throws ParseException, IOException{
        HttpEntity entity = response.getEntity();

        String body = null;
        body = EntityUtils.toString(entity);

        return body;
    }

    private static final HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) throws ClientProtocolException, IOException {
        HttpResponse response = null;

        response = httpclient.execute(httpost);
        return response;
    }
}
