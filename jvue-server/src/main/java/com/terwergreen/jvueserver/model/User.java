package com.terwergreen.jvueserver.model;

import java.util.Date;

/**
 * 用户 Model
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:22
 **/
public class User {

    /**
     * 账号
     */
    private String username;

    /**
     * 用户密码 md5存储
     */
    private String passwordMd5;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户显示名称
     */
    private String screenName;

    /**
     * 用户创建时间
     */
    private Date created;

    /**
     * 最后登陆时间
     */
    private Date logged;
}

