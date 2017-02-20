# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.15)
# Database: ss_panel
# Generation Time: 2017-02-20 16:02:10 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sp_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sp_config`;

CREATE TABLE `sp_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_` varchar(128) NOT NULL DEFAULT '',
  `value_` varchar(2000) NOT NULL DEFAULT '',
  `created_at` int(10) NOT NULL,
  `updated_at` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sp_config` WRITE;
/*!40000 ALTER TABLE `sp_config` DISABLE KEYS */;

INSERT INTO `sp_config` (`id`, `key_`, `value_`, `created_at`, `updated_at`)
VALUES
	(1,'analytics_code','',147483647,0),
	(2,'home_code','',147483647,0),
	(3,'app_name','ssp',147483647,0),
	(4,'user_index','',147483647,0),
	(5,'user_node','',147483647,0);

/*!40000 ALTER TABLE `sp_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sp_email_verify
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sp_email_verify`;

CREATE TABLE `sp_email_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(32) NOT NULL,
  `token` varchar(64) NOT NULL,
  `expire_at` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sp_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sp_log`;

CREATE TABLE `sp_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  `msg` text NOT NULL,
  `created_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sp_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sp_user`;

CREATE TABLE `sp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) CHARACTER SET utf8mb4 NOT NULL,
  `email` varchar(32) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `passwd` varchar(16) NOT NULL,
  `t` int(11) NOT NULL DEFAULT '0',
  `u` bigint(20) NOT NULL,
  `d` bigint(20) NOT NULL,
  `transfer_enable` bigint(20) NOT NULL,
  `port` int(11) NOT NULL,
  `protocol` varchar(32) NOT NULL DEFAULT 'origin',
  `obfs` varchar(32) NOT NULL DEFAULT 'plain',
  `switched` tinyint(4) NOT NULL DEFAULT '1',
  `enable` tinyint(4) NOT NULL DEFAULT '1',
  `type` tinyint(4) NOT NULL DEFAULT '1',
  `last_get_gift_time` int(11) NOT NULL DEFAULT '0',
  `last_check_in_time` int(11) NOT NULL DEFAULT '0',
  `last_rest_pass_time` int(11) NOT NULL DEFAULT '0',
  `reg_date` int(10) NOT NULL,
  `invite_num` int(8) NOT NULL DEFAULT '0',
  `is_admin` int(2) NOT NULL DEFAULT '0',
  `ref_by` int(11) NOT NULL DEFAULT '0',
  `expire_time` int(11) NOT NULL DEFAULT '0',
  `method` varchar(64) NOT NULL DEFAULT 'rc4-md5',
  `is_email_verify` tinyint(4) NOT NULL DEFAULT '0',
  `reg_ip` varchar(128) NOT NULL DEFAULT '127.0.0.1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `port` (`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sp_user` WRITE;
/*!40000 ALTER TABLE `sp_user` DISABLE KEYS */;

INSERT INTO `sp_user` (`id`, `user_name`, `email`, `pass`, `passwd`, `t`, `u`, `d`, `transfer_enable`, `port`, `protocol`, `obfs`, `switched`, `enable`, `type`, `last_get_gift_time`, `last_check_in_time`, `last_rest_pass_time`, `reg_date`, `invite_num`, `is_admin`, `ref_by`, `expire_time`, `method`, `is_email_verify`, `reg_ip`)
VALUES
	(1,'user','sp3@sspanel.dev','9003d1df225b4d3820015070385194c8','123gxopp',1460651176,0,0,21474836480,21567,'origin','plain',1,1,7,0,1460209591,0,147483647,46,1,0,1456593755,'rc4-md5',0,'127.0.0.1'),
	(2,'biezhi','921293209@qq.com','93279e3308bdbbeed946fc965017f67a','fjOOJYVV',0,0,0,123731968,21568,'origin','plain',1,1,1,0,1487518740,0,1487493132,10,1,0,0,'aes-192-cfb',0,'127.0.0.1');

/*!40000 ALTER TABLE `sp_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ss_checkin_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_checkin_log`;

CREATE TABLE `ss_checkin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `checkin_at` int(11) NOT NULL,
  `traffic` int(11) NOT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ss_checkin_log` WRITE;
/*!40000 ALTER TABLE `ss_checkin_log` DISABLE KEYS */;

INSERT INTO `ss_checkin_log` (`id`, `user_id`, `checkin_at`, `traffic`, `created_at`, `updated_at`)
VALUES
	(7,2,1487518740,61865984,NULL,NULL);

/*!40000 ALTER TABLE `ss_checkin_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ss_invite_code
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_invite_code`;

CREATE TABLE `ss_invite_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` int(10) NOT NULL,
  `updated_at` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ss_invite_code` WRITE;
/*!40000 ALTER TABLE `ss_invite_code` DISABLE KEYS */;

