<template>
  <div id="app">

    <myHeader/>

    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>

    <br> <adminDept/>

    <myFooter/>

  </div>
</template>
<script>

  import myHeader from './components/myHeader.vue'
  import myFooter from './components/myFooter.vue'
  import adminDept from './components/adminDept.vue'

  export default {
    name: 'app',
    components: {
      myHeader,
      myFooter,
      adminDept,
    },

  }
</script>
<style>


  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    /*background-color: #f5f5ff;*/
    color: #2c3e50;
    margin-top: 0px;
  }


</style>