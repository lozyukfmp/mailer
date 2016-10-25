CREATE DATABASE IF NOT EXISTS `mailer` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE IF NOT EXISTS `mailer`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` enum('ADMIN','USER') NOT NULL,
  `user_status` enum('BANNED','ACTIVE') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login_UNIQUE` (`user_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS 'mailer'.'user_role' (
  'user_role_id' INT PRIMARY KEY AUTO_INCREMENT,
  'user_id' INT,
  'role' ENUM('ADMIN', 'USER') DEFAULT 'USER',
  CONSTRAINT user_role_user_id_fk FOREIGN KEY ('user_role_id') REFERENCES user ('id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mailer`.`posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_date` varchar(45) NOT NULL,
  `post_text` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_post_id_idx` (`user_id`),
  CONSTRAINT `user_post_id` FOREIGN KEY (`user_id`) REFERENCES `user` (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mailer`.`comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_text` varchar(500) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `post_id_idx` (`post_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mailer`.`likes` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `like_user_id_idx` (`user_id`),
  CONSTRAINT `like_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `mailer`.`user_credentials` (
  `user_id` int(11) NOT NULL,
  `user_firstname` varchar(45) NOT NULL,
  `user_secondname` varchar(45) NOT NULL,
  `user_thirdname` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_info_id_idx` (`user_id`),
  CONSTRAINT `user_info_id` FOREIGN KEY (`user_id`) REFERENCES `user` (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;