INSERT INTO `ss_invite_code` (`id`, `code`, `user_id`, `created_at`, `updated_at`)
VALUES
	(2,'6ra56nd8i8jvtosup3rt8g3fdc',2,1487495290,1487495290),
	(3,'6o0c5iu67ih6sqoft0c77o2ovf',2,1487495306,1487495306),
	(4,'1ril956q0ogsore9c5hrnhsfgs',2,1487495306,1487495306),
	(5,'6mhi57prtcgkcpk2pc3dbfn8g4',2,1487495306,1487495306),
	(6,'7tkssr0jfujnnou8kvnnuqtodu',2,1487495306,1487495306),
	(7,'7bacdino5ahcarpcvh0ujcsit6',2,1487495306,1487495306),
	(8,'um027gmohsjhaq7ub2200cln5d',2,1487495306,1487495306),
	(9,'o4bodocku2jfno8s432ns7ll7k',2,1487495306,1487495306),
	(10,'uq4bv2ln1qh1iq7ir75hjiri7a',2,1487495306,1487495306),
	(11,'ujfamp727qi05qrlfra7fnru8b',2,1487495306,1487495306),
	(12,'u2o4111l1vkgjcrr0konb11e3hri',0,1487511750,1487511750),
	(13,'u25iv9gs2mjsipsrorsbg8t4ogv2',0,1487511750,1487511750),
	(14,'u27ekum8qluige4p70ten56gi0nd',0,1487511750,1487511750),
	(15,'u257aedqhv88gu7ohg3booehl6fm',0,1487511750,1487511750),
	(16,'u24vq5tdc7i6ibkr3hqocb6p80uk',0,1487511750,1487511750),
	(17,'u2q9aoapsfuahkfos32p2r0r618t',0,1487511750,1487511750),
	(18,'u2s7r4pcv2g2ioqr99uc4e659i0q',0,1487511750,1487511750),
	(19,'u2pps149u5jej7fo4smn53j9jgaa',0,1487511750,1487511750),
	(20,'u27h1q4oq964gckrl07r8s1oi4pv',0,1487511750,1487511750),
	(21,'u2otl7i6rbrug5lrdub1pman1igm',0,1487511750,1487511750),
	(22,'u2rq3lm81krigaapek41aqqghuar',0,1487511750,1487511750),
	(23,'u2vl2sa0c8gkjigpjgo717asc4uh',0,1487511750,1487511750),
	(24,'u21fngvou5ccheoq3604rreifv1m',0,1487511750,1487511750),
	(25,'u20udkpu75nqi1eo3l6rue6g8t0i',0,1487511750,1487511750),
	(26,'u24sjjavk6tajfvqivtspjc5d0ll',0,1487511750,1487511750),
	(27,'u2pdjbh81e7gjijpac60387o2720',0,1487511750,1487511750),
	(28,'u2622phri3rghq6pvleqsqcn16hm',0,1487511750,1487511750),
	(29,'u2s5cciq78ugiqio9f7ff449rhmk',0,1487511750,1487511750),
	(30,'u24be9s38lnuialqu3dmipldcr3c',0,1487511750,1487511750),
	(31,'u227gfsvhhksgjtpi2gnhu0os3t6',0,1487511750,1487511750);

/*!40000 ALTER TABLE `ss_invite_code` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ss_node
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_node`;

CREATE TABLE `ss_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `type` int(3) NOT NULL,
  `server` varchar(128) NOT NULL,
  `method` varchar(64) NOT NULL,
  `custom_method` tinyint(1) NOT NULL DEFAULT '0',
  `traffic_rate` float NOT NULL DEFAULT '1',
  `info` varchar(128) NOT NULL,
  `status` varchar(128) NOT NULL,
  `offset` int(11) NOT NULL DEFAULT '0',
  `sort` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `ss_node` WRITE;
/*!40000 ALTER TABLE `ss_node` DISABLE KEYS */;

INSERT INTO `ss_node` (`id`, `name`, `type`, `server`, `method`, `custom_method`, `traffic_rate`, `info`, `status`, `offset`, `sort`)
VALUES
	(1,'name3',1,'server','rc4',1,0.5,'info','ok',0,1);

/*!40000 ALTER TABLE `ss_node` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ss_node_info_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_node_info_log`;

CREATE TABLE `ss_node_info_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` int(11) NOT NULL,
  `uptime` float NOT NULL,
  `load` varchar(32) NOT NULL,
  `log_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table ss_node_online_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_node_online_log`;

CREATE TABLE `ss_node_online_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` int(11) NOT NULL,
  `online_user` int(11) NOT NULL,
  `log_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table ss_password_reset
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ss_password_reset`;

CREATE TABLE `ss_password_reset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(32) NOT NULL,
  `token` varchar(128) NOT NULL,
  `init_time` int(11) NOT NULL,
  `expire_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ss_password_reset` WRITE;
/*!40000 ALTER TABLE `ss_password_reset` DISABLE KEYS */;

INSERT INTO `ss_password_reset` (`id`, `email`, `token`, `init_time`, `expire_time`)
VALUES
	(1,'921293209@qq.com','8ce60a35e61549bda3642621c30aacd4',1487520368,1487606768),
	(6,'921293209@qq.com','1c063e69589a405fb218709ed3393551',1487602391,1487688791);

/*!40000 ALTER TABLE `ss_password_reset` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_token`;

CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(256) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` int(11) NOT NULL,
  `expire_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user_traffic_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_traffic_log`;

CREATE TABLE `user_traffic_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `u` int(11) NOT NULL,
  `d` int(11) NOT NULL,
  `node_id` int(11) NOT NULL,
  `rate` float NOT NULL,
  `traffic` varchar(32) NOT NULL,
  `log_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_traffic_log` WRITE;
/*!40000 ALTER TABLE `user_traffic_log` DISABLE KEYS */;

INSERT INTO `user_traffic_log` (`id`, `user_id`, `u`, `d`, `node_id`, `rate`, `traffic`, `log_time`)
VALUES
	(105,1,10255,10255,1,1,'1MB',1111111111);

/*!40000 ALTER TABLE `user_traffic_log` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
