package com.terwergreen.jvueserver.coresevice.xmlrpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.terwergreen.jvueserver.core.CommonService;
import com.terwergreen.jvueserver.coresevice.aliyunoss.OssManager;
import com.terwergreen.jvueserver.model.Post;
import com.terwergreen.jvueserver.model.SiteConfig;
import com.terwergreen.jvueserver.service.PostService;
import com.terwergreen.jvueserver.service.UsersService;
import com.terwergreen.jvueserver.util.SpringBeanUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.XmlRpcNotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * metaWeblogApi的具体实现
 *
 * @name: MetaWeblogImpl
 * @author: terwer
 * @date: 2022-03-07 14:09
 **/
public class MetaWeblogImpl implements IMetaWeblog {
    private static final Logger logger = LoggerFactory.getLogger(MetaWeblogImpl.class);

    private CommonService commonService;
    private Object userService;
    private PostService postService;
    private SiteConfig siteConfig;

    public CommonService getCommonService() {
        if (commonService == null) {
            commonService = SpringBeanUtils.getBean(CommonService.class);
        }
        return commonService;
    }

    public PostService getPostService() {
        if (postService == null) {
            postService = SpringBeanUtils.getBean(PostService.class);
        }
        return postService;
    }

    public Object getUserService() {
        if (userService == null) {
            userService = (UsersService) SpringBeanUtils.getBean("userService");
            // userService = SpringBeanUtils.getBean("com.terwergreen.plugins.auth.service.impl.UserService");
        }
        return userService;
    }

    public MetaWeblogImpl() {
        siteConfig = getCommonService().getSiteConfig();
        logger.info("容器中注册MetaWeblogImpl");
    }

    private Map<String, Object> isValid(String username, String password) throws XmlRpcNotAuthorizedException {
        logger.info("username: {}, password: {}", username, password);
        Map<String, Object> rtnResult = new HashMap<>();
//        Object userService = getUserService();
//        Map<String, Object> rtnResult = (Map<String, Object>) ReflectUtil.invoke(userService, "isValid", new Class[]{String.class, String.class},
//                new Object[]{username, password});
//        boolean isValid = (boolean) rtnResult.get("matches");
//        logger.info("isValid = {}", isValid);
//        if (!isValid) {
//            throw new XmlRpcNotAuthorizedException("账号或密码有误");
//        }
        return rtnResult;
    }

    @Override
    public List<Map<String, Object>> getUsersBlogs(String appKey, String username, String password) throws XmlRpcException {
        logger.info("[blogger.getUsersBlogs] -> appKey: {}, username: {}, password: {}", appKey, username, password);
        isValid(username, password);

        List<Map<String, Object>> usersBlogs = new ArrayList<>();
        Map<String, Object> blogInfo = new HashMap<>();
        blogInfo.put("blogid", "JVue");
        blogInfo.put("url", siteConfig.getWeburl());
        blogInfo.put("blogName", siteConfig.getWebname());
        usersBlogs.add(blogInfo);

        return usersBlogs;
    }

