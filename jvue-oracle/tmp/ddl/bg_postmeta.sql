create table BG_POSTMETA
(
	POST_ID NUMBER,
	META_KEY VARCHAR2(100),
	META_VALUE VARCHAR2(500)
)
/

comment on table BG_POSTMETA is '文章附加属性表'
/

comment on column BG_POSTMETA.POST_ID is '文章ID'
/

comment on column BG_POSTMETA.META_KEY is '属性'
/

comment on column BG_POSTMETA.META_VALUE is '取值'
/

