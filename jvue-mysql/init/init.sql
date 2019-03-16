USE testbugucms;

-- ------------------------------
-- Database
-- ------------------------------
-- 数据库和用户已经指定，不要创建
-- 数据库
-- CREATE DATABASE `testbugucms` DEFAULT CHARACTER SET utf8;
-- create database testbugucms default character set utf8 collate utf8_general_ci;
-- 测试用户
-- CREATE USER 'test'@'localhost' IDENTIFIED BY '123456'; #本地登录
-- CREATE USER 'test'@'%' IDENTIFIED BY '123456'; #远程登录
-- CREATE USER 'prod'@'localhost' IDENTIFIED BY '123456'; #本地登录
-- CREATE USER 'prod'@'%' IDENTIFIED BY '123456'; #远程登录
-- grant all privileges on testbugucms.* to test@'%' identified by '123456';
-- REVOKE ALL PRIVILEGES ON `testbugucms`.* FROM 'test'@'%';
-- GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER ON `testbugucms`.* TO 'test'@'%';
-- grant all privileges on bugucms.* to prod@'%' identified by "123456"
-- REVOKE ALL PRIVILEGES ON `bugucms`.* FROM 'prod'@'%';
-- GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER ON `bugucms`.* TO 'prod'@'%';

-- ------------------------------
-- Table Structure
-- ------------------------------
CREATE TABLE `comments` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `post_id` int(11) NOT NULL COMMENT '文章ID',
    `p_id` int(11) DEFAULT NULL COMMENT '父评论id',
    `content` text NOT NULL COMMENT '评论内容',
    `name` varchar(255) DEFAULT NULL COMMENT '名称',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `website` varchar(255) DEFAULT NULL COMMENT '主页',
    `agree` int(11) NOT NULL DEFAULT '0' COMMENT '通过原因',
    `disagree` int(11) NOT NULL DEFAULT '0' COMMENT '拒绝原因',
    `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
    `agent` varchar(255) DEFAULT NULL COMMENT '来源',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='评论表';

CREATE TABLE `logs` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志表',
    `action` varchar(255) DEFAULT NULL COMMENT '操作',
    `data` text COMMENT '数据',
    `message` varchar(255) DEFAULT NULL COMMENT '信息',
    `type` varchar(255) DEFAULT NULL COMMENT '类型',
    `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
    `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

CREATE TABLE `metas` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` varchar(255) NOT NULL COMMENT '属性名',
    `type` varchar(45) NOT NULL COMMENT '属性类型',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='属性表';

CREATE TABLE `middles` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `p_id` int(11) NOT NULL COMMENT '父级属性ID',
   `m_id` int(11) NOT NULL COMMENT '属性ID',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章属性关联表';

CREATE TABLE `options` (
    `option_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置ID',
    `option_group` varchar(64) NOT NULL DEFAULT '' COMMENT '配置组',
    `option_name` varchar(64) NOT NULL DEFAULT '' COMMENT '配置名',
    `option_value` longtext NOT NULL COMMENT '配置值',
    PRIMARY KEY (`option_id`) USING BTREE,
    UNIQUE KEY `option_name` (`option_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='站点配置表';

CREATE TABLE `posts` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
    `name` varchar(100) DEFAULT NULL COMMENT '文章别名',
    `title` varchar(255) NOT NULL,
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `content` text COMMENT '文章内容',
    `author_id` int(11) DEFAULT NULL COMMENT '作者',
    `hits` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
    `tags` varchar(255) DEFAULT NULL COMMENT '标签',
    `category` varchar(255) DEFAULT NULL COMMENT '分类',
    `status` varchar(32) DEFAULT NULL COMMENT '状态',
    `type` varchar(32) DEFAULT NULL COMMENT '类型',
    `allow_comment` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许评论',
    `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章表';

CREATE TABLE `users` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(45) NOT NULL COMMENT '用户名',
    `password_md5` varchar(45) NOT NULL COMMENT '密码',
    `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
    `screen_name` varchar(45) DEFAULT NULL COMMENT '昵称',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `logged` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '是否登录',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ------------------------------
-- Data
-- ------------------------------
-- 初始化站点配置
INSERT INTO options (option_group, option_name, option_value)
VALUES ('siteConfig', 'domain', 'localhost:8081'),
       ('siteConfig', 'weburl', 'http://localhost:8081'),
       ('siteConfig', 'webtheme', 'default'),
       ('siteConfig', 'webname', '远方的灯塔'),
       ('siteConfig', 'webslogen', '专注于服务端技术分享'),
       ('siteConfig', 'keywords', '软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务'),
       ('siteConfig', 'description',
        '远方的灯塔是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎您关注我们。'),
       ('siteConfig', 'debug', 'false'),
       ('siteConfig', 'beianinfo', '粤ICP备18023717号-1');

INSERT INTO users (username, password_md5, email, screen_name)
VALUES ('jvue', '3e6693e83d186225b85b09e71c974d2d', '', 'admin');

INSERT INTO posts (title, created, modified, content, author_id, hits, tags, category, status, type)
VALUES ('Hello world', now(), now(), '
欢迎使用[Jvue](https://github.com/zzzzbw/Jvue)! 这是你的第一篇博客。快点来写点什么吧

```java
public static void main(String[] args){
    System.out.println("Hello world");
}
```

> 想要了解更多详细信息，可以查看[文档](https://github.com/terwer/jvue)。', 1, 0, 'First', 'New', 'publish', 'post');

INSERT INTO comments (post_id, content, name, email, website, agree, disagree, ip, agent) VALUES ('1', '## 测试评论
这是我的网址[jvue](http://www.terwergreen.com)', 'terwer', '920049380@qq.com', 'http://www.terwergreen.com', '1', '0', '0.0.0.1', '');

INSERT INTO metas (name, type) VALUES ('First', 'tag');
INSERT INTO metas (name, type) VALUES ('New', 'category');

INSERT INTO middles (p_id, m_id) VALUES (1, 1);
INSERT INTO middles (p_id, m_id) VALUES (1, 2);

INSERT INTO posts (title, created, modified, content, author_id, tags, category, status, type)
VALUES ('About', now(), now(), '# About me
### Hello word
这是关于我的页面

* [Github](https://github.com/terwer)
* [知乎](https://www.zhihu.com/people/terwer)

### 也可以设置别的页面
* 比如友链页面', 1, NULL, NULL, 'publish', 'page');
