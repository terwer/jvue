create table if not exists bg_posts
(
  post_id bigint unsigned auto_increment comment '文章ID'
    primary key,
  post_author bigint unsigned default 0 not null comment '文章作者ID',
  post_date datetime not null comment '发表时间',
  post_content longtext not null comment '文章内容',
  post_title text not null comment '文章标题',
  post_status varchar(20) default 'publish' not null comment '文章状态',
  comment_status varchar(20) default 'open' not null comment '评论状态',
  post_password varchar(20) default '' not null comment '文章密码',
  post_name varchar(200) default '' not null comment '文章别名',
  post_modified datetime not null comment '文章修改时间',
  post_parent bigint unsigned default 0 not null comment '父类文章ID',
  menu_order int default 0 not null comment '排序',
  post_type varchar(20) default 'post' not null comment '文章类型',
  post_mime_type varchar(100) default '' not null comment '文章媒体类型',
  comment_count bigint default 0 not null comment '评论数',
  post_finished int default 0 null comment '是否完成'
)
  comment '文章表' charset=utf8mb4;

create index post_author
  on bg_posts (post_author);

create index post_name
  on bg_posts (post_name);

create index post_parent
  on bg_posts (post_parent);

create index type_status_date
  on bg_posts (post_type, post_status, post_date, post_id);