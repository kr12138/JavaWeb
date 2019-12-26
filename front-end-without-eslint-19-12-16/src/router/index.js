import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

import homeNav from "@/components/homeNav"
import home from "@/components/home"
import info from "@/components/info"
import login from "@/components/login/login"
import register from "@/components/login/register"
import adminNav from "@/components/admin/adminNav"
import adminDept from "@/components/admin/adminDept"
import adminUser from "@/components/admin/adminUser"
import adminCourse from "@/components/admin/adminCourse"
import adminTeacher from "@/components/admin/adminTeacher"
import adminTeachesT from "@/components/admin/adminTeachesT"
import adminTeachesC from "@/components/admin/adminTeachesC"
import studentNav from "@/components/student/studentNav"
import studentDept from "@/components/student/studentDept"
import studentCourse from "@/components/student/studentCourse"
import studentTeacher from "@/components/student/studentTeacher"
import studentQuestion from "@/components/student/studentQuestion"
import studentNewQuestion from "@/components/student/studentNewQuestion"
import studentMyQuestions from "@/components/student/studentMyQuestions"
import studentQuestionShow from "@/components/student/studentQuestionShow"
import settings from "@/components/profile/settings"
import profileNav from "@/components/profile/profileNav"
import imageUpload from "@/components/profile/imageUpload"
import teacherNav from "@/components/teacher/teacherNav"
import teacherCourseList from "@/components/teacher/teacherCourseList"
import teacherCourse from "@/components/teacher/teacherCourse";
import teacherQuestion from "@/components/teacher/teacherQuestion";
import teacherQuestionShow from "@/components/teacher/teacherQuestionShow";


Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/',
    redirect: '/frontPage',/*设置默认指向的路径*/
  },
  // { path: '/#',
  //   children:[
      {
        path: '/frontPage',
        redirect: '/frontPage/home',
        component: homeNav,
        children: [
          {path: 'home', component: home, meta: {title: '校园风景'} },
          {path: 'info', component: info, meta: {title: '系统说明'} },
          {path: 'login', component: login, meta: {title: '系统登陆'} },
          {path: 'register', component: register, meta: {title: '用户注册'} },
        ],
        meta: {title: '系统首页'},
      },
      {
        path: '/admin',
        redirect: '/admin/dept',
        component: adminNav,
        children: [
          {path: 'dept', component: adminDept, meta: {title: '学院管理'} },
          {path: 'user', component: adminUser, meta: {title: '用户管理'} },
          {path: 'course', component: adminCourse, meta: {title: '课程管理'} },
          {path: 'teacher', component: adminTeacher, meta: {title: '教师管理'} },
          {path: 'teachesT', component: adminTeachesT, meta: {title: '授课管理'} },
          {path: 'teachesC', component: adminTeachesC, meta: {title: '授课教师管理'} },
        ],
        meta: {title: '管理中心'},
      },
      {
        path: '/student',
        redirect: '/student/course',
        component: studentNav,
        children: [
          {path: 'dept', component: studentDept, meta: {title: '查看学院'} },
          {path: 'course', component: studentCourse, meta: {title: '查看课程'} },
          {path: 'teacher', component: studentTeacher, meta: {title: '查看教师'} },
          {path: 'question', component: studentQuestion, meta: {title: '查看提问'} },
          {path: 'myQuestions', component: studentMyQuestions, meta: {title: '我的提问'} },
          {path: 'newQuestion', component: studentNewQuestion, meta: {title: '新建提问'} },
          {path: 'questionShow', component: studentQuestionShow, meta: {title: '提问详情'} },
        ],
        meta: {title: '学生中心'},
      },
      {
        path: '/teacher',
        redirect: '/teacher/courseList',
        component: teacherNav,
        children: [
          {path: 'course', component: teacherCourse, meta: {title: '搜索课程'} },
          {path: 'question', component: teacherQuestion, meta: {title: '搜索提问'} },
          {path: 'courseList', component: teacherCourseList, meta: {title: '开课列表'} },
          {path: 'questionShow', component: teacherQuestionShow, meta: {title: '提问详情'} },
        ],
        meta: {title: '教师中心'},
      },
      {
        path: '/profile',
        component: profileNav,
        redirect: '/profile/avatar',
        children: [
          {path: 'settings', component: settings, meta: {title: '密码更改'} },
          {path: 'avatar', component: imageUpload, meta: {title: '头像更换'} },
        ],
        meta: {title: '个人中心'},
      },

  //   ]
  // }
]

const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})

export default router
