DROP TABLE `user`;

CREATE TABLE `user` (
	`u_id` BIGINT (20) NOT NULL,
	`password` VARCHAR (50) NOT NULL,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`identity` TINYINT (4) NOT NULL,
	-- 0：管理 1：学生 2：教师
	PRIMARY KEY (`u_id`)
);

DROP TABLE `dept`;

CREATE TABLE `dept` (
	`d_id` INT (20) NOT NULL,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`info` VARCHAR (50) NULL DEFAULT NULL,
	PRIMARY KEY (`d_id`),
	INDEX (`name`)
);

DROP TABLE `course`;

CREATE TABLE `course` (
	`c_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (50) NULL DEFAULT NULL,
	`info` VARCHAR (50) NULL DEFAULT NULL,
	`dept` VARCHAR (50) REFERENCES `dept` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
	-- `dept_id`  int(20) REFERENCES `dept`(`d_id`) ON DELETE CASCADE ON UPDATE CASCADE ,
	PRIMARY KEY (`c_id`),
	INDEX (`dept`) -- INDEX (`dept_id`)
);

DROP TABLE `teaches`;

CREATE TABLE `teaches` (
	`c_id` BIGINT (20) NOT NULL REFERENCES `course` (`c_id`),
	`u_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	PRIMARY KEY (`c_id`, `u_id`)
);

DROP TABLE `teacher`;

CREATE TABLE `teacher` (
	`u_id` BIGINT (20) NOT NULL REFERENCES `user` (`u_id`),
	`name` VARCHAR (50) DEFAULT NULL,
	`prof_title` VARCHAR (50) DEFAULT NULL,
	`info` VARCHAR (50) DEFAULT NULL,
	PRIMARY KEY (`u_id`)
);

DROP TABLE `question`;

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

DROP TABLE `answer`;

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


