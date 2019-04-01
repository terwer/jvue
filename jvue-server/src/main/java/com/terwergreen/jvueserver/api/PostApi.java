package com.terwergreen.jvueserver.api;

import com.github.pagehelper.PageInfo;
import com.terwergreen.jvueserver.exception.RestException;
import com.terwergreen.jvueserver.model.Post;
import com.terwergreen.jvueserver.service.PostService;
import com.terwergreen.jvueserver.util.Constants;
import com.terwergreen.jvueserver.util.HtmlUtil;
import com.terwergreen.jvueserver.util.ImageUtil;
import com.terwergreen.jvueserver.util.MarkdownUtil;
import com.terwergreen.jvueserver.util.PostStatusEnum;
import com.terwergreen.jvueserver.util.PostTypeEmum;
import com.terwergreen.jvueserver.util.RestResponse;
import com.terwergreen.jvueserver.util.RestResponseStates;
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
@RequestMapping(value = "api/blog/post", produces = "application/json;charset=utf-8")
public class PostApi extends BaseApi {
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
    @ApiOperation("获取文章列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示的数目"),
            @ApiImplicitParam(name = "isHot", value = "是否热门，1热门，不传或者0全部"),
            @ApiImplicitParam(name = "postStatus", value = "状态"),
            @ApiImplicitParam(name = "postType", value = "文章类型"),
            @ApiImplicitParam(name = "search", value = "搜索关键字")
    })
    @PostMapping("/list")
    public RestResponse getPostList(@RequestParam(required = false) Integer pageNum,
                                    @RequestParam(required = false) Integer pageSize,
                                    @RequestParam(required = false) Integer isHot,
                                    @RequestParam(required = false) String postStatus,
                                    @RequestParam(required = false) String postType,
                                    @RequestParam(required = false) String search
    ) throws RestException {
        if (pageNum == null) {
            pageNum = Constants.DEFAULT_PAGE_NUM;
        }
        if (pageSize == null) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }
        RestResponse restResponse = new RestResponse();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            if (StringUtils.isEmpty(postType)) {
                paramMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            } else {
                paramMap.put("postType", postType);
            }
            if (null != isHot && isHot == 1) {
                paramMap.put("isHot", isHot);
                pageSize = 5;
            }
            if (StringUtils.isNotEmpty(postStatus)) {
                paramMap.put("postStatus", postStatus);
            }
            if (StringUtils.isNotEmpty(search)) {
                paramMap.put("search", search);
            }
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
            resultMap.put("total", posts.getTotal());
            resultMap.put("list", list);

            restResponse.setData(resultMap);
            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponse;
    }

    @ApiOperation("获取文章详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "postSlug", value = "文章ID或者别名")
    })
    @PostMapping("/detail")
    public RestResponse getPostDetail(@RequestParam(required = true) String postSlug) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            if (StringUtils.isEmpty(postSlug)) {
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg("文章ID或者别名不能为空");
                return restResponse;
            }

            Post post = null;
            if (StringUtils.isNumeric(postSlug)) {
                post = postService.getPostById(Integer.parseInt(postSlug));
            } else {
                post = postService.getPostBySlug(postSlug);
            }

            if (null == post || post.getStatus().equals(PostStatusEnum.POST_STATUS_DRAFT.getName())) {
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg("文章不存在");
                return restResponse;
            }

            // 转换结果
            transformContent(post);
            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
            restResponse.setData(post);
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponse;
    }

    /**
     * 点击量添加
     *
     * @param postId 文章id
     * @param hits   当前点击量
     */
    @ApiOperation("获取文章详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "postId", value = "文章ID"),
            @ApiImplicitParam(name = "hits", value = "浏览数")
    })
    @PostMapping("/updateHits")
    public RestResponse updateHits(Integer postId, Integer hits) {
        RestResponse restResponse = new RestResponse();
        try {
            if (null == postId) {
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg("文章ID不能为");
                return restResponse;
            }
            if (null == hits || hits <= 0) {
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg("浏览数不能为空或者浏览数错误");
                return restResponse;
            }

            postService.updatePostHits(postId, hits);
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
     * @param post 文章实体类
     */
    private void transformContent(Post post) {
        String rawContent = post.getContent();
        String html = MarkdownUtil.md2html(rawContent);
        post.setContent(html);
        post.setRawContent(rawContent);
    }

    /**
     * 文章内容转为预览html
     *
     * @param post 文章实体类
     */
    private void transformPreView(Post post) {
        // markdown转换为html
        String html = MarkdownUtil.md2html(post.getContent());
        post.setContent(null);
        // 截取摘要
        String filteredHtml = HtmlUtil.parseHtml(html, Constants.MAX_PREVIEW_LENGTH);
        post.setDesc(filteredHtml);
        // 解析图片
        List<String> thumbnails = ImageUtil.getImgSrc(html);
        post.setThumbnails(thumbnails);
    }
}
