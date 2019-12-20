/*
CREATE TABLE `user` (
	`u_id` BIGINT (20) NOT NULL,
	`password` VARCHAR (50) NOT NULL,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`identity` TINYINT (4) NOT NULL,
	-- 0：管理 1：学生 2：教师
	PRIMARY KEY (`u_id`)
);
CREATE TABLE `dept` (
	`d_id` INT (20) NOT NULL,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`info` VARCHAR (50) NULL DEFAULT NULL,
	PRIMARY KEY (`d_id`),
	INDEX (`name`)
);
CREATE TABLE `course` (
	`c_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`info` VARCHAR (50) NULL DEFAULT NULL,
	`dept` VARCHAR (50) REFERENCES `dept` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
	-- `dept_id`  int(20) REFERENCES `dept`(`d_id`) ON DELETE CASCADE ON UPDATE CASCADE ,
	PRIMARY KEY (`c_id`),
	INDEX (`dept`) -- INDEX (`dept_id`)
);
CREATE TABLE `teaches` (
	`c_id` BIGINT (20) NOT NULL REFERENCES `course` (`c_id`),
	`u_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	PRIMARY KEY (`c_id`, `u_id`)
);
CREATE TABLE `question` (
	`q_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`c_id` BIGINT (20) NOT NULL REFERENCES `course` (`c_id`),
	`student_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	`teacher_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	`title` VARCHAR (50) NOT NULL,
	`content` VARCHAR (200),
	`date` VARCHAR (40),
	`read_by_teacher` bit DEFAULT 0,
	PRIMARY KEY (`q_id`)
);
CREATE TABLE `answer` (
	`a_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`q_id` BIGINT (20) NOT NULL REFERENCES `question` (`q_id`),
	`teacher_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	`content` VARCHAR (200),
	`date` VARCHAR (40),
	`read_by_student` bit DEFAULT 0,
	PRIMARY KEY (`a_id`)
);

-- ALTER TABLE `answer` ADD UNIQUE `index_answer` ON `q_id`(20);
-- ADD INDEX `index_answer` ON `answer`(`q_id`(20));
INSERT INTO `user` (`u_id`,	`name`,	`password`,	`identity`) VALUES (0, 'Admin', 'admin', 0)
*/
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (1111, '光电', '光电结棍');
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (2222, '计院', '光电结不结棍和我计院有关吗');
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (3333, '光电', '计院不结棍跟我软院有关吗');

INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (111, '学生1', '111', 1);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (222, '学生2', '222', 1);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (333, '学生1', '333', 1);

INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (11, '教师1', '11', 2);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (22, '教师2', '22', 2);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (33, '教师3', '33', 2);