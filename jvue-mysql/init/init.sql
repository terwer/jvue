USE testbugucms;

-- ------------------------------
-- Database
-- ------------------------------
-- 数据库和用户已经指定，不要创建
-- 数据库
-- create database testbugucms default character set utf collate utf8_general_ci;
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
-- 站点选项表
CREATE TABLE options (
    option_id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置ID',
    option_group varchar(64) NOT NULL DEFAULT '' COMMENT '配置组',
    option_name varchar(64) NOT NULL DEFAULT '' COMMENT '配置名',
    option_value longtext NOT NULL COMMENT '配置值',
    PRIMARY KEY (option_id) USING BTREE,
    UNIQUE KEY option_name (option_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='站点配置表';

CREATE TABLE users (
  id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username     VARCHAR(45)     NOT NULL UNIQUE,
  password_md5 VARCHAR(45)     NOT NULL,
  email        VARCHAR(45),
  screen_name  VARCHAR(45),
  created      TIMESTAMP       NOT NULL DEFAULT current_timestamp,
  logged       TIMESTAMP       NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE articles (
  id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title         VARCHAR(255)    NOT NULL,
  created       TIMESTAMP       NOT NULL DEFAULT current_timestamp,
  modified      TIMESTAMP       NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
  content       TEXT,
  author_id     INT,
  hits          INT DEFAULT 0 NOT NULL,
  tags          VARCHAR(255),
  category      VARCHAR(255),
  status        VARCHAR(32),
  type          VARCHAR(32),
  allow_comment BOOLEAN DEFAULT TRUE NOT NULL,
  comment_count INT DEFAULT 0 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comments (
  id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  article_id INT             NOT NULL,
  p_id       INT,
  content    TEXT            NOT NULL,
  name       VARCHAR(255),
  email      VARCHAR(255),
  website    VARCHAR(255),
  agree      INT             NOT NULL DEFAULT 0,
  disagree   INT             NOT NULL DEFAULT 0,
  ip         VARCHAR(255),
  agent      VARCHAR(255),
  created    TIMESTAMP       NOT NULL DEFAULT current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE metas (
  id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)    NOT NULL,
  type VARCHAR(45)     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE middles (
  id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  a_id INT             NOT NULL,
  m_id INT             NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE logs (
  id      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  action  VARCHAR(255),
  data    TEXT,
  message VARCHAR(255),
  type    VARCHAR(255),
  ip      VARCHAR(255),
  user_id INT,
  created TIMESTAMP       NOT NULL DEFAULT current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

INSERT INTO comments (article_id, content, name, email, website, agree, disagree, ip, agent) VALUES ('1', '## 测试评论
这是我的网址[jvue](http://www.terwergreen.com)', 'terwer', '920049380@qq.com', 'http://www.terwergreen.com', '1', '0', '0.0.0.1', '');

INSERT INTO metas (name, type) VALUES ('First', 'tag');
INSERT INTO metas (name, type) VALUES ('New', 'category');

INSERT INTO middles (a_id, m_id) VALUES (1, 1);
INSERT INTO middles (a_id, m_id) VALUES (1, 2);

INSERT INTO posts (title, created, modified, content, author_id, tags, category, status, type)
VALUES ('About', now(), now(), '# About me
### Hello word
这是关于我的页面

* [Github](https://github.com/terwer)
* [知乎](https://www.zhihu.com/people/terwer)

### 也可以设置别的页面
* 比如友链页面', 1, NULL, NULL, 'publish', 'page');
