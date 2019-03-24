package com.terwergreen.jvueserver.api;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CommentApi
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/24 20:04
 **/
@RequestMapping(value = "api/blog/comment", produces = "application/json;charset=utf-8")
public class CommentApi {
    /**
     * 获取文章的评论
     *
     * @param articleId 文章id
     * @param page      第几页
     * @param limit     每页数量
     * @return {@see Pagination<Comments>}
     */
//    @GetMapping("comment")
//    public RestResponse getArticleComment(@RequestParam Integer articleId, @RequestParam(required = false, defaultValue = "1") Integer page,
//                                          @RequestParam(required = false, defaultValue = FameConsts.PAGE_SIZE) Integer limit) {
//        Page<Comments> comments = commentsService.getCommentsByArticleId(articleId, page, limit);
//        for (Comments comment : comments) {
//            comment.setContent(FameUtil.mdToHtml(comment.getContent()));
//        }
//        return RestResponse.ok(new Pagination<Comments>(comments));
//    }


    /**
     * 发表评论
     *
     * @param articleId 文章id
     * @param pId       父评论id
     * @param content   评论内容
     * @param name      评论用户名
     * @param email     评论用户email
     * @param website   评论用户网址
     * @return {@see RestResponse.ok()}
     */
//    @PostMapping("comment")
//    public RestResponse postComment(@RequestParam Integer articleId, @RequestParam(required = false) Integer pId,
//                                    @RequestParam String content, @RequestParam String name,
//                                    @RequestParam(required = false) String email, @RequestParam(required = false) String website) {
//        Comments comments = new Comments();
//        comments.setArticleId(articleId);
//        comments.setPId(pId);
//        comments.setContent(content);
//        comments.setName(name);
//        comments.setEmail(email);
//        comments.setWebsite(website);
//        comments.setIp(FameUtil.getIp());
//        comments.setAgent(FameUtil.getAgent());
//        commentsService.save(comments);
//
//        //发送邮件提醒
//        CommentDto commentDetail = commentsService.getCommentDetail(comments.getId());
//        emailService.sendEmailToAdmin(commentDetail);
//        if (null != commentDetail.getPComment() && !StringUtils.isEmpty(commentDetail.getPComment().getEmail())) {
//            emailService.sendEmailToUser(commentDetail, commentDetail.getPComment().getEmail());
//        }
//        return RestResponse.ok();
//    }

    /**
     * 顶或踩评论
     *
     * @param commentId 评论id
     * @param assess    {@link Types#AGREE},{@link Types#DISAGREE}
     * @return {@see RestResponse.ok()}
     */
//    @PostMapping("comment/{commentId}/assess")
//    public RestResponse assessComment(@PathVariable Integer commentId, @RequestParam String assess) {
//        commentsService.assessComment(commentId, assess);
//        return RestResponse.ok();
//    }
}
