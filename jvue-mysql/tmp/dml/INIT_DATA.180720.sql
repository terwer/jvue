-- 初始化角色
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '普通用户');

-- 初始化权限
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2', '2');

-- 初始化用户
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$ya3llY31E0vlNTA0Yhxam.IJowaGi2bCW766IdmquneMeIJLBroUW', '小布', '我是超级管理员', 'http://t.cn/RdPXvCZ', '18888888888', 'youweics@sina.com', 'abcdef', '0', '2018-03-28 16:05:59', '这是备注呀');
INSERT INTO `sys_user` VALUES ('2', 'terwer', '123456', '小塔', '', '', '13333333333', '', '', '0', '2018-03-28 16:05:59', '');