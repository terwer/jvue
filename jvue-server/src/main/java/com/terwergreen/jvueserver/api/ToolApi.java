package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.exception.RestException;
import com.terwergreen.jvueserver.service.ICounterService;
import com.terwergreen.jvueserver.util.RestResponse;
import com.terwergreen.jvueserver.util.RestResponseStates;
import com.terwergreen.jvueserver.util.VerificationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 工具类接口
 *
 * @name: ToolApi
 * @author: terwer
 * @date: 2022-07-17 22:25
 **/
@RestController
@RequestMapping(value = "api/tool", produces = "application/json;charset=utf-8")
public class ToolApi extends BaseApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICounterService counterService;

    @GetMapping("/vcode")
    public RestResponse vcode(HttpServletRequest request, HttpServletResponse response) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            Map verificationCode = VerificationCode.getVerificationCode();
            BufferedImage bufferedImage = (BufferedImage) verificationCode.get("imgStream");

            // 禁止http缓存
            response.setContentType("image/jpeg");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");

            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponse;
    }

    @GetMapping("/counter")
    public RestResponse counter(HttpServletRequest request, HttpServletResponse response) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            Integer counter = counterService.getCounter();
            Map verificationCode = VerificationCode.getVerificationCodeWithStr(String.valueOf(counter));
            BufferedImage bufferedImage = (BufferedImage) verificationCode.get("imgStream");

            // 禁止http缓存
            response.setContentType("image/jpeg");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");

            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponse;
    }
}
