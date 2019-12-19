<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark">
            <a class="navbar-brand glyphicon glyphicon-tower w-25" href="#"
               style="text-shadow: black 5px 3px 8px;"> 管理 </a>
            <button class="navbar-toggler w-25" type="button"
                    style="box-shadow: black 3px 2px 3px;"
                    data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav container-fluid">

                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/dept"> 修改学院
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/"> 修改教师
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/course"> 修改课程
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/"> soon2
                        </router-link>
                    </div>
                    <div class="nav-item ">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/"> soon3
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link class="nav-link d-block d-sm-none"
                                     style="text-shadow: black 5px 3px 5px;"
                                     to="/admin/"> soon4
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
        name: "adminNav"
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
</style>