package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.core.CommonService;
import com.terwergreen.jvueserver.model.SiteConfig;
import com.terwergreen.jvueserver.util.RestResponse;
import com.terwergreen.jvueserver.util.RestResponseStates;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点配置API接口
 *
 * @author Terwer
 * @version 1.0
 * 19-2-25 下午5:59
 **/
@RestController
@RequestMapping(value = "api/site",produces = "application/json;charset=utf-8")
public class SiteConfigApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonService commonService;

    @ApiOperation("获取站点配置")
    @PostMapping(value = "/config/list")
    public RestResponse getConfigList() {
        RestResponse restResponse = new RestResponse();
        try {
            SiteConfig siteConfig = commonService.getSiteConfig();
            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
            restResponse.setData(siteConfig);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
        }
        return restResponse;
    }
}
