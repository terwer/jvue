package com.terwergreen.jvueserver.coresevice.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;

import java.util.List;
import java.util.Map;

/**
 * metaWeblogApi接口定义
 *
 * @name: IMetaWeblog
 * @author: terwer
 * @date: 2022-03-07 13:30
 **/
public interface IMetaWeblog {
    /**
     * 获取博客信息：blogger.getUsersBlogs
     *
     * @param appKey   appKey
     * @param username 用户名
     * @param password 密码
     * @return 博客数组
     * @throws XmlRpcException 异常
     */
    List<Map<String, Object>> getUsersBlogs(String appKey, String username, String password) throws XmlRpcException;

    /**
     * 发布博客文章：metaWeblog.newPost
     *
     * @param blogid   blogid
     * @param username 用户名
     * @param password 密码
     * @param post     文章
     * @param publish  是否发布
     * @return postid
     * @throws XmlRpcException 异常
     */
    String newPost(String blogid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException;

    /**
     * 编辑博客文章：metaWeblog.editPost
     *
     * @param postid   postid
     * @param username 用户名
     * @param password 密码
     * @param post     文章
     * @param publish  是否发布
     * @return flag
     * @throws XmlRpcException 异常
     */
    boolean editPost(String postid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException;

    /**
     * 获取博客文章：metaWeblog.getPost
     *
     * @param postid   postid
     * @param username 用户名
     * @param password 密码
     * @return 文章
     * @throws XmlRpcException 异常
     */
    Map<String, Object> getPost(String postid, String username, String password) throws XmlRpcException;

    /**
     * 获取博客分类：metaWeblog.getCategories
     *
     * @param blogid   blogid
     * @param username 用户名
     * @param password 密码
     * @return 分类列表
     * @throws XmlRpcException 异常
     */
    List<Map<String, String>> getCategories(String blogid, String username, String password) throws XmlRpcException;

    /**
     * 获取最近的文章列表：metaWeblog.getRecentPosts
     *
     * @param blogid        blogid
     * @param username      用户名
     * @param password      密码
     * @param numberOfPosts 数目
     * @return 文章列表
     * @throws XmlRpcException 异常
     */
    List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) throws XmlRpcException;

    /**
     * 上传媒体对象：metaWeblog.newMediaObject
     *
     * @param blogid   blogid
     * @param username 用户名
     * @param password 密码
     * @param post     文章
     * @return 媒体信息
     * @throws XmlRpcException 异常
     */
    Map<String, String> newMediaObject(String blogid, String username, String password, Map<String, Object> post) throws XmlRpcException;

    /**
     * 删除博客文章：blogger.deletePost
     *
     * @param appKey   appKey
     * @param postid   postid
     * @param username 用户名
     * @param password 密码
     * @param publish  是否发布
     * @return flag
     * @throws XmlRpcException 异常
     */
    boolean deletePost(String appKey, String postid, String username, String password, boolean publish) throws XmlRpcException;
}
