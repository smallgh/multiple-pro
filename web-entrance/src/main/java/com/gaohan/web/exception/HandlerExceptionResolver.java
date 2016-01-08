package com.gaohan.web.exception;

import com.gaohan.exception.BizException;
import com.gaohan.web.result.WebResult;
import com.gaohan.web.util.HttpRequestHelper;
import com.gaohan.web.util.WebResultUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaohan on 2015/12/28.
 */
public class HandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        if (ex instanceof BizException) {
            LOGGER.warn("异常捕获:", ex);
        } else {
            LOGGER.error("异常捕获:", ex);
        }

        //        CustomWebRequestStat customWebRequestStat = CustomWebRequestStat.current();
        //
        //        if (customWebRequestStat != null) {
        //            customWebRequestStat.setError(ex);
        //        }

        ModelAndView resultModelAndView = new ModelAndView();

        //        if (HttpRequestHelper.isAjaxRequest(request) && HttpRequestHelper.canReceivedJsonData(request)) {
        WebResult<?> webResult;
        if (ex instanceof BizException) {
            webResult = WebResultUtil.bizExceptionToWebResult((BizException) ex);
        } else {
            webResult = new WebResult<>(StringUtils.EMPTY, ex.toString(), false);
        }
        resultModelAndView.setView(WebResultUtil.wrapIntoJsonView(webResult));

        //        } else {
        //            resultModelAndView.addObject("errorMsg",ex.getMessage());
        //            resultModelAndView.setViewName("error/error");
        //        }

        return resultModelAndView;
    }
}
