package com.xhu.javaprobaby.common;

public enum ResultEnum {

    /**
     * success:成功
     * error：错误
     * failed:失败
     */
    success(200, "操作成功！"),
    failed(202,"操作失败"),
    error(500, "系统错误"),
    unauthorized(401, "未授权"),
    autherror(403, "授权验证失败"),

    //---系统错误返回码-----
    UNKNOWN_ERROR(-1, "未知错误"),
    FAIL(10001, "系统失败"),
    UNAUTHENTICATED(10002, "您还未登录"),
    UNAUTHORISE(10003, "权限不足"),
    SERVER_ERROR(99999, "抱歉，系统繁忙，请稍后重试！");

    private Integer code;
    private String msg = "成功";

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}