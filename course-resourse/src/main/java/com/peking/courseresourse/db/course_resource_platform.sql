/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : course_resource_platform

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 21/03/2023 08:41:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for download_admin
-- ----------------------------
DROP TABLE IF EXISTS `download_admin`;
CREATE TABLE `download_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `upload_time` date NOT NULL,
  `status` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of download_admin
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `content` varchar(10000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `status` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notifications
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_permission`;
CREATE TABLE `rbac_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `controller` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `action` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
BEGIN;
INSERT INTO `rbac_role` (`id`, `name`) VALUES (1, '管理员');
INSERT INTO `rbac_role` (`id`, `name`) VALUES (2, '教师');
INSERT INTO `rbac_role` (`id`, `name`) VALUES (3, '学生');
COMMIT;

-- ----------------------------
-- Table structure for rbac_role_has_permissions
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_has_permissions`;
CREATE TABLE `rbac_role_has_permissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色关联权限';

-- ----------------------------
-- Records of rbac_role_has_permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '173396b70f4aad597b4cfd9130f33f7b' COMMENT '密码',
  `user_id` int NOT NULL COMMENT 'user_info表id',
  `salt` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '12332112##1%' COMMENT '密码MD5盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='仅该表中的用户可登录系统';

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
BEGIN;
INSERT INTO `rbac_user` (`id`, `username`, `password`, `user_id`, `salt`) VALUES (1, 'admin', '173396b70f4aad597b4cfd9130f33f7b', 1, '12332112##1%');
INSERT INTO `rbac_user` (`id`, `username`, `password`, `user_id`, `salt`) VALUES (2, 'teacher', '173396b70f4aad597b4cfd9130f33f7b', 2, '12332112##1%');
INSERT INTO `rbac_user` (`id`, `username`, `password`, `user_id`, `salt`) VALUES (3, 'student', '173396b70f4aad597b4cfd9130f33f7b', 3, '12332112##1%');
COMMIT;

-- ----------------------------
-- Table structure for rbac_user_has_roles
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_has_roles`;
CREATE TABLE `rbac_user_has_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT 'user_info表id',
  `role_id` int NOT NULL COMMENT 'rbac_role表id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户权限关联表';

-- ----------------------------
-- Records of rbac_user_has_roles
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_case_table
-- ----------------------------
DROP TABLE IF EXISTS `tab_case_table`;
CREATE TABLE `tab_case_table` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '实习社区',
  `internship_begintime` datetime DEFAULT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime DEFAULT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作地点',
  `participants` int DEFAULT NULL COMMENT '参与人员',
  `case_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '案例名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关键字',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '服务对象',
  `case_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '案例类型',
  `uploadresource_date` datetime DEFAULT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '上传资源文件url',
  `type` int DEFAULT NULL COMMENT '1代表典型个案，2代表小组活动案例',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tab_case_table
-- ----------------------------
BEGIN;
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1011, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1223', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1012, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1013, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1014, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-19', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1015, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-20202303200022026151a8cf-5637-4b2c-a74d-d60b5d42f4bf.docx', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1016, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-2020230320002203bae79e08-5136-48ee-8faa-8114956e9ec0.doc', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-2020230320003421790c4850-959c-4850-8a3b-e609c954829d.docx', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1018, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/files/2023-03-20202303200034217d8ec9dd-c21f-40b0-bd54-fe2336b17f04.doc', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1019, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/filesstatic/files/2023-03-20202303200035494944a722-a38b-4224-b01e-2b2818aedae7.docx', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1020, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/filesstatic/files/2023-03-2020230320003549ff55727f-6f7a-4e47-a222-7bf76dd91af7.doc', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1021, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20202303200040516b6fceda-12d8-429e-b861-f0fb1cae5a0a.doc', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1022, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-2020230320004254f7a8fc98-ae0c-447e-a32b-c325fe7bf956.doc', NULL, NULL, NULL, NULL);
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1023, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-2020230320005632453e2c3e-5aad-4372-a1ef-2c568ac15617.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1024, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/20230320005924c2811254-b142-456c-aeb1-8e9db546f7f6.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/202303200101435049ec1e-8961-43c2-b14f-04b7ec3a8bc2.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1026, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/20230320194341bc638671-e48b-4f23-8360-0c8d6c660ed5.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1027, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/202303201952230d333020-df83-4816-90ab-5db8143c672e.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
INSERT INTO `tab_case_table` (`id`, `internship_community`, `internship_begintime`, `internship_endtime`, `work_location`, `participants`, `case_name`, `keywords`, `service_target`, `case_type`, `uploadresource_date`, `resource_url`, `type`, `status`, `create_by`, `file_name`) VALUES (1028, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2023-03-20 19:56:26', '/Users/liuyu/workspace/java/-course-interaction-platform/course-resourse/target/classes/static/files/2023-03-20/2023032019562628fb3716-6767-4460-a6c2-85a9ad32653b.doc', NULL, NULL, NULL, '2020级《软件需求规格说明书》 模板要.doc');
COMMIT;

-- ----------------------------
-- Table structure for tab_electronic_journal
-- ----------------------------
DROP TABLE IF EXISTS `tab_electronic_journal`;
CREATE TABLE `tab_electronic_journal` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '实习社区',
  `internship_begintime` datetime NOT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime NOT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工作地点',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '篇名',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键词',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务对象',
  `uploadresource_date` datetime NOT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传资源文件url',
  `type` int NOT NULL COMMENT '1代表个人电子期刊，2代表小组电子期刊',
  `create_by` int NOT NULL COMMENT '电子期刊上传者id',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1代表通过审核，0代表待审核',
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tab_electronic_journal
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_product_design
-- ----------------------------
DROP TABLE IF EXISTS `tab_product_design`;
CREATE TABLE `tab_product_design` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `design_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设计名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键字',
  `service_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务对象',
  `product_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品类别',
  `instructor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '指导老师',
  `uploadresource_date` datetime NOT NULL COMMENT '上传资料日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传资源文件url',
  `status` int NOT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tab_product_design
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_project_report
-- ----------------------------
DROP TABLE IF EXISTS `tab_project_report`;
CREATE TABLE `tab_project_report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键词',
  `project_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目类别',
  `instructor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '指导老师',
  `uploadresource_date` datetime NOT NULL COMMENT '上传资料日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传资源文件url',
  `status` int NOT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tab_project_report
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `tab_sys_log`;
CREATE TABLE `tab_sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='系统日志';

-- ----------------------------
-- Records of tab_sys_log
-- ----------------------------
BEGIN;
INSERT INTO `tab_sys_log` (`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `create_date`) VALUES (1, NULL, '删除', 'com.peking.courseresourse.controller.CaseTableController.delete()', '1', 92, '127.0.0.1', '2023-03-15 23:49:53');
COMMIT;

-- ----------------------------
-- Table structure for tab_weekly_reportre_cords
-- ----------------------------
DROP TABLE IF EXISTS `tab_weekly_reportre_cords`;
CREATE TABLE `tab_weekly_reportre_cords` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `internship_community` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '实习社区',
  `internship_begintime` datetime NOT NULL COMMENT '实习开始时间',
  `internship_endtime` datetime NOT NULL COMMENT '实习结束时间',
  `work_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工作地点',
  `weeklyreport_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '周报名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键字',
  `service _target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务对象',
  `uploadresource_date` datetime NOT NULL COMMENT '上传资源日期',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传资源文件url',
  `status` int NOT NULL COMMENT '1代表通过审核，0代表待审核',
  `create_by` int NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tab_weekly_reportre_cords
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_extend_info
-- ----------------------------
DROP TABLE IF EXISTS `user_extend_info`;
CREATE TABLE `user_extend_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `extend_id` int NOT NULL,
  `extend_table` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_extend_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `account` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `tel` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
