package com.terwergreen.jvueserver.util;

/**
 * 默认常量定义
 */
public class Constants {
    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 默认每页展示数目
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 文章简介最大长度
     */
    public static final int MAX_PREVIEW_LENGTH = 255;

    /**
     * 登录地址
     */
    public static final String AUTH_LOGIN_PAGE = "/auth/login";
    /**
     * 登录出错地址
     */
    public static final String AUTH_ERROR_URL = "/auth/login";

    /**
     * 登陆用户session key
     */
    public static final String USER_SESSION_KEY = "login_user";
}
