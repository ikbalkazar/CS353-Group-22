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
