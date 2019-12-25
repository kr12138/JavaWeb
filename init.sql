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


INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (111, '李华', '111', 1);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (222, '徐红', '222', 1);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (333, '张杰', '333', 1);


INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (11, 'PDL', '11', 2);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (22, 'YJane', '22', 2);
INSERT INTO `user` (`u_id`,	`name`,	`password`, `identity`)
			VALUES (33, 'LRay', '33', 2);

INSERT INTO `teacher` (`t_id`, `name`, `prof_title`, `info`)
			VALUES (11, '彭登陆', '教授', '擅长网络和数据库');
INSERT INTO `teacher` (`t_id`, `name`, `prof_title`, `info`)
			VALUES (22, '袁酱', '副教授', '擅长数据结构和操作系统');
INSERT INTO `teacher` (`t_id`, `name`, `prof_title`, `info`)
			VALUES (33, '刘日', '讲师', '擅长Java和Web');

			
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (1111, '光电学院', '研究光学、电学、信息工程');
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (2222, '计算机学院', '计算机科学相关、硬件相关');
INSERT INTO `dept` (`d_id`,	`name`,	`info`)
			VALUES (3333, '软件学院', '计算机技术相关、软件相关');

INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (1, '计算机网络', '从物理层到应用层的五层模型', '光电学院');
INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (2, '数据库原理', '关系数据库及其设计', '软件学院');
INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (3, '数据结构', '经典数据结构及其应用', '软件学院');
INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (4, '操作系统', '现代计算机操作系统', '计算机学院');
INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (5, 'Java应用开发', 'Java基础语法及应用', '软件学院');
INSERT INTO `course` (`c_id`, `name`, `info`, `dept`)
			VALUES (6, 'Web应用开发', 'JavaWeb应用', '软件学院');

INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (1, 11);
INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (2, 11);
INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (3, 22);
INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (4, 22);
INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (5, 33);
INSERT INTO `teaches` (`c_id`, `u_id`) VALUES (6, 33);








