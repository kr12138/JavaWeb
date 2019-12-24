<template>
    <div>
        <h3>
            用户设定
        </h3>

        <div class="container"> <br> <br>
            <!--        <form action="/api/user/changePassword" method="post">-->
            <h4> 用户ID：{{id}} </h4> <br>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 旧密码 </span>
                </span>
                <input type="text" class="form-control"
                       placeholder="输入新密码" v-model="oldPassword">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 新密码 </span>
                </span>
                <input type="text" class="form-control"
                       placeholder="输入新密码" v-model="newPassword">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 新密码 </span>
                </span>
                <input type="text" class="form-control"
                       placeholder="确认新密码" v-model="newPassword2">
            </div> <br> <br>
            <button type="button" class="btn btn-info" @click="change"> 更换 </button>
            <!--        </form>-->
        </div>

    </div>
</template>

<script>
    import eventBus from "@/eventBus";
    import {info, error, success} from "@/myToastr";

    export default {
        name: "settings",
        data() {
            return {
                id: sessionStorage.getItem('id'),
                oldPassword: '',
                newPassword: '',
                newPassword2: '',
            }
        },
        created() {
            eventBus.$on('loginSuccess', ()=> {
                this.id = sessionStorage.getItem('id')
            });
            eventBus.$on('logoutSuccess', ()=> {
                this.id = undefined
            })
        },
        methods: {

            change: function() {
                if (this.id === undefined) {
                    error(this.$toastr, '未成功登陆！', '错误：')
                    window.location.href = '/#/'
                    return
                } else if (this.oldPassword === '' && !this.oldPassword) {
                    info(this.$toastr, '请先输入旧密码', '提示：')
                    return
                } else if (this.newPassword === '' || this.newPassword2 === '') {
                    info(this.$toastr, '请先输入新密码', '提示：')
                    return
                } else if (this.newPassword !== this.newPassword2) {
                    info(this.$toastr, '两次输入的新密码应当相同', '提示：')
                    return
                }

                this.$axios.put(
                    'http://localhost:8080/api/user/changePassword', {
                        id: this.id,
                        oldPassword: this.oldPassword,
                        newPassword: this.newPassword,
                }).then ( response => {
                    window.console.log(response)
                    let flag = response.data.flag
                    if (flag === 'false') {
                        error(this.$toastr, '旧密码错误！<br> 请确认旧密码无误！')
                        return
                    } else {
                        success(this.$toastr, '修改成功！<br> 推荐您从右上角重新登录一下')
                        sessionStorage.clear()
                        eventBus.$emit('logoutSuccess',)
                        window.location.href = '/#/frontPage/home'

                    }
                }).catch ( error => {
                    window.console.log('！！！登录失败异常：')
                    window.console.log(error)
                });
            },

        },
    }
</script>

<style scoped>

    .container { font-family: Consolas, Inconsolata, "微软雅黑" }

</style>