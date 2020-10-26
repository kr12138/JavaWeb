<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark">
            <a class="navbar-brand glyphicon glyphicon-bookmark w-25" href="/#/student"
               style="text-shadow: black 5px 3px 8px;"> 学生 </a>
            <button class="navbar-toggler w-25" type="button"
                    style="box-shadow: black 3px 2px 3px;"
                    data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav container-fluid">

                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/dept"> 搜索学院
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/teacher"> 搜索教师
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/course"> 搜索课程
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/question"> 搜索提问
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/newQuestion"> 新建提问
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/student/myQuestions"> 我的提问
                        </router-link>
                    </div>

                    <div class="nav-item w-25">
                        <router-link class="nav-link d-block d-sm-none"
                                     style="text-shadow: black 5px 3px 5px;"
                                     to="/student/"> soon
                        </router-link>
                    </div>

                </div>
            </div>

        </nav> <br>

        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: "studentNav"
    }
</script>

<style scoped>
    nav {
        margin-top: 0;
        background-color: #2c3e50;
        /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#275a84+0,275a84+5,2c3e50+100 */
        background: rgb(39,90,132); /* Old browsers */
        background: -moz-linear-gradient(left,  rgba(39,90,132,1) 0%, rgba(39,90,132,1) 5%, rgba(44,62,80,1) 100%); /* FF3.6-15 */
        background: -webkit-linear-gradient(left,  rgba(39,90,132,1) 0%,rgba(39,90,132,1) 5%,rgba(44,62,80,1) 100%); /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(to right,  rgba(39,90,132,1) 0%,rgba(39,90,132,1) 5%,rgba(44,62,80,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#275a84', endColorstr='#2c3e50',GradientType=1 ); /* IE6-9 */

        box-shadow: slategray 0 3px 5px;
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
</style>