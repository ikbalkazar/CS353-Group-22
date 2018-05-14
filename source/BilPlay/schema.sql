SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(32) NOT NULL,
	`firstName` VARCHAR(64),
	`lastName` VARCHAR(64),
	`email` VARCHAR(256) NOT NULL,
	`password` VARCHAR(32) NOT NULL,
	`budget` DOUBLE NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
);
INSERT INTO `user` (`username`, `password`, `email`, `budget`) VALUES ('admin', 'password', 'admin@gmail.com', 50);
INSERT INTO `user` (`username`, `password`, `email`, `budget`) VALUES ('co-admin', 'password', 'co-admin@gmail.com', 100);


DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
    `user_id` INT NOT NULL,
    `friend_id` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`user_id`, `friend_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id),
    FOREIGN KEY (`friend_id`) REFERENCES `user`(id)
);
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (1, 2);


DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL,
    `mode` INT NOT NULL DEFAULT 0,
    `price` DOUBLE NOT NULL DEFAULT 0,
    `description` VARCHAR(1024),
    PRIMARY KEY (`id`)
);
INSERT INTO `game` (`name`, `description`, `price`) VALUES ('DOTA', 'The most played game on stream', 20);


DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
    `user_id` INT NOT NULL,
    `game_id` INT NOT NULL,
    `rating` INT NOT NULL,
    `comment` VARCHAR(256),
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`user_id`, `game_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id),
    FOREIGN KEY (`game_id`) REFERENCES `game`(id)
);
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 1, 5, 'Great game!');


DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
    `user_id` INT NOT NULL,
    `game_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `game_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id),
    FOREIGN KEY (`game_id`) REFERENCES `game`(id)
);

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `friend_id` INT NOT NULL,
    `content` VARCHAR(256),
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id),
    FOREIGN KEY (`friend_id`) REFERENCES `user`(id)
);
INSERT INTO `message` (`user_id`, `friend_id`, `content`) VALUES (1, 2, 'Hey co-admin!');


DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `creator_id` INT NOT NULL,
    `game_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`creator_id`) REFERENCES `user`(id),
    FOREIGN KEY (`game_id`) REFERENCES `game`(id)
);

DROP TABLE IF EXISTS `invite`;
CREATE TABLE `invite` (
    `session_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `accepted` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`session_id`, `user_id`),
    FOREIGN KEY (`session_id`) REFERENCES `session`(id),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id)
);

SET FOREIGN_KEY_CHECKS = 1;
