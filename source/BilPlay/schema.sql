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
		`pic` VARCHAR(256) NOT NULL,
		`genre` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`id`)
);
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('DOTA2', 'Valve\'s most popular, fast paced MOBA game.', 0.0, 'https://www.wannart.com/wp-content/uploads/2018/02/wannart_dota_2-900x580.jpg','MOBA');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('PUBG', 'The most played game on BilPlay.', 19.99, 'https://imgrosetta.mynet.com.tr/file/2397484/2397484-728xauto.jpg','FPS');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('FARCRY 5', 'Dive into the action with Ubisoft\'s latest release of Farcry series.', 14.75, 'https://images.g2a.com/newlayout/600x351/1x1x0/3cf5debbf871/5a6482e75bafe3ffa41d0220','Action');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('Devil May Cry', 'Even the devil himself has tears to fall.', 9.99, 'https://img.bolumsonucanavari.com/images/Upload/002af4ce-28f2-4b2b-ab09-65c9cf01df15.jpg','Action');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('Mount&Blade Warband', 'Warband is the latest release of Turkish developers TaleWorlds Entertainment.', 4.99, 'https://steamcdn-a.akamaihd.net/steam/apps/48700/header.jpg?t=1511540397','RPG');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('Super Mario Kart', 'Relive your childhood memories with this arcade version of Super Mario Kart.', 20, 'http://cdn-static.denofgeek.com/sites/denofgeek/files/styles/main_wide/public/mario_kart_main_45.jpg?itok=8WXONELK','Arcade');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('The Witcher 3', 'Continue your adventures with Geralt of Rivia.', 19.99, 'https://steamcdn-a.akamaihd.net/steam/apps/292030/header.jpg?t=1509077015','RPG');
INSERT INTO `game` (`name`, `description`, `price`,`pic`,`genre`) VALUES ('Battlerite', 'Skip the boring laning phase and jump right into the action with this approach Battlerite taking to MOBA games.', 5, 'https://www.wasdzone.com/wp-content/uploads/2017/06/Battlerite-05-HD.png','MOBA');


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
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 1, 5, 'It is going to devour your soul!');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 2, 6, 'If only Bluehole could better manage the development of this game.');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 3, 8, 'It is FarCry as usual. Just buy it.');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 4, 2, 'Compared to old games of the series, it sucks!');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 5, 10, 'In the name of God just release the Bannerlord already!!!');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 6, 8, 'Ahh the chills you get when you see the intro screen.');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 7, 6, 'I don\'t think Witcher 3 is as good as it could be. Overrated describes it perfectly!');
INSERT INTO `review` (`user_id`, `game_id`, `rating`, `comment`) VALUES (1, 8, 4, 'Well it is certainly not a strategy based game but the talent cap is higher than other MOBAs.');


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
    `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
    `left_at` TIMESTAMP NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`creator_id`) REFERENCES `user`(id),
    FOREIGN KEY (`game_id`) REFERENCES `game`(id)
);

DROP TABLE IF EXISTS `invite`;
CREATE TABLE `invite` (
    `session_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `accepted` INT NOT NULL DEFAULT 0,
    `left_at` TIMESTAMP NULL,
    `accepted_at` TIMESTAMP NULL,
    PRIMARY KEY (`session_id`, `user_id`),
    FOREIGN KEY (`session_id`) REFERENCES `session`(id),
    FOREIGN KEY (`user_id`) REFERENCES `user`(id)
);

SET FOREIGN_KEY_CHECKS = 1;
