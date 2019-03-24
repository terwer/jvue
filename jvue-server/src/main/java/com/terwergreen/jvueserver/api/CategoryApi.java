package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.util.RestResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryApi
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:03
 **/
@RestController
@RequestMapping(value = "api/blog/cat", produces = "application/json;charset=utf-8")
public class CategoryApi {
    /**
     * 分类页
     *
     * @return {@see List<MetaDto>}
     */
    @PostMapping("post/list")
    public RestResponse category() {
//        List<MetaDto> metaDtos = metasService.getMetaDtos(Types.CATEGORY);
//        return RestResponse.ok(metaDtos);
        return null;
    }
}
