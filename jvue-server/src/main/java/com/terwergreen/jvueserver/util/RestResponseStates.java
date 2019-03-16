package com.terwergreen.jvueserver.util;

/**
 * @author terwergreen Rest响应客户端的状态值
 */
public enum RestResponseStates {

    SUCCESS(1, "请求成功，无任何异常"),

    SERVER_ERROR(0, "系统忙，请稍候再试。如有疑问请联系管理员");

    private Integer value;
    private String msg;

    RestResponseStates(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}