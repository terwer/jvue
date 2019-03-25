package com.terwergreen.jvueserver.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.terwergreen.jvueserver.util.EncryptAndDecryptUtil;
import com.terwergreen.jvueserver.util.RestResponseStates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口token校验
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/6 14:53
 **/
public class AuthHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(AuthHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
        logger.info(request.getRequestURI());
        // Access-Control-Allow-Origin
        response.setHeader("Access-Control-Allow-Origin", "*");

        // Access-Control-Max-Age
        response.setHeader("Access-Control-Max-Age", "3600");
        // Access-Control-Allow-Credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Access-Control-Allow-Methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // Access-Control-Allow-Headers
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-CSRF-TOKEN");

        // 兼容入参为RequestBody时候的预请求
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            // response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            return true;
        }

        // 校验token
        Object tokenString = request.getParameter("tokenString");
        if (!StringUtils.isEmpty(tokenString) && !tokenString.toString().trim().isEmpty()) {
            logger.info("token:" + EncryptAndDecryptUtil.decrypt(tokenString.toString()));
            JSONObject jsonObject = JSON.parseObject(EncryptAndDecryptUtil.decrypt(tokenString.toString()));
            if (!StringUtils.isEmpty(jsonObject)) {
                request.getSession().setAttribute("admin.loginId", jsonObject.getIntValue("loginId"));
                request.getSession().setAttribute("admin.loginName", jsonObject.getString("loginName"));
                request.getSession().setAttribute("admin.role", jsonObject.getString("role"));
                return true;
            }
        }
        //tokenString错误，返回错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("status", RestResponseStates.SERVER_ERROR.getValue());
            Map dataMap = new HashMap();
            dataMap.put("code", 10001);
            map.put("data", dataMap);
            map.put("msg", "tokenString为空或错误");
            out.append(JSON.toJSONString(map));
            logger.error("tokenString错误");
        } catch (IOException e) {
            logger.error(e);
        }
        return false;
    }
}