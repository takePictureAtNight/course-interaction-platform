/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : course_resource_platform

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 21/03/2023 17:58:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for download_admin
-- ----------------------------
DROP TABLE IF EXISTS `download_admin`;
CREATE TABLE `download_admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upload_time` date NOT NULL,
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of download_admin
-- ----------------------------

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_permission`;
CREATE TABLE `rbac_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `controller` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES (1, '管理员');
INSERT INTO `rbac_role` VALUES (2, '教师');
INSERT INTO `rbac_role` VALUES (3, '学生');

-- ----------------------------
-- Table structure for rbac_role_has_permissions
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_has_permissions`;
CREATE TABLE `rbac_role_has_permissions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色关联权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rbac_role_has_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '173396b70f4aad597b4cfd9130f33f7b' COMMENT '密码',
  `user_id` int NOT NULL COMMENT 'user_info表id',
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12332112##1%' COMMENT '密码MD5盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仅该表中的用户可登录系统' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES (1, 'admin', '173396b70f4aad597b4cfd9130f33f7b', 1, '12332112##1%');
INSERT INTO `rbac_user` VALUES (2, 'teacher', '173396b70f4aad597b4cfd9130f33f7b', 2, '12332112##1%');
INSERT INTO `rbac_user` VALUES (3, 'student', '173396b70f4aad597b4cfd9130f33f7b', 3, '12332112##1%');

-- ----------------------------
-- Table structure for rbac_user_has_roles
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_has_roles`;
CREATE TABLE `rbac_user_has_roles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT 'user_info表id',
  `role_id` int NOT NULL COMMENT 'rbac_role表id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rbac_user_has_roles
-- ----------------------------

-- ----------------------------
-- Table structure for tab_case_table
-- ----------------------------
DROP TABLE IF EXISTS `tab_case_table`;
CREATE TABLE `tab_case_table`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习社区',
  `internship_begintime` datetime NULL DEFAULT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime NULL DEFAULT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作地点',
  `participants` int NULL DEFAULT NULL COMMENT '参与人员',
  `case_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '案例名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务对象',
  `case_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '案例类型',
  `uploadresource_date` datetime NULL DEFAULT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传资源文件url',
  `type` int NULL DEFAULT NULL COMMENT '1代表典型个案，2代表小组活动案例',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int NULL DEFAULT NULL COMMENT '逻辑删除，未删除未0，已删除未1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1029 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tab_case_table
