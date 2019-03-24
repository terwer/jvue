package com.terwergreen.jvueserver.service.impl;

import com.terwergreen.jvueserver.model.User;
import com.terwergreen.jvueserver.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * UsersServiceImpl
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:24
 **/
@Service
public class UsersServiceImpl implements UsersService {

    @Override
    public User login(String username, String password) {
//        User record = new User();
//        record.setUsername(username);
//        String md5 = FameUtil.getMd5(password);
//        record.setPasswordMd5(md5);
        User user = null;
        if (username.equals("admin") && password.equals("123456")) {
            user = new User();
        }
//        User user = usersMapper.selectOne(record);
//        if (user == null) {
//            throw new ServiceException("用户名或者密码错误");
//        }
//        user.setLogged(new Date());
//        // usersMapper.updateByPrimaryKey(user);
//        //清空密码
//        user.setPasswordMd5(null);
        return user;
    }

}
