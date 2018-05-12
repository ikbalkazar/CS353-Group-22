DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(32) NOT NULL,
	`email` VARCHAR(256) NOT NULL,
	`password` VARCHAR(32) NOT NULL,
	`budget` DOUBLE NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
);
INSERT INTO `user` (`username`, `password`, `email`) VALUES ('admin', 'password', 'admin@gmail.com');
INSERT INTO `user` (`username`, `password`, `email`) VALUES ('co-admin', 'password', 'co-admin@gmail.com');


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
INSERT INTO `game` (`name`, `description`) VALUES ('DOTA', 'The most played game on stream');


