import Vue from 'vue'
import App from './App.vue'
import router from './router'

import axios from 'axios'
axios.defaults.headers.common['token'] = sessionStorage['token']
// axios.defaults.headers.common[sessionStorage['token']] = sessionStorage['token']
Object.defineProperty(Vue.prototype, '$axios', {value:axios});

import 'bootstrap'
import "bootstrap/dist/css/bootstrap.css"

import toastr from 'toastr'
import "toastr/build/toastr.css"
Object.defineProperty(Vue.prototype, '$toastr', {value: toastr});

Vue.config.productionTip = false

router.beforeEach((to,from,next) => {
  if (to.meta.title !== '')
    document.title = to.meta.title;
  next()
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
