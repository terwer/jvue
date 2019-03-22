package com.terwergreen.jvueserver.api.admin;

import com.terwergreen.jvueserver.api.BaseApi;
import com.terwergreen.jvueserver.exception.RestException;
import com.terwergreen.jvueserver.pojo.Users;
import com.terwergreen.jvueserver.service.UsersService;
import com.terwergreen.jvueserver.util.Constants;
import com.terwergreen.jvueserver.util.RestResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台登陆校验API
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:00
 **/
@RestController
@RequestMapping(value = "api/admin/auth", produces = "application/json;charset=utf-8")
public class AuthApi extends BaseApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService usersService;

    /**
     * 后台登录
     *
     * @param response   {@link HttpServletResponse}
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 是否记住
     * @return {@see RestResponse.ok()}
     */
    @ApiOperation("获取文章列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "rememberMe", value = "是否记住")
    })
    @PostMapping("login")
    public RestResponse login(@ApiIgnore HttpServletResponse response, @RequestParam String username, @RequestParam String password, String rememberMe) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            Map<String, Object> paramMap = new HashMap<>();

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return RestResponse.fail("用户名和密码不能为空");
            }
            Users user = usersService.login(username, password);
            // 正确返回了user，说明登录成功
            if (null != user) {
                request.getSession().setAttribute(Constants.USER_SESSION_KEY, user);
                return RestResponse.ok();
            }
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            throw new RestException(e);
        }
        return RestResponse.fail("用户名或密码错误");
    }

    @PostMapping("logout")
    public RestResponse logout() {
        return RestResponse.ok("退出成功");
    }
}
