import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

import homeNav from "@/components/homeNav"
import home from "@/components/home"
import info from "@/components/info"
import register from "@/components/register"
import login from "@/components/login"
import adminNav from "@/components/adminNav"
import adminCourse from "@/components/adminCourse"
import settings from "@/components/settings"
import profileNav from "@/components/profileNav"
import adminDept from "@/components/adminDept"

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
    redirect: '/frontPage/home',/*设置默认指向的路径*/
  },
  // { path: '/#',
  //   children:[

      {
        path: '/frontPage',
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
        component: adminNav,
        children: [
          {path: 'dept', component: adminDept, meta: {title: '学院管理'} },
          {path: 'course', component: adminCourse, meta: {title: '课程管理'} },
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
