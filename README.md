# JavaWeb Q&A System
 Final assigment of Java Web application development./JavaWeb实验课结课大作业


## 教学在线留言答疑系统
大学教学中，学生在课后，可以通过该网站提出问题留言，教师登录后可以选择回答。 

### 【功能要求】 

#### 1、 系统分为管理员，学生，教师帐号，其中，学生帐号可以任意新注册，管理员帐号为系统预先设置好，教师帐号由管理员帐号开设。 

​			`User.java, UserRepository.java, UserAdminController.java; login.vue, loginModal.vue, register.vue`

#### 2、 管理员帐号功能： 

​	a) 增删改课程信息，包括课程名称，授课教师，课程内容描述，开课学院。 

​			`Course.java, CourseRepository.java, CourseAdminController.java; adminCourse.vue, adminTeachesC.vue`

​	b) 增删改教师，包括教师名称，所讲授课程（可以有多个），教师职称，教师简介， 

​			`Teacher.java, TeacherRepository.java, TeacherAdminController.java; adminTeacher.vue, adminTeachesT.vue`

​	c) 增删改学院信息。 

​			`Dept.java, DeptRepository.java, DeptAdminController.java; adminDept.vue`

​	d) 删除或修改所有课程交流和留言信息。 

​			`Question.java, QuestionRepository.java, QuestionAdminController.java, Answer.java, AnswerRepository.java, AnswerAdminController.java; adminQuestion.vue, adminAnswer.vue`

#### 3、 学生帐号功能： 

​	a) 登录后，如果自己的留言问题有老师回答，将显示提示有几条回答，并可以通过连接直接跳转到该回答留言信息。 

​			`UserMessageController.java; newMessage.vue, login.vue`

​	b) 登录后，每个课程均可以有若干留言问题，可以按学院，教师分类浏览所有课程列表 

​			`DeptController.java, CourseController.java, TeachesController.java, TeacherController.java; studentDept.vue, studentTeacher.vue`

​	c) 可以选择进入某课程的页面，并显示该课程的相关信息和简介，以及该课程的所有留言问题与教师解答（如果没有教师解答，显示未解答）。 

​			`CourseController.java, QuestionController.java; studentCourse.vue`

​	d) 如果留言很多，可以分页显示。 

​			`QuestionController.java, QuestionRepository.java; studentQuestion.vue`

​	e) 可以提出针对某课程的问题留言。留言包括标题，内容，时间日期。 

​			`QuestionUserController.java; studentNewQuestion.vue`

​	f) 可以浏览查看当前系统中所有学生的留言提问和教师回答，并可以按课程，教师，学院，以及问题内容的关键字等进行检索。 

​			`QuestionController.java, QuestionRepository.java; studentDept.vue, studentTeacher.vue, studentCourse.vue, studentQuestion.vue`

​	g) 可以查看修改或者删除自己发表的留言。 

​			`QuestionController.java; studentMyQuestions.vue, studentQuestionShow.vue`

​	h) 可以上传图片的文件附件 

​			`QuestionController.java; studentNewQuestion.vue`

#### 4、 教师帐号的功能 

​	a) 教师登录后，将提示自己所教授的课程是否有新的未回答的留言并且显示有多少条待回复的问题，可以选择进入某个问题进行回答。 

​			`UserMessageController.java; newMessage.vue, login.vue`

​	b) 登录后，将显示自己所有讲授的课程列表。并可以选择进入某个课程的页面。 

​			`CourseController.java, QuestionController.java; teacherCourseList.vue, teacherCourse.vue`

​	c) 进入课程页面后，可以查看当前的所有问题信息，并进行回答。 

​			`AnswerUserController.java, QuestionIntroController.java; teacherCourse.vue, teacherQuestionShow.vue`

​	d) 可以设置自己的课程只允许某些同学浏览和提问，可以增加删除可以浏览提问该课程的学生 

​			`QuestionAuthAdminRepository.java, QuestionDetailsController.java; teacherQuestionAuth.vue, studentQuestionShow.vue`

​	e) 可以修改删除自己发表的任意回答信息 

​			`AnswerIntroController.java, QuestionController.java; teacherMyAnswers.vue`

​	f) 也可以删除某个同学的提问信息 

​			`AnswerIntroController.java, QuestionController.java; teacherCourse.vue`

​	g) 可以上传图片的文件附件

​			`AnswerController.java; teacherQuestionShow.vue`

#### 5、 所有帐号都可以修改自己的帐号密码

​			`User.java, UserRepository.java, UserLoginController.java; settings.vue`



```
本系统网页前端基于 Vue.js 2.6.10 和 bootstrap 4.4.1 
	开发工具：VSCode 1.41.1 和 IntelliJ IDEA 19.2.2
	使用插件：vue-router 3.1.3 和 axios 0.19.0
	其他工具：npm 6.9.0 和 toastr 2.1.4
```

```
本系统后端控制层基于 Java 10.0.2 和 Springboot 2.2.1 
	开发工具：Postman 7.13.0 和 IntelliJ IDEA 19.2.2
	其他工具：maven 3.6.3 和 fastjson 1.2.61
```

```
本系统后端持久层基于 MySql 8.0.13 和 Hibernate 5.4.8
	开发工具：Navicat 12.0.18 和 IntelliJ IDEA 19.2.2
```
