-- 数据库
create database testbugucms default character set utf8mb4 collate utf8mb4_unicode_ci;
-- create database bugucms default character set utf8mb4 collate utf8mb4_unicode_ci;

-- 测试用户
CREATE USER 'test'@'localhost' IDENTIFIED BY 'ABCdef123456!#'; #本地登录
CREATE USER 'test'@'%' IDENTIFIED BY 'ABCdef123456!#'; #远程登录

-- CREATE USER 'prod'@'localhost' IDENTIFIED BY '123456'; #本地登录
-- CREATE USER 'prod'@'%' IDENTIFIED BY '123456'; #远程登录

grant all privileges on testbugucms.* to test@'%' identified by '123456';
REVOKE ALL PRIVILEGES ON `testbugucms`.* FROM 'test'@'%'; 
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER ON `testbugucms`.* TO 'test'@'%';

-- grant all privileges on bugucms.* to prod@'%' identified by "123456"
-- REVOKE ALL PRIVILEGES ON `bugucms`.* FROM 'prod'@'%'; 
-- GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER ON `bugucms`.* TO 'prod'@'%';