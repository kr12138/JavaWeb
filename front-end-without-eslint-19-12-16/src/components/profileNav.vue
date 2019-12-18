<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark">
            <a class="navbar-brand glyphicon glyphicon-user w-25" href="#"
               style="text-shadow: black 5px 3px 8px;"> 用户中心 </a>
            <button class="navbar-toggler w-25" type="button"
                    style="box-shadow: black 3px 2px 3px;"
                    data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav container-fluid">

                    <div class="nav-item w-25">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/profile/settings"> 修改密码
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/profile/"> soon2
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link class="nav-link" style="text-shadow: black 5px 3px 5px;"
                                     to="/profile/"> soon3
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link class="nav-link d-block d-sm-none"
                                     style="text-shadow: black 5px 3px 5px;"
                                     to="/profile/"> soon4
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
        name: "profileNav"
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