<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark">
            <a class="navbar-brand glyphicon glyphicon-tower w-25" href="#" style="text-shadow: black 5px 3px 8px;">管理</a>
            <button class="navbar-toggler w-25" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <div class="navbar-nav container-fluid">

                    <div class="nav-item w-25">
                        <router-link to="/admin/course">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> 修改课程 </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/admin/">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> soon2 </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/admin/">
                            <a class="nav-link" style="text-shadow: black 5px 3px 5px;"> soon3 </a>
                        </router-link>
                    </div>
                    <div class="nav-item w-25">
                        <router-link to="/admin/">
                            <a class="nav-link d-block d-sm-none" style="text-shadow: black 5px 3px 5px;"> soon4 </a>
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