package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.util.RestResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageApi
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:03
 **/
@RequestMapping(value = "api/blog/page", produces = "application/json;charset=utf-8")
public class PageApi {
    /**
     * 自定义页面
     *
     * @param title 页面标题
     * @return {@see Articles}
     */
    @PostMapping("{title}")
    public RestResponse page(@PathVariable String title) {
//        Articles page = articlesService.getPage(title);
//        if (null == page) {
//            return error404();
//        }
//        transformContent(page);
//        return RestResponse.ok(page);
        return null;
    }
}
