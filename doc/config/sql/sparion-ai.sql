SET FOREIGN_KEY_CHECKS = 0;
SET NAMES utf8mb4;
-- ai_chat DDL
CREATE TABLE `ai_chat` (`id` BIGINT NOT NULL Comment "主键",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
-- ai_chat_message DDL
CREATE TABLE `ai_chat_message` (`id` BIGINT NOT NULL Comment "主键",
`chat_id` BIGINT NULL Comment "会话id",
`request` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "消息",
`response` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "响应",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;
