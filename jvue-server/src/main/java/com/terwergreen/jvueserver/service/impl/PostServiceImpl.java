package com.terwergreen.jvueserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.terwergreen.jvueserver.core.CommonDAO;
import com.terwergreen.jvueserver.pojo.Post;
import com.terwergreen.jvueserver.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章相关API
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/10 1:23
 **/
@Service
public class PostServiceImpl implements PostService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<Post> getRecentPosts(Map<String, Object> paraMap) {
        return null;
    }

    @Override
    public Post getPostBySlug(String slug) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postSlug", slug);
        Post post = (Post) commonDAO.querySingleByMap("getPostBySlug", paramMap);
        return post;
    }

    @Override
    public Post getPostById(Integer postId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postId", postId);
        Post post = (Post) commonDAO.querySingleByMap("getPostById", paramMap);
        return post;
    }

    @Override
    public PageInfo<Post> getPostsByPage(Integer pageNum, Integer pageSize, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum, pageSize);
        List<Post> list = (List<Post>) commonDAO.queryListByMap("selectPosts", paramMap);
        // 分页信息
        PageInfo<Post> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        if (pageNum > pages) {
            pageInfo.setList(new ArrayList<>());
        }
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        logger.info("分页信息：total=" + total + "，pages=" + pages + "，pageNum=" + pageNum + "，pageSize=" + pageSize);
        return pageInfo;
    }

    @Override
    public List getUsersBlogs(String appkey, String username, String password) {
        return null;
    }

    @Override
    public Integer newPost(Post post) {
        return null;
    }

    @Override
    public boolean editPostById(Post post) {
        return false;
    }

    @Override
    public boolean deletePostBySlug(String postSlug) {
        return false;
    }

    @Override
    public boolean deletePostById(Integer postId) {
        return false;
    }
}