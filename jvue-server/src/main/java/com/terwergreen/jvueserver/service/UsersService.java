package com.terwergreen.jvueserver.service;

import com.terwergreen.jvueserver.model.User;

/**
 * UsersService
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:23
 **/
public interface UsersService {
    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User login(String username, String password);
}
