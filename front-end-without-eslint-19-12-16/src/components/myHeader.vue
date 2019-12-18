<template>
    <div class="container-fluid">
        <br><br>
        <div class="row">
            <router-link class="myText col-sm-8 col-md-6 col-lg-4 offset-lg-1"
                         to="/frontPage/home" >
                <h3 style="text-shadow: #2c3e50 5px 3px 3px;">
                    教学课后辅导交流系统
                </h3>
            </router-link>
<!--            <button v-if=" name === '' "-->
<!--            class="btn btn-primary d-none d-sm-block col-sm-2 offset-sm-1 col-md-1 offset-md-4 offset-lg-5"-->
<!--            style="box-shadow: black 3px 2px 5px; text-shadow: black 2px 1px 1px;"-->
<!--            data-toggle="modal" data-target="#loginModal">-->
<!--                <span class="glyphicon glyphicon-user"></span>-->
<!--                login-->
<!--            </button>-->
            <!-- v-if=" name !== '' " -->
            <button
             data-toggle="dropdown"
             class="btn btn-primary dropdown-toggle d-none d-sm-block
             col-sm-3 col-md-2 offset-md-3 offset-lg-4 col-xl-2 offset-xl-4"
             style="box-shadow: black 3px 2px 5px; text-shadow: black 2px 1px 1px;">
                <span class="glyphicon glyphicon-user"></span>
                <span v-if=" name !== '' "> {{name}} </span>
                <span v-else> login </span>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item"
                   v-if=" name === '' "
                   data-toggle="modal" data-target="#loginModal"> login </a>
                <div v-if=" name === '' " class="dropdown-divider"></div>
                <a class="dropdown-item"
                   v-if=" name === '' "
                   data-toggle="modal" data-target="#registerModal"> register </a>
<!--                <div v-if=" name === '' " class="dropdown-divider"></div>-->

                <router-link v-if=" name !== '' " class="dropdown-item"
                        to="/profile/settings"> 修改密码
                </router-link>
                <div v-if=" name !== '' " class="dropdown-divider"></div>
                <a v-if=" name !== '' " class="dropdown-item" href="#"> soon 2</a>
                <div v-if=" name !== '' " class="dropdown-divider"></div>
                <a v-if=" name !== '' " class="dropdown-item"
                   data-toggle="modal" data-target="#logoutModal">
                    logout
                </a>
            </div>
        </div>

        <!-- 模态框（Modal） -->
        <loginModal id="loginModal"/>
        <logoutModal id="logoutModal"/>
        <registerModal id="registerModal"/>

    </div>

</template>

<script>
    import loginModal from './loginModal.vue'
    import logoutModal from './logoutModal.vue'
    import registerModal from './registerModal.vue'
    import settings from './settings.vue'
    import eventBus from "@/eventBus";

    export default {
        name: "myHeader",
        components: {
            loginModal,
            logoutModal,
            registerModal,
            settings,
        },
        created() {
            eventBus.$on('loginSuccess',()=> {
                this.name = sessionStorage.getItem('name')
            });
            eventBus.$on('logoutSuccess',()=> {
                this.name = ''
            })
        },
        data() {
            return {
                name: '',
            }
        },
        // watch: {
        //     changeName: function(newname, oldname) {
        //         this.name = newname
        //         sessionStorage.setItem('name', newname)
        //     },
        // },
    }
</script>

<style scoped>

    /*@import "../../node_modules/bootstrap/dist/css/bootstrap.css";*/

    a { text-decoration: none; }
    a:hover { text-decoration: none; }

    .myText {
        font-family: Consolas, Inconsolata, "微软雅黑";
        font-size: 30px;
        color: #ddeeee;
    }
    button {
        background-color: #247cc9;
    }
    .d-none {
        text-emphasis: #ddeeee;
        color: #ddeeee;
    }
    .container-fluid {
        height: 130px;
        background-color: #1c5f9c;
        margin: 0 auto;
    }

</style>