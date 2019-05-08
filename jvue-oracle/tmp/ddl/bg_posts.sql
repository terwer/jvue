create table BG_POSTS
(
  POST_ID NUMBER(20) not null,
  POST_AUTHOR NUMBER(20) not null,
  POST_DATE DATE not null,
  POST_CONTENT NCLOB not null,
  POST_TITLE NCLOB not null,
  POST_STATUS NVARCHAR2(20) not null,
  COMMENT_STATUS NVARCHAR2(20) not null,
  POST_PASSWORD NVARCHAR2(20) not null,
  POST_NAME NVARCHAR2(200) not null,
  POST_MODIFIED DATE not null,
  POST_PARENT NUMBER(20) not null,
  MENU_ORDER NUMBER(11) not null,
  POST_TYPE NVARCHAR2(20) not null,
  POST_MINE_TYPE NVARCHAR2(100) not null,
  COMMENT_COUNT NUMBER(20) not null,
  POST_FINISHED NUMBER default 0
)
/

comment on table BG_POSTS is '文章表'
/

comment on column BG_POSTS.POST_ID is '文章ID'
/

comment on column BG_POSTS.POST_AUTHOR is '文章作者ID'
/

comment on column BG_POSTS.POST_DATE is '发表时间'
/

comment on column BG_POSTS.POST_CONTENT is '文章内容'
/

comment on column BG_POSTS.POST_TITLE is '文章标题'
/

comment on column BG_POSTS.POST_STATUS is '文章状态'
/

comment on column BG_POSTS.COMMENT_STATUS is '评论状态'
/

comment on column BG_POSTS.POST_PASSWORD is '文章密码'
/

comment on column BG_POSTS.POST_NAME is '文章别名'
/

comment on column BG_POSTS.POST_MODIFIED is '文章修改时间'
/

comment on column BG_POSTS.POST_PARENT is '父类文章ID'
/

comment on column BG_POSTS.MENU_ORDER is '排序'
/

comment on column BG_POSTS.POST_TYPE is '文章类型'
/

comment on column BG_POSTS.POST_MINE_TYPE is '文章媒体类型'
/

comment on column BG_POSTS.COMMENT_COUNT is '评论数'
/

comment on column BG_POSTS.POST_FINISHED is '是否完成'
/