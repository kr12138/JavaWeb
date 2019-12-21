import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

import homeNav from "@/components/homeNav"
import home from "@/components/home"
import info from "@/components/info"
import settings from "@/components/settings"
import profileNav from "@/components/profileNav"
import login from "@/components/login/login"
import register from "@/components/login/register"
import adminNav from "@/components/admin/adminNav"
import adminDept from "@/components/admin/adminDept"
import adminUser from "@/components/admin/adminUser"
import adminCourse from "@/components/admin/adminCourse"
import adminTeacher from "@/components/admin/adminTeacher";

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
          {path: 'register', component: register, meta: {title: '用户注册'} },
          {path: 'login', component: login, meta: {title: '系统登陆'} },
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
        ],
        meta: {title: '系统管理'},
      },
      {
        path: '/profile',
        component: profileNav,
        children: [
          {path: 'settings', component: settings, meta: {title: '密码更改'} },
        ],
        meta: {title: '系统管理'},
      },

  //   ]
  // }
]

const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})

export default router
