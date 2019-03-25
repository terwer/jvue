package com.terwergreen.jvueserver.api.admin;

import com.github.pagehelper.PageInfo;
import com.terwergreen.jvueserver.exception.RestException;
import com.terwergreen.jvueserver.model.Post;
import com.terwergreen.jvueserver.service.PostService;
import com.terwergreen.jvueserver.util.Constants;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台文章管理 Controller
 *
 * @author Terwer
 * @since 2017/7/11 19:52
 */
@RequestMapping("/api/admin/post")
@RestController
public class PostManageApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostService postService;

    /**
     * 文章信息列表
     *
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return {@see Pagination<Post>}
     */
    @ApiOperation("获取文章列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示的数目"),
            @ApiImplicitParam(name = "postStatus", value = "状态")
    })
    @PostMapping("list")
    public RestResponse getPostList(@RequestParam(required = false) Integer pageNum,
                                    @RequestParam(required = false) Integer pageSize,
                                    @RequestParam(required = false) String postStatus
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
            if (StringUtils.isNotEmpty(postStatus)) {
                paramMap.put("postStatus", postStatus);
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
            resultMap.put("total", posts.getTotal());
            resultMap.put("pageSize", posts.getPageSize());
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

    /**
     * 单个文章信息
     *
     * @param postId 文章id
     */
    @ApiOperation("获取文章详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "postId", value = "文章ID或者别名")
    })
    @PostMapping("/{postId}")
    public RestResponse getPostDetail(@PathVariable Integer postId) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            if (null == postId) {
                restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
                restResponse.setMsg("文章ID不能为空");
                return restResponse;
            }

            Post post = postService.getPostById(postId);

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
     * 新建或修改文章
     *
     * @param post <br/>
     *             id           文章id
     *             title        文章标题
     *             content      文章内容
     *             tags         文章标签
     *             category     文章分类
     *             status       Types#DRAFT,Types#PUBLISH
     *             allowComment 是否允许评论
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("/save")
    public RestResponse savePost(Post post) throws RestException {
        RestResponse restResponse = new RestResponse();
        try {
            // 登录检测
            // 发布文章
            postService.savePost(post);

            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
            restResponse.setData(post);
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return RestResponse.ok("保存文章成功");
    }

    /**
     * 文章移到回收站
     *
     * @param postId 文章ID
     * @return 结果
     */
    @PostMapping("/trash/{postId}")
    public RestResponse trashArticle(@PathVariable Integer postId) {
        return RestResponse.fail();
    }

    /**
     * 删除文章
     *
     * @param postId 文章id
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("/del/{postId}")
    public RestResponse deleteArticle(@PathVariable Integer postId) {
//        if (articlesService.deleteArticle(id)) {
//            return RestResponse.ok("删除文章成功");
//        } else {
        return RestResponse.fail();
//        }
    }
}