    @Override
    public String newPost(String blogid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException {
        // logger.info("metaWeblog.newPost -> blogid: {}, post: {}, publish: {}", blogid, JSON.toJSONString(post), publish);
        logger.info("metaWeblog.newPost -> blogid: {}, publish: {}", blogid, publish);
        Map<String, Object> rtnResult = isValid(username, password);

        JSONObject postJson = JSONObject.parseObject(JSON.toJSONString(post));
        // logger.debug("postJson = {}", postJson);

        Integer postId = 0;
        Map pramMap = new HashMap();
        try {
            Post postObj = new Post();
            // ==========
            // 数据转换开始
            // ArticleCategory ac = getCategory(postJson.getJSONArray("categories"));
            // article.setCategory(ac);
            postObj.setTitle(postJson.getString("title"));
            postObj.setContent(postJson.getString("description"));
            postObj.setTags(postJson.getString("mt_keywords"));
            postObj.setName(postJson.getString("wp_slug"));
            postObj.setStatus(postJson.getString("post_status"));
            postObj.setCreated(postJson.getDate("dateCreated"));
            // post page essay note
            postObj.setType("post");
            postObj.setAuthorId(1);
            // 数据转换结束
            // ==========

            postId = getPostService().savePost(postObj).getId();
            logger.info("postId = {}", postId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return postId + "";
    }

    @Override
    public boolean editPost(String postid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException {
        // logger.info("metaWeblog.editPost -> postid: {}, post: {}", postid, JSON.toJSONString(post));
        logger.info("metaWeblog.editPost -> postid: {}", postid);

        Map<String, Object> rtnResult = isValid(username, password);

        JSONObject postJson = JSONObject.parseObject(JSON.toJSONString(post));
        // logger.debug("postJson = {}", postJson);

        boolean flag = false;
        try {
            Post postObj = new Post();
            // ==========
            // 数据转换开始
            // ArticleCategory ac = getCategory(postJson.getJSONArray("categories"));
            // article.setCategory(ac);
            postObj.setId(Integer.valueOf(postid));
            postObj.setTitle(postJson.getString("title"));
            postObj.setContent(postJson.getString("description"));
            postObj.setTags(postJson.getString("mt_keywords"));
            postObj.setName(postJson.getString("wp_slug"));
            postObj.setStatus(postJson.getString("post_status"));
            postObj.setCreated(postJson.getDate("dateCreated"));
            // post page essay note
            postObj.setType("post");
            postObj.setAuthorId(1);
            // 数据转换结束
            // ==========

            flag = getPostService().savePost(postObj).getId() > 0;
            logger.info("flag = {}", flag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return flag;
    }

    @Override
    public Map<String, Object> getPost(String postid, String username, String password) throws XmlRpcException {
        logger.info("metaWeblog.getPost -> postid: {}", postid);
        isValid(username, password);

        Map<String, Object> rtnResult = isValid(username, password);

        Map<String, Object> post = new HashMap<>();
        Map pramMap = new HashMap();
        try {

            Post postObj = getPostService().getPostById(Integer.valueOf(postid));
            // ==========
            // 数据转换开始
            post = transformPost(postObj);
            // 数据转换结束
            // ==========
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return post;
    }

    private Map<String, Object> transformPost(Post postObj) {
        Map<String, Object> post = new HashMap<>();

        // post.put("categories", postObj.getCategory());
        post.put("postid", postObj.getId());
        post.put("title", postObj.getTitle());
        post.put("description", postObj.getContent());
        post.put("mt_keywords", postObj.getTags());
        post.put("wp_slug", postObj.getName());
        post.put("post_status", postObj.getStatus());
        post.put("dateCreated", postObj.getCreated());
        post.put("permalink", siteConfig.getWeburl() + "/post/" + postObj.getId() + ".html");
        // post page essay note
        post.put("post", postObj.getType());

        return post;
    }

    @Override
    public List<Map<String, String>> getCategories(String blogid, String username, String password) throws XmlRpcException {
        logger.info("metaWeblog.getCategories -> blogid: {}", blogid);

        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) throws XmlRpcException {
        return this.getRecentPosts(blogid, username, password, numberOfPosts, 1, "");
    }

    @Override
    public List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts, int page, String keyword) throws XmlRpcException {
        logger.info("metaWeblog.getRecentPosts -> blogid: {}, numberOfPosts: {}", blogid, numberOfPosts);

        List<Map<String, Object>> posts = new ArrayList<>();

        try {
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("pageSize", numberOfPosts);
            paraMap.put("pageNum", page);
            paraMap.put("search", keyword);
            List<Post> postList = getPostService().getRecentPosts(paraMap);

            for (Post postObj : postList) {
                // ==========
                // 数据转换开始
                Map<String, Object> post = transformPost(postObj);
                posts.add(post);
                // 数据转换结束
                // ==========
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return posts;
    }

    @Override
    public Map<String, String> newMediaObject(String blogid, String username, String password, Map<String, Object> post) throws XmlRpcException {
        logger.info("metaWeblog.newMediaObject -> blogid: {}", blogid);

        isValid(username, password);

        Map<String, String> urlData = new HashMap<>();

        try {
            String retUrl = "http://oss.terwergreen.com/%s";
            String name = post.get("name").toString();
            //  {year}/{mon}/{day}/{filename}{.suffix}
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String forder = sdf.format(now);
            System.out.println("forder = " + forder);
            String fileName = "bugucms/" + forder + "/" + name;
            String url = String.format(retUrl, fileName);

            byte[] bits = (byte[]) post.get("bits");
            logger.info("准备上传图片，url = " + url);
            // 开始上传图片
            OssManager manager = OssManager.getInstance();
            manager.upload(fileName, bits);

            // 水印
            // String watermark = String.format("?x-oss-process=%s", "image/auto-orient,1/quality,q_90/format,jpg/watermark,image_YnVndWNtcy9sb2dvLWRhcmsucG5nP3gtb3NzLXByb2Nlc3M9aW1hZ2UvcmVzaXplLFBfNjI,g_se,x_10,y_10");
            // String markedUrl = url + watermark;

            urlData.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("图片上传错误", e);
        }

        logger.info("urlData = {}", urlData);
        return urlData;
    }

    @Override
    public boolean deletePost(String appKey, String postid, String username, String password, boolean publish) throws XmlRpcException {
        // logger.info("metaWeblog.editPost -> postid: {}, post: {}", postid, JSON.toJSONString(post));
        logger.info("metaWeblog.editPost -> postid: {}", postid);

        Map<String, Object> rtnResult = isValid(username, password);

        boolean flag = false;
        try {
            flag = getPostService().deletePostById(Integer.valueOf(postid));
            logger.info("flag = {}", flag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }
        return flag;
    }
}
