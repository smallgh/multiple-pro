package com.gaohan.web.result;


import com.gaohan.enums.BizResultCodeEnum;
import com.gaohan.exception.BizException;
import com.gaohan.result.BizResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * controller返回result
 *
 */
public class WebResult<T> {

    /**
     * 执行结果
     */
    private String                resultCode;

    /**
     * 提示信息，会显示在页面上，标题
     */
    private String                message;

    /**
     * 错误的字段信息
     */
    private List<WebErrorMessage> errorMessage = new ArrayList<WebErrorMessage>();

    /**
     * 成功标识
     */
    private boolean               success      = false;

    /**
     * 结果集
     */
    private T                     data;

    /**
     * 构造方法
     */
    public WebResult(){
    }

    /**
     * 新建一个对象
     *
     * @param resultCode 结果类型
     * @param message 显示在页面的信息
     */
    public WebResult(String resultCode, String message){
        this.resultCode = resultCode;
        this.message = message;
    }

    /**
     * 新建一个对象
     *
     * @param resultCode
     * @param message
     * @param success
     */
    public WebResult(String resultCode, String message, boolean success){
        this.resultCode = resultCode;
        this.message = message;
        this.success = success;
    }

    /**
     * 构造函数，转化BizResultCodeEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(BizResultCodeEnum codeEnum, boolean success){
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), success);
    }

    /**
     * 构造函数，转化BizResultCodeEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(BizResultCodeEnum codeEnum){
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), true);
    }

    /**
     * 构造函数，转化BizResultCodeEnum，同时attach data
     *
     * @param codeEnum
     * @param data
     */
    public WebResult(BizResultCodeEnum codeEnum, T data){
        this(codeEnum);
        this.data = data;
    }

    public WebResult(BizResultCodeEnum codeEnum, T data,boolean success){
        this(codeEnum,data);
        this.success = success;
    }

    /**
     * 转化业务返回对象
     *
     * @param bizResult
     * @return
     */
    public static WebResult convertBizResult(BizResult<Object> bizResult) {
        WebResult webResult = new WebResult(String.valueOf(bizResult.getCodeNumber()), bizResult.getMessage(), bizResult.isSuccess());
        if (null != bizResult.getResultObj()) {
            webResult.setData(bizResult.getResultObj());
        }
        return webResult;
    }

    /**
     * 转化业务返回对象
     *
     * @param bizResult
     * @return
     */
    public static WebResult convertBizResultMapType(BizResult<Map<String, Object>> bizResult) {
        return new WebResult(bizResult.getCode(), bizResult.getMessage(), bizResult.isSuccess());
    }

    /**
     * 转化BizException对象
     *
     * @param bizEx
     * @return
     */
    public static WebResult convertBizException(BizException bizEx) {
        return new WebResult(String.valueOf(bizEx.getCodeNumber()), bizEx.getMessage());
    }

    /**
     * 转化BizResultCodeEnum对象
     *
     * @return
     */
    public static WebResult convertBizResultCode(BizResultCodeEnum codeEnum) {
        return new WebResult(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription());
    }

    /**
     * 增加错误信息
     *
     * @param field
     * @param bizEx
     */
    public void addFieldError(String field, BizException bizEx) {

        if (errorMessage == null) {
            errorMessage = new ArrayList<WebErrorMessage>();
        }
        errorMessage.add(new WebErrorMessage(field, bizEx.getCode(), bizEx.getMessage()));
    }

    public static<T> WebResult<T> success(T data){
        return new WebResult<T>(BizResultCodeEnum.SUCCESS,data,true);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WebErrorMessage> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<WebErrorMessage> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
