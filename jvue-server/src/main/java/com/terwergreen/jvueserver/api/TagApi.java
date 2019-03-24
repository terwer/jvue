package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.util.RestResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签相关API接口
 *
 * @author Terwer
 * @version 1.0
 * 2018/7/6 10:05
 **/
@RestController
@RequestMapping(value = "api/blog/tag", produces = "application/json;charset=utf-8")
public class TagApi {
    /**
     * 标签页
     *
     * @return {@see List<MetaDto>}
     */
    @PostMapping("post/list")
    public RestResponse tag() {
//        List<MetaDto> metaDtos = metasService.getMetaDtos(Types.TAG);
//        return RestResponse.ok(metaDtos);
        return null;
    }

}
