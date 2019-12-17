<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark">
            <a class="navbar-brand glyphicon glyphicon-home w-25" href="#" style="text-shadow: black 5px 3px 8px;">首页</a>
            <button class="navbar-toggler w-25" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav container-fluid">
                        <!-- 使用 router-link 组件来导航. -->
                        <!-- 通过传入 `to` 属性指定链接. -->
                        <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
                    <div class="nav-item w-25">
                        <router-link to="/frontPage/home">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> Home </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/frontPage/register">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> Register </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/frontPage/info">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> Info </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/frontPage/login">
                            <a class="nav-link d-block d-sm-none" style="text-shadow: black 5px 3px 5px;"> Login </a>
                        </router-link>
                    </div>
    <!--                    <div class="nav-item w-25">-->
    <!--                        <a class="nav-link" href="#">Link1</a>-->
    <!--                    </div>-->
    <!--                    <div class="nav-item w-25">-->
    <!--                        <a class="nav-link" href="#">Link2</a>-->
    <!--                    </div>-->
    <!--                    <div class="nav-item w-25">-->
    <!--                        <a class="nav-link" href="#">Link3</a>-->
    <!--                    </div>-->
    <!--                    <div class="nav-item d-none d-sm-block">-->
    <!--                        <a class="nav-link" href="#">Link</a>-->
    <!--                    </div>-->
                </div>
            </div>

        </nav> <br>
        <!-- 路由出口 -->
        <!-- 路由匹配到的组件将渲染在这里 -->
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: "homeNav"
    }
</script>

<style scoped>
    nav {
        margin-top: 0;
        background-color: #2c3e50;
    }
    a {
        font-size: 20px;
    }
    a:hover {
        text-decoration: none;
    }
    a::before {
        vertical-align: middle;
        padding-right: 5px;
    }
    /*ul li {*/
    /*    !*padding-left: 150px;*!*/
    /*}*/
</style>