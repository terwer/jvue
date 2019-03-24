package com.terwergreen.jvueserver.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ArchiveApi
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:03
 **/
@RestController
@RequestMapping(value = "api/blog/archive", produces = "application/json;charset=utf-8")
public class ArchiveApi {
    /**
     * 归档页
     *
     * @return {@see List<Archives>}
     */
//    @PostMapping("post/list")
//    public RestResponse archive() {
//        Integer maxLimit = 9999;
//        List<Articles> articles = articlesService.getContents(1, maxLimit);
//        List<Archives> archives = new ArrayList<>();
//        String current = "";
//        for (Articles article : articles) {
//            // 清空文章内容
//            article.setContent("");
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(article.getCreated());
//            String dateStr = cal.get(Calendar.YEAR) + "";
//            if (dateStr.equals(current)) {
//                Archives arc = archives.get(archives.size() - 1);
//                arc.getArticles().add(article);
//                arc.setCount(arc.getArticles().size());
//            } else {
//                current = dateStr;
//                Archives arc = new Archives();
//                arc.setDateStr(dateStr);
//                arc.setCount(1);
//                List<Articles> arts = new ArrayList<>();
//                arts.add(article);
//                arc.setArticles(arts);
//                archives.add(arc);
//            }
//        }
//        return RestResponse.ok(archives);
//    }
}
