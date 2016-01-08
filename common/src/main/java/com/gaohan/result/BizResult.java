package com.gaohan.result;

import com.gaohan.constants.ResultConstant;
import com.gaohan.enums.BizResultCodeEnum;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回基类
 * 
 */
public class BizResult<T> implements ResultConstant {

    /** serialVersionUID */
    private static final long serialVersionUID = -5935703301155834067L;

    /**
     * 成功标识
     */
    private boolean           success          = false;

    /** 结果码 */
    private String            code             = "UNKNOWN";

    /** 数值型结果码 */
    private int               codeNumber       = 101;

    /**
     * 返回信息
     */
    private String            message          = "未知异常";

    /** 返回结果实例。 */
    private T                 resultObj;

    /**
     * 默认构造函数
     */
    public BizResult(){

    }

    /**
     * 构造函数
     * 
     * @param code
     * @param codeNumber
     * @param message
     */
    public BizResult(String code, int codeNumber, String message){
        this.code = code;
        this.codeNumber = codeNumber;
        this.message = message;
    }

    /**
     * 含操作结果的构造函数
     *
     * @param success 成功标志
     */
    public BizResult(boolean success){
        this.success = success;
        if (success) {
            this.code = SUCCESS_CODE;
            this.codeNumber = SUCCESS_CODE_NUMBER;
            this.message = "成功";
        }
    }

    /**
     * 构造方法
     * 
     * @param success 成功标示
     * @param resultCode 返回值code
     */
    public BizResult(boolean success, String resultCode, int codeNumber, String description){
        this.success = success;
        this.code = resultCode;
        this.codeNumber = codeNumber;
        this.message = description;
    }

    /**
     * 构造方法
     *
     * @param resultCode
     */
    public BizResult(BizResultCodeEnum resultCode){
        this.code = resultCode.getCode();
        this.codeNumber = resultCode.getCodeNumber();
        this.message = resultCode.getDescription();
    }

    /**
     * 返回默认成功对象
     *
     * @return
     */
    public static BaseResult retSuccess() {

        return new BaseResult(true, SUCCESS_CODE, SUCCESS_CODE_NUMBER, "成功");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    public T getResultObj() {
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
