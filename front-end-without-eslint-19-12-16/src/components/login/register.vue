<template>

    <div>
        <br>
        <h4>
            新用户注册
        </h4>

        <div class="container"> <br> <br>
    <!--        <form action="/api/user/register" method="post">-->
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 学号 </span>
                </span>
                <input type="text" class="form-control" name="id" v-model=" user.id ">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 昵称 </span>
                </span>
                <input type="text" class="form-control" name="name" v-model=" user.name ">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 密码 </span>
                </span>
                <input type="password" class="form-control" name="password" v-model=" user.password ">
            </div> <br> <br>
            <button type="button" class="btn btn-info" @click=" register "> 注册 </button>
    <!--        </form>-->
        </div> <br>
    </div>

</template>

<script>

    import {info, warning, success, error} from "../../myToastr.js";

    export default {
        name: "register",
        data() {
            return {
                user: {
                    identity: '1',
                },
            }
        },
        methods: {
            register: () => {
                if (this.user.identity != '1') {
                    warning(this.$toastr, '请不要修改用户权限，本系统仅支持学生用户注册', '警告：')
                    return
                } else if (this.user.id === undefined) {
                    info(this.$toastr, '请先输入学号', '提示：')
                    return
                } else if (this.user.name === undefined) {
                    info(this.$toastr, '请先输入昵称', '提示：')
                    return
                } else if (this.user.password === undefined) {
                    info(this.$toastr, '请先输入密码', '提示：')
                    return
                }

                this.$axios.post(
                    'http://localhost:8080/api/user/register', this.user
                ).then ( response => {
                    window.console.log(response)
                    let flag = response.data.flag
                    if (!flag || flag === 'false') {
                        error(this.$toastr, '该学号已注册<br>请尝试用其他ID重新注册！')
                        return
                    } else {
                        success(this.$toastr, '注册成功！快点击右上角登录试试吧！')
                        return
                    }
                }).catch ( error => {
                    window.console.log('！！！注册失败异常：')
                    window.console.log(error)
                });
            },

        },

    }
</script>

<style scoped>

    .container { font-family: Consolas, Inconsolata, "微软雅黑" }

</style>