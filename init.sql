
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

INSERT INTO `question` (`q_id`, `c_id`, `student_id`, `teacher_id`, `title`, `content`, `img`, `date`)
VALUES (1, 2, 111, 11, '关于第一范式', '如何判断一个数据库模式是不是第一范式', NULL, '19-12-12 12:12:12');





