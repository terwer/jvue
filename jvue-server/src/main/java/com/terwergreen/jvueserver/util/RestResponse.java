package com.terwergreen.jvueserver.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author terwergreen
 * 接口返回数据模型DTO
 */
@Getter
@Setter
public class RestResponse {

    /**
     * 状态码（数字节点），包括通用状态码及业务状态
     */
    private Integer status;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据节点（对象节点），包含接口中的业务数据
     */
    private Object data;

    public RestResponse() {
    }

    private RestResponse(Integer status) {
        this.status = status;
    }

    private RestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private RestResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public RestResponse(RestResponse dto) {
        if (null == dto || null == dto.getStatus()) {
            this.status = RestResponseStates.SUCCESS.getValue();
            this.msg = RestResponseStates.SUCCESS.getMsg();
        } else {
            this.status = dto.getStatus();
            this.msg = dto.getMsg();
            this.data = dto.getData();
        }
    }

    // ===============
    // 便捷方法
    // ===============
    public static RestResponse ok() {
        return new RestResponse(RestResponseStates.SUCCESS.getValue(), RestResponseStates.SUCCESS.getMsg());
    }

    public static RestResponse ok(Object data) {
        return new RestResponse(RestResponseStates.SUCCESS.getValue(), RestResponseStates.SUCCESS.getMsg(), data);
    }

    public static RestResponse fail() {
        return new RestResponse(RestResponseStates.SERVER_ERROR.getValue(), RestResponseStates.SERVER_ERROR.getMsg());
    }

    public static RestResponse fail(String msg) {
        return new RestResponse(RestResponseStates.SERVER_ERROR.getValue(), msg);
    }
}
