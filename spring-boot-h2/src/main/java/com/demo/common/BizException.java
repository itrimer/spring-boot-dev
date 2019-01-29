package com.demo.common;

public class BizException extends Exception {
    private int code;
    private String errorMsg;

    public BizException(int code, String errorMsg) {
        super(code + ":" + errorMsg);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
