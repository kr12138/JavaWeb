<template>

    <div>
        <br>
        <h4>
            {{ identity }}登录
        </h4>

        <div class="container"> <br> <br>
    <!--        <form action="/api/user/login" method="post">-->
            <div class="btn-group">
                <button type="button" class="btn btn-info" name="identity"
                        @click=" handleIdentityClick( 0 ) "> 管理 </button>
                <button type="button" class="btn btn-info" name="identity"
                        @click=" handleIdentityClick( 1 ) "> 学生 </button>
                <button type="button" class="btn btn-info" name="identity"
                        @click=" handleIdentityClick( 2 ) "> 教师 </button>
            </div><br><br>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> {{ ID }} </span>
                </span>
                <input type="number" class="form-control" name="id" v-model=" user.id ">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 密码 </span>
                </span>
                <input type="password" class="form-control" name="password" v-model=" user.password" >
            </div> <br> <br>
            <button type="button" class="btn btn-info" @click=" login "> 登录 </button>
    <!--        </form>-->
        </div>
    </div>

</template>

<script>

    import eventBus from "@/eventBus";
    import {info, success, error} from "../../myToastr.js";

    export default {
        name: "login",
        data() {
            return {
                ID: '学号',
                identity: '用户',
                user: {
                    name: ''
                },
            }
        },
        methods: {

            handleIdentityClick(x) {
                this.user.identity = x
                if (x == 1) {
                    this.ID = '学号'
                    this.identity = '用户'
                } else if(x == 2) {
                    this.ID = '工号'
                    this.identity = '教师'
                } else {
                    this.ID = '帐号'
                    this.identity = '管理员'
                }
            },
            login() {
                if (this.user.identity !== 0 && !this.user.identity) {
                    info(this.$toastr, '请先选择职业', '提示：')
                    return
                } else if (this.user.id !== 0 && !this.user.id) {
                    info(this.$toastr, '请先输入'+this.ID, '提示：')
                    return
                } else if (this.user.password === undefined || this.user.password == null) {
                    info(this.$toastr, '请先输入密码', '提示：')
                    return
                }

                this.$axios.post(
                    'api/user/login', this.user
                ).then ( response => {
                    console.log(response)
                    if (!response.data.flag || response.data.flag === 'false')
                        error(this.$toastr, '密码错误<br>或所选权限不存在该用户！')
                    else {
                        success(this.$toastr, '登录成功！<br>单击右上角看看有没有新消息吧！')
                        sessionStorage.setItem("name", response.data.name)
                        sessionStorage.setItem("id", this.user.id)
                        console.log('login id:', this.user.id)
                        eventBus.$emit('loginSuccess')
                        console.log('!token:')
                        console.log(response.data.token)
                        sessionStorage.setItem("token", response.data.token)
                        if (response.data.identity === '0')
                            location.href = '/#/admin'
                        else if (response.data.identity === '1')
                            location.href = '/#/student'
                    }
                }).catch ( error => {
                    console.log('！！！登录失败异常：')
                    console.log(error)
                });
            },

        },

    }
</script>

<style scoped>

    .container { font-family: Consolas, Inconsolata, "微软雅黑" }

</style>