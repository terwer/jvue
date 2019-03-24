package com.terwergreen.jvueserver.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 评论 Model
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:27
 **/
@Getter
@Setter
public class Comment {
    /**
     * 所属文章id
     */
    private Integer articleId;

    /**
     * 父评论id
     */
    private Integer pId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 昵称
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String website;

    /**
     * 评论时间
     */
    private Date created;

    /**
     * 赞
     */
    private Integer agree;

    /**
     * 踩
     */
    private Integer disagree;

    /**
     * 评论ip
     */
    private String ip;

    /**
     * 评论agent
     */
    private String agent;
}
