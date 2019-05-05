package com.terwergreen.jvueserver.service;

import com.github.pagehelper.PageInfo;
import com.terwergreen.jvueserver.model.Post;
import com.terwergreen.jvueserver.util.PostStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * 文章相关API
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/10 1:23
 **/
public interface PostService {
    /**
     * 查询最新文章
     *
     * @param paramMap 筛选条件
     * @return 最新文章
     */
    List<Post> getRecentPosts(Map<String, Object> paramMap);

    /**
     * 查询单个文章
     *
     * @param slug 文章别名
     * @return 单个文章
     */
    Post getPostBySlug(String slug);

    /**
     * 查询单个文章
     *
     * @param postId 文章ID
     * @return 单个文章
     */
    Post getPostById(Integer postId);

    /**
     * 获取分页数据
     *
     * @param pageNum  页码
     * @param pageSize 数目
     * @param paramMap 筛选条件
     * @return 分页数据
     */
    PageInfo<Post> getPostsByPage(Integer pageNum, Integer pageSize, Map<String, Object> paramMap);

    /**
     * 获取博客信息
     *
     * @param appkey   appkey
     * @param username 用户名
     * @param password 密码
     * @return 博客信息
     */
    List getUsersBlogs(String appkey, String username, String password);

    /**
     * 添加或者更新文章
     *
     * @return 结果
     */
    Post savePost(Post post);

    /**
     * 根据ID删除文章
     *
     * @param postId 文章ID
     * @return 是否删除成功
     */
    boolean deletePostById(Integer postId);

    /**
     * 更新文章状态
     *
     * @param postId 文章ID
     * @param status 状态
     * @return 结果
     */
    boolean updatePostStatus(Integer postId, PostStatusEnum status);

    /**
     * 更新文章浏览时
     *
     * @param articleId 文章ID
     * @param hits      浏览时
     * @return 结果
     */
    boolean updatePostHits(Integer articleId, Integer hits);
}