SET FOREIGN_KEY_CHECKS = 0;
SET NAMES utf8mb4;
-- res_file DDL
CREATE TABLE `res_file` (`id` BIGINT NOT NULL Comment "主键",
`file_name` VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "文件名称",
`file_suffix` VARCHAR(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "文件后缀名",
`path` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "文件存储路径",
`storage_type` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "存储类型 1-永久 2-临时",
`storage_model` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "存储方式 1-本地 2-minio",
`hash` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "文件hash值",
`file_size` BIGINT NULL Comment "文件大小",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "文件信息表";
-- res_template DDL
CREATE TABLE `res_template` (`id` BIGINT NOT NULL Comment "主键",
`code` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "模板编码",
`name` VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "模板名称",
`file_id` BIGINT NULL Comment "文件id",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "文件模板表";
SET FOREIGN_KEY_CHECKS = 1;
