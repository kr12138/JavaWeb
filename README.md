# JavaWeb
 Final assigment of Java Web application development./JavaWeb实验课结课大作业

`2019-12-25 我感觉要写不完了……`
`2019-12-26凌晨 被mysql的长字符串乱杀……`
`2019-12-26上午 被base64编码和url编码乱杀……`

## 教学在线留言答疑系统
大学教学中，学生在课后，可以通过该网站提出问题留言，教师登录后可以选择回答。 

### 【功能要求】 

#### 1、 系统分为管理员，学生，教师帐号，其中，学生帐号可以任意新注册，管理员帐号为系统预先设置好，教师帐号由管理员帐号开设。 

​			`User.java, UserRepository.java, UserController.java; login.vue, loginModal.vue, register.vue`

#### 2、 管理员帐号功能： 

​	a) 增删改课程信息，包括课程名称，授课教师，课程内容描述，开课学院。 

​			`Course.java, CourseRepository.java, CourseController.java; adminCourse.vue, adminTeachesC.vue`

​	b) 增删改教师，包括教师名称，所讲授课程（可以有多个），教师职称，教师简介， 

​			`Teacher.java, TeacherRepository.java, TeacherController.java; adminTeacher.vue, adminTeachesT.vue`

​	c) 增删改学院信息。 

​			`Dept.java, DeptRepository.java, DeptController.java; adminDept.vue`

​	d) 删除或修改所有课程交流和留言信息。 

#### 3、 学生帐号功能： 

​	a) 登录后，如果自己的留言问题有老师回答，将显示提示有几条回答，并可以通过连接直接跳转到该回答留言信息。 

​	b) 登录后，每个课程均可以有若干留言问题，可以按学院，教师分类浏览所有课程列表 

​			`DeptController.java, CourseController.java, TeachesController.java, TeacherController.java; studentDept.vue, studentTeacher.vue`

​	c) 可以选择进入某课程的页面，并显示该课程的相关信息和简介，以及该课程的所有留言问题与教师解答（如果没有教师解答，显示未解答）。 

​			`CourseController.java, QuestionController.java; studentCourse.vue, studentAnswer.vue`

​	d) 如果留言很多，可以分页显示。 

​			`QuestionController.java, QuestionRepository.java; studentQuestion.vue`

​	e) 可以提出针对某课程的问题留言。留言包括标题，内容，时间日期。 

​			`QuestionController.java; studentNewQuestion.vue`

​	f) 可以浏览查看当前系统中所有学生的留言提问和教师回答，并可以按课程，教师，学院，以及问题内容的关键字等进行检索。 

​			`QuestionController.java, QuestionRepository.java; studentDept.vue, studentTeacher.vue, studentCourse.vue, studentQuestion.vue`

​	g) 可以查看修改或者删除自己发表的留言。 

​			`QuestionController.java; studentMyQuestions.vue, questionShow.vue`

​	h) 可以上传图片的文件附件 

​			`QuestionController.java; studentNewQuestion.vue`

#### 4、 教师帐号的功能 

​	a) 教师登录后，将提示自己所教授的课程是否有新的未回答的留言并且显示有多少条待回复的问题，可以选择进入某个问题进行回答。 

​	b) 登录后，将显示自己所有讲授的课程列表。并可以选择进入某个课程的页面。 

​	c) 进入课程页面后，可以查看当前的所有问题信息，并进行回答。 

​	d) 可以设置自己的课程只允许某些同学浏览和提问，可以增加删除可以浏览提问该课程的学生 

​	e) 可以修改删除自己发表的任意回答信息 

​	f) 也可以删除某个同学的提问信息 

​	g) 可以上传图片的文件附件

#### 5、 所有帐号都可以修改自己的帐号密码

​			`User.java, UserRepository.java, UserController.java; settings.vue`