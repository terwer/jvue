create table bg_postmeta
(
	post_id int null comment '文章ID',
	meta_key varchar(100) null comment '属性',
	meta_value varchar(500) null comment '取值'
)
comment '文章附加属性表';