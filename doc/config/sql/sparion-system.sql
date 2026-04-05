SET FOREIGN_KEY_CHECKS = 0;
SET NAMES utf8mb4;
-- sparion-system DDL
CREATE DATABASE `sparion-system`
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;;
use `sparion-system`;
-- sparion-system.sys_menu DDL
CREATE TABLE `sparion-system`.`sys_menu` (`id` BIGINT NOT NULL Comment "主键",
`title` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL Comment "标题",
`parent_id` BIGINT NULL DEFAULT 0 Comment "父级ID",
`order_num` INT NULL DEFAULT 0 Comment "显示顺序",
`path` VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' Comment "路由地址",
`component` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "组件路径",
`query` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "路由参数",
`route_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' Comment "路由名称",
`redirect` VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "重定向地址",
`is_frame` INT NULL DEFAULT 1 Comment "是否为外链（0是 1否）",
`is_cache` INT NULL DEFAULT 0 Comment "是否缓存（0缓存 1不缓存）",
`menu_type` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' Comment "菜单类型（1-目录 2-菜单 3-按钮 4-接口）",
`visible` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' Comment "菜单状态（0显示 1隐藏）",
`status` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' Comment "菜单状态（0正常 1停用）",
`perms` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "权限标识",
`icon` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "菜单图标",
`remark` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' Comment "备注",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "菜单权限表";
-- sparion-system.sys_role DDL
CREATE TABLE `sparion-system`.`sys_role` (`id` BIGINT NOT NULL Comment "主键",
`role_code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "角色编码",
`role_name` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "角色名称",
`role_sort` INT NULL Comment "角色排序",
`status` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "角色状态（0正常 1停用）",
`remark` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "备注",
`can_del` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "能否删除 1-可以删除 0-不能删除",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "角色信息表";
-- sparion-system.sys_role_menu DDL
CREATE TABLE `sparion-system`.`sys_role_menu` (`id` BIGINT NOT NULL Comment "主键",
`role_id` BIGINT NOT NULL Comment "角色ID",
`menu_id` BIGINT NOT NULL Comment "菜单ID",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "角色和菜单关联表";
-- sparion-system.sys_user DDL
CREATE TABLE `sparion-system`.`sys_user` (`id` BIGINT NOT NULL Comment "主键",
`nickname` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "昵称",
`username` VARCHAR(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "用户名",
`password` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "密码",
`status` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "帐号状态（0正常 1停用）",
`email` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "邮箱",
`phone` VARCHAR(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "手机号",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "用户信息表";
-- sparion-system.sys_user_role DDL
CREATE TABLE `sparion-system`.`sys_user_role` (`id` BIGINT NOT NULL Comment "主键",
`user_id` BIGINT NOT NULL Comment "用户ID",
`role_id` BIGINT NOT NULL Comment "角色ID",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` TINYINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "用户和角色关联表";
-- sparion-system.sys_menu DML
INSERT INTO `sparion-system`.`sys_menu` (`id`,`title`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`redirect`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) VALUES (1,'系统管理',0,1,'/system','LAYOUT',NULL,'','/system/index',1,0,'1','0','0',NULL,'BarsOutlined','',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(2,'产品管理',0,1,'/product','LAYOUT',NULL,'','/product/soft/index',1,0,'1','0','0',NULL,'BarsOutlined','',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(101,'用户管理',1,1,'user','/system/user/index',NULL,'',NULL,1,0,'2','0','0','system:user:list',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(102,'角色管理',1,2,'role','/system/role/role',NULL,'',NULL,1,0,'2','0','0','system:role:list',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(103,'菜单管理',1,3,'menu','/system/menu/menu',NULL,'',NULL,1,0,'2','0','0','system:menu:list',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(104,'字典管理',1,4,'dict','/dashboard/console/console',NULL,'',NULL,1,0,'2','0','0','system:dict:list',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',1),(201,'软件管理',2,1,'soft','/product/soft/index',NULL,'',NULL,1,0,'2','0','0','product:soft:list',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1001,'用户查询',101,1,'','',NULL,'',NULL,1,0,'3','0','0','system:user:query',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1002,'用户新增',101,2,'','',NULL,'',NULL,1,0,'3','0','0','system:user:add',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1003,'用户修改',101,3,'','',NULL,'',NULL,1,0,'3','0','0','system:user:edit',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1004,'用户删除',101,4,'','',NULL,'',NULL,1,0,'3','0','0','system:user:remove',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1005,'用户导出',101,5,'','',NULL,'',NULL,1,0,'3','0','0','system:user:export',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1006,'用户导入',101,6,'','',NULL,'',NULL,1,0,'3','0','0','system:user:import',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1007,'重置密码',101,7,'','',NULL,'',NULL,1,0,'3','0','0','system:user:resetPwd',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1008,'角色查询',102,1,'','',NULL,'',NULL,1,0,'3','0','0','system:role:query',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1009,'角色新增',102,2,'','',NULL,'',NULL,1,0,'3','0','0','system:role:add',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1010,'角色修改',102,3,'','',NULL,'',NULL,1,0,'3','0','0','system:role:edit',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1011,'角色删除',102,4,'','',NULL,'',NULL,1,0,'3','0','0','system:role:remove',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0),(1012,'角色导出',102,5,'','',NULL,'',NULL,1,0,'3','0','0','system:role:export',NULL,'',1,'2025-03-09 11:02:50',1,'2025-03-09 11:02:50',0);
-- sparion-system.sys_role DML
INSERT INTO `sparion-system`.`sys_role` (`id`,`role_code`,`role_name`,`role_sort`,`status`,`remark`,`can_del`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) VALUES (1,'super_admin','系统管理员',1,'1','管理员','0',NULL,NULL,1,'2025-03-10 19:40:03',0),(1899763414277169153,'1','2',NULL,'1',NULL,'1',1,'2025-03-12 18:04:29',1,'2025-03-12 18:04:31',1741773871);
-- sparion-system.sys_user DML
INSERT INTO `sparion-system`.`sys_user` (`id`,`nickname`,`username`,`password`,`status`,`email`,`phone`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) VALUES (1,'系统管理员','admin','$2a$10$7YJFAjU9ABabVBUOp9b0teBnC8gxN2EukxEg4ZyqBxVPG5HctzKcK','1','admin@sparion.com',NULL,1,'2025-03-05 20:41:38',1,'2025-03-05 20:41:38',0);
-- sparion-system.sys_user_role DML
INSERT INTO `sparion-system`.`sys_user_role` (`id`,`user_id`,`role_id`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) VALUES (1,1,1,NULL,NULL,NULL,NULL,0);
SET FOREIGN_KEY_CHECKS = 1;
