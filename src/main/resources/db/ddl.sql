CREATE DATABASE IF NOT EXISTS `mailer` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mailer`;

CREATE TABLE `mailer`.`user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mailer`.`user_profile` (
  `firstname` varchar(45) NOT NULL,
  `secondname` varchar(45) NOT NULL,
  `thirdname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_profile_username_uindex` (`username`),
  CONSTRAINT `user_profile_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mailer`.`user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` enum('ROLE_ADMIN','ROLE_USER') DEFAULT 'ROLE_USER',
  `username` varchar(45) DEFAULT NULL,
  UNIQUE KEY `user_role_user_role_id_uindex` (`user_role_id`),
  KEY `user_role_user_username_fk` (`username`),
  CONSTRAINT `user_role_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `mailer`.`posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_date` varchar(45) NOT NULL,
  `post_text` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mailer`.`likes` (
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `like_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mailer`.`comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_text` varchar(500) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `post_id_idx` (`post_id`),
  CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;