-- ----------------------------
INSERT INTO `tab_case_table` VALUES (1011, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1223', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1012, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1013, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1014, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1015, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-20202303200022026151a8cf-5637-4b2c-a74d-d60b5d42f4bf.docx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1016, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-2020230320002203bae79e08-5136-48ee-8faa-8114956e9ec0.doc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-2020230320003421790c4850-959c-4850-8a3b-e609c954829d.docx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1018, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-20202303200034217d8ec9dd-c21f-40b0-bd54-fe2336b17f04.doc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1019, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/filesstatic/files/2023-03-20202303200035494944a722-a38b-4224-b01e-2b2818aedae7.docx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1020, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/filesstatic/files/2023-03-2020230320003549ff55727f-6f7a-4e47-a222-7bf76dd91af7.doc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1021, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20202303200040516b6fceda-12d8-429e-b861-f0fb1cae5a0a.doc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1022, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-2020230320004254f7a8fc98-ae0c-447e-a32b-c325fe7bf956.doc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` VALUES (1023, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-2020230320005632453e2c3e-5aad-4372-a1ef-2c568ac15617.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1024, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/20230320005924c2811254-b142-456c-aeb1-8e9db546f7f6.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/202303200101435049ec1e-8961-43c2-b14f-04b7ec3a8bc2.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1026, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/20230320194341bc638671-e48b-4f23-8360-0c8d6c660ed5.doc', 1, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1027, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '社区居民赋能服务', NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/202303201952230d333020-df83-4816-90ab-5db8143c672e.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1028, '承泽园', '2023-03-23 15:06:20', NULL, NULL, NULL, NULL, NULL, NULL, '脆弱人群关怀服务', '2023-03-20 19:56:26', '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/2023032019562628fb3716-6767-4460-a6c2-85a9ad32653b.doc', 1, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc', NULL);
INSERT INTO `tab_case_table` VALUES (1029, '承泽园', '2023-02-08 15:10:47', NULL, NULL, NULL, NULL, NULL, NULL, '脆弱人群关怀服务', '2023-03-21 12:26:23', '/C:/Users/26675/Desktop/meiyong/course-interaction-platform/course-resourse/target/classes/static/files/2023-03-21/202303211226235400d6d4-ec92-4694-a6af-42d76cb8ca18.docx', 2, NULL, NULL, '人口研究所社工实习课程资源交互平台——20230307.docx', NULL);
INSERT INTO `tab_case_table` VALUES (1030, '承泽园', '2023-03-21 15:20:52', NULL, NULL, NULL, NULL, NULL, NULL, '脆弱人群关怀服务', '2023-03-21 14:33:01', '/C:/Users/26675/Desktop/meiyong/course-interaction-platform/course-resourse/target/classes/static/files/2023-03-21/20230321143301e553048f-9721-4b53-8c77-924a7f717606.docx', 1, NULL, NULL, '人口研究所社工实习课程资源交互平台——20230307.docx', NULL);
INSERT INTO `tab_case_table` VALUES (1031, '承泽园', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '社区居民赋能服务', '2023-03-21 14:33:43', '/C:/Users/26675/Desktop/meiyong/course-interaction-platform/course-resourse/target/classes/static/files/2023-03-21/20230321143342cca376da-e783-47d6-8a9a-40222513998a.docx', 1, NULL, NULL, '1.docx', NULL);
INSERT INTO `tab_case_table` VALUES (1032, '承泽园', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '社区居民赋能服务', '2023-03-21 14:33:43', '/C:/Users/26675/Desktop/meiyong/course-interaction-platform/course-resourse/target/classes/static/files/2023-03-21/20230321143342ca90b81b-27c2-4736-9e40-ac1002ddb37d.docx', 1, NULL, NULL, '人口研究所社工实习课程资源交互平台——20230307.docx', NULL);
INSERT INTO `tab_case_table` VALUES (1033, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '脆弱人群关怀服务', '2023-03-21 14:34:06', '/C:/Users/26675/Desktop/meiyong/course-interaction-platform/course-resourse/target/classes/static/files/2023-03-21/20230321143406dd1c65b3-41c7-4a35-8265-77f7ac4feb6e.docx', 1, NULL, NULL, '1.docx', NULL);

-- ----------------------------
-- Table structure for tab_electronic_journal
-- ----------------------------
DROP TABLE IF EXISTS `tab_electronic_journal`;
CREATE TABLE `tab_electronic_journal`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习社区',
  `internship_begintime` datetime NULL DEFAULT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime NULL DEFAULT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作地点',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '篇名',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键词',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务对象',
  `uploadresource_date` datetime NULL DEFAULT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传资源文件url',
  `type` int NULL DEFAULT NULL COMMENT '1代表个人电子期刊，2代表小组电子期刊',
  `create_by` int NULL DEFAULT NULL COMMENT '电子期刊上传者id',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int NULL DEFAULT NULL COMMENT '逻辑删除，未删除未0，已删除未1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tab_electronic_journal
-- ----------------------------
INSERT INTO `tab_electronic_journal` VALUES (1, '中关园', '2023-03-24 17:01:30', NULL, NULL, NULL, '123', '刘彧', NULL, NULL, 1, NULL, NULL, NULL, 0);
INSERT INTO `tab_electronic_journal` VALUES (2, '中关园', '2023-04-01 16:58:35', NULL, NULL, NULL, '234', '模糊搜索', NULL, NULL, 2, NULL, NULL, NULL, 0);
INSERT INTO `tab_electronic_journal` VALUES (3, '中关园', '2023-03-21 16:59:22', '2023-05-07 17:00:23', NULL, NULL, '345', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tab_product_design
-- ----------------------------
DROP TABLE IF EXISTS `tab_product_design`;
CREATE TABLE `tab_product_design`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `design_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设计名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务对象',
  `product_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类别',
  `instructor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指导老师',
  `uploadresource_date` datetime NULL DEFAULT NULL COMMENT '上传资料日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传资源文件url',
  `status` int NULL DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int NULL DEFAULT NULL COMMENT '逻辑删除，未删除未0，已删除未1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tab_product_design
-- ----------------------------

-- ----------------------------
-- Table structure for tab_project_report
-- ----------------------------
DROP TABLE IF EXISTS `tab_project_report`;
CREATE TABLE `tab_project_report`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键词',
  `project_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目类别',
  `instructor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '指导老师',
  `uploadresource_date` datetime NULL DEFAULT NULL COMMENT '上传资料日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传资源文件url',
  `status` int NULL DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int NULL DEFAULT NULL COMMENT '逻辑删除，未删除未0，已删除未1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tab_project_report
-- ----------------------------

-- ----------------------------
-- Table structure for tab_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `tab_sys_log`;
CREATE TABLE `tab_sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_sys_log
-- ----------------------------
INSERT INTO `tab_sys_log` VALUES (1, NULL, '删除', 'com.peking.courseresourse.controller.CaseTableController.delete()', '1', 92, '127.0.0.1', '2023-03-15 23:49:53');

-- ----------------------------
-- Table structure for tab_weekly_reportre_cords
-- ----------------------------
DROP TABLE IF EXISTS `tab_weekly_reportre_cords`;
CREATE TABLE `tab_weekly_reportre_cords`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习社区',
  `internship_begintime` datetime NULL DEFAULT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime NULL DEFAULT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作地点',
  `weeklyreport_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '周报名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关键字',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务对象',
  `uploadresource_date` datetime NULL DEFAULT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传资源文件url',
  `status` int NULL DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int NULL DEFAULT NULL COMMENT '逻辑删除，未删除未0，已删除未1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tab_weekly_reportre_cords
-- ----------------------------
INSERT INTO `tab_weekly_reportre_cords` VALUES (1, '燕北园', '2023-03-21 16:43:54', '2023-04-08 16:43:58', NULL, '000', '5', '模糊搜索', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tab_weekly_reportre_cords` VALUES (2, '燕园街道', '2023-04-01 16:44:24', '2023-05-05 16:44:29', NULL, '111', '5', '刘彧', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tab_weekly_reportre_cords` VALUES (3, '燕园街道', '2022-10-05 16:45:02', '2023-03-21 16:45:06', NULL, '222', '6', '刘刘彧彧', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tab_weekly_reportre_cords` VALUES (4, '燕北园', '2023-03-23 16:46:06', '2023-03-30 16:46:09', NULL, '222', '6', NULL, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_extend_info
-- ----------------------------
DROP TABLE IF EXISTS `user_extend_info`;
CREATE TABLE `user_extend_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `extend_id` int NOT NULL,
  `extend_table` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_extend_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
