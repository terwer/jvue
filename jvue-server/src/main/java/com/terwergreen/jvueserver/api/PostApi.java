package com.terwergreen.jvueserver.api;

import com.github.pagehelper.PageInfo;
import com.terwergreen.jvueserver.pojo.Post;
import com.terwergreen.jvueserver.service.PostService;
import com.terwergreen.jvueserver.util.Constants;
import com.terwergreen.jvueserver.util.HtmlUtil;
import com.terwergreen.jvueserver.util.MarkdownUtil;
import com.terwergreen.jvueserver.util.PostTypeEmum;
import com.terwergreen.jvueserver.util.RestResponse;
import com.terwergreen.jvueserver.util.RestResponseStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章相关API接口
 *
 * @author Terwer
 * @version 1.0
 * 2018/7/6 10:05
 **/
@RestController
@RequestMapping(value = "api/blog", produces = "application/json;charset=utf-8")
public class PostApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostService postService;

    /**
     * 文章列表
     *
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return {@see Pagination<Post>}
     */
    @PostMapping("post/list")
    public RestResponse list(@RequestParam(required = false) Integer pageNum,
                             @RequestParam(required = false) Integer pageSize) {
        if (pageNum == null) {
            pageNum = Constants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }
        RestResponse restResponse = new RestResponse();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            PageInfo<Post> posts = postService.getPostsByPage(pageNum, pageSize, paramMap);

            if (null == posts.getList()) {
                restResponse.setData(new ArrayList<>());
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
                return restResponse;
            }

            // 转换结果
            Map<String, Object> resultMap = new HashMap<>();
            List<Post> list = posts.getList();
            for (Post post : list) {
                this.transformPreView(post);
            }
            resultMap.put("list", list);

            restResponse.setData(resultMap);
            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
        }
        return restResponse;
    }

    /**
     * 文章内容转为html
     *
     * @param article 文章实体类
     */
    private void transformContent(Post article) {
        String rawContent = article.getContent();
        String html = MarkdownUtil.md2html(rawContent);
        article.setContent(html);
        article.setRawContent(rawContent);
    }

    /**
     * 文章内容转为预览html
     *
     * @param article 文章实体类
     */
    private void transformPreView(Post article) {
        String html = MarkdownUtil.md2html(article.getContent());
        String filteredHtml = HtmlUtil.parseHtml(html, Constants.MAX_PREVIEW_LENGTH);
        article.setContent(null);
        article.setDesc(filteredHtml);
    }
}
