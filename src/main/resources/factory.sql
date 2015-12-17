# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.25)
# Database: factory
# Generation Time: 2015-12-17 09:21:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_codeitem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_codeitem`;

CREATE TABLE `sys_codeitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `codemap` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `sort` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典详表';



# Dump of table sys_codemap
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_codemap`;

CREATE TABLE `sys_codemap` (
  `code` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';



# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(16) NOT NULL COMMENT '表id',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名',
  `description` varchar(256) DEFAULT NULL COMMENT '菜单说明',
  `url` varchar(256) DEFAULT NULL COMMENT '菜单地址',
  `pid` varchar(16) DEFAULT NULL COMMENT '上级菜单',
  `level` int(4) unsigned DEFAULT NULL COMMENT '菜单级别',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能菜单表';



# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `status` varchar(32) DEFAULT '0' COMMENT '角色状态 1-启用，0-停用',
  `description` varchar(256) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';



# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';



# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '用户类型',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `contact` varchar(64) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(255) DEFAULT NULL COMMENT '用户描述',
  `extrainfo` varchar(64) DEFAULT NULL COMMENT '额外信息',
  `status` varchar(32) NOT NULL DEFAULT '0' COMMENT '状态 1-启用，0-停用',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO `sys_menu` (`id`, `name`, `description`, `url`, `pid`, `level`, `icon`)
VALUES
	('10', '首页', '', 'main', '#', NULL, 'icon-home'),
	('99', '系统管理', NULL, NULL, '#', 1, 'icon-settings'),
	('9901', '菜单管理', NULL, 'system/menu', '99', 2, 'icon-menu'),
	('9902', '用户管理', '', 'system/user', '99', NULL, 'icon-people'),
	('9903', '角色管理', '', 'system/role', '99', NULL, 'icon-user'),
	('9904', '字典管理', '', 'system/dict', '99', NULL, 'icon-book-open');

INSERT INTO `sys_user` (`id`, `name`, `type`, `username`, `password`, `contact`, `email`, `description`, `extrainfo`, `status`, `createtime`)
VALUES
	(1, '管理员', '0', 'admin', '96e79218965eb72c92a549dd5a330112', '', '', '', NULL, '1', '2015-12-17 00:00:00');

INSERT INTO `sys_role` (`id`, `name`, `status`, `description`)
VALUES
	(1, '系统管理员', '1', '系统管理员');
	
INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	(1, 1);

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`)
VALUES
	(1, '10'),
	(1, '99'),
	(1, '9901'),
	(1, '9902'),
	(1, '9903'),
	(1, '9904');


