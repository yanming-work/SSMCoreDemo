# SSMCoreDemo
Spring 5 +SpringMvc 5 + mybatis 3.4.6 + pagehelper 5.1.4 + druid 1.1.10

JDK 1.8 +(Weblogic 12 /Tomcat 8+) +(Oracle / Mysql)

注意 pagehelper 高版本配置与低版本不一样

统一接口返回对象 ApiReturnObj  
{
code: 1,
msg: "成功",
datas: 
}

统一日志记录Spring Aop 

WebLogAspect

支持跨域情况 cors 
MyCorsFilter


进过修改mybatis-generator-core  1.3.6 得到 mybatis-generator-core-gavin 自动生成需要的 model,dao,xml,service



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123' COMMENT '密码',
  `alive` int(2) NULL DEFAULT NULL COMMENT '是否活跃状态，1为活跃状态，0为冻结状态',
  `role` int(2) UNSIGNED NULL DEFAULT NULL COMMENT '用户身份，1为管理员，0为一般用户',
  `d` double(10, 2) NULL DEFAULT NULL,
  `t` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', 'admin', 1, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
