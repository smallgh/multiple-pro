package com.gaohan.web.result;

/**
 * 错误消息对象
 */
public class WebErrorMessage {

    /**
     * 出错的property
     */
    private String field;

    /** 错误码 */
    private String code;

    /**提示信息*/
    private String message;

    /**
     * 构造方法
     * @param field
     * @param message
     */
    public WebErrorMessage(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
