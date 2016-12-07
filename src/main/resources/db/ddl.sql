CREATE DATABASE IF NOT EXISTS `mailer`;

USE `mailer`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user` CASCADE;
DROP TABLE IF EXISTS `user_profile` CASCADE;
DROP TABLE IF EXISTS `user_role` CASCADE;
DROP TABLE IF EXISTS `posts` CASCADE;
DROP TABLE IF EXISTS `comments` CASCADE;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `user` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled`  TINYINT(1)  NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_username_uindex` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_profile` (
  `username`   VARCHAR(45) NOT NULL,
  `firstname`  VARCHAR(45) NOT NULL,
  `secondname` VARCHAR(45) NOT NULL,
  `thirdname`  VARCHAR(45) NOT NULL,
  `email`      VARCHAR(45) NOT NULL,
  `image_url`  VARCHAR(100),
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_profile_username_uindex` (`username`),
  CONSTRAINT `user_profile_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_role` (
  `user_role_id` INT(11) NOT NULL                 AUTO_INCREMENT,
  `role`         ENUM ('ROLE_ADMIN', 'ROLE_USER') DEFAULT 'ROLE_USER',
  `username`     VARCHAR(45)                      DEFAULT NULL,
  UNIQUE KEY `user_role_user_role_id_uindex` (`user_role_id`),
  KEY `user_role_user_username_fk` (`username`),
  CONSTRAINT `user_role_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

CREATE TABLE `posts` (
  `post_id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `post_date` VARCHAR(45) NOT NULL,
  `post_text` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(100)         DEFAULT NULL,
  `username`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `posts_user_username_fk` (`username`),
  CONSTRAINT `posts_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;

CREATE TABLE `comments` (
  `comment_id`   INT(11)      NOT NULL AUTO_INCREMENT,
  `post_id`      INT(11)      NOT NULL,
  `comment_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_text` VARCHAR(500) NOT NULL,
  `username`     VARCHAR(45)  NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `post_id_idx` (`post_id`),
  CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comments_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;