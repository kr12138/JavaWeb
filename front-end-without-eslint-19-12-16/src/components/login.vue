<template>

    <div>
        <br>
        <h4>
            {{identity}}登录
        </h4>
        <div class="container"> <br> <br>
    <!--        <form action="/api/user/login" method="post">-->
            <div class="btn-group">
                <button type="button" class="btn btn-info" name="identity" @click="handleIdentityClick(0)">管理</button>
                <button type="button" class="btn btn-info" name="identity" @click="handleIdentityClick(1)">学生</button>
                <button type="button" class="btn btn-info" name="identity" @click="handleIdentityClick(2)">教师</button>
            </div><br><br>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> {{ID}} </span>
                </span>
                <input type="number" class="form-control" name="id" v-model="user.id">
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <span class="input-group-prepend">
                    <span class="input-group-text"> 密码 </span>
                </span>
                <input type="password" class="form-control" name="password" v-model="user.password">
            </div> <br> <br>
            <button type="button" class="btn btn-info" @click="login">登录</button>
    <!--        </form>-->
        </div>
    </div>

</template>

<script>

    import eventBus from "@/eventBus";

    function toastrInit(toastr, msg) {
        toastr.options = {
            closeButton: true,                       // 是否显示关闭按钮，（提示框右上角关闭按钮）
            debug: false,                            // 是否使用deBug模式
            progressBar: true,                       // 是否显示进度条，（设置关闭的超时时间进度条）
            positionClass: "toast-top-center",       // 设置显示的位置
            onclick: null,                           // 点击消息框自定义事件
            showDuration: "300",                     // 显示动画的时间
            hideDuration: "1000",                    // 消失的动画时间
            timeOut: "1000",                         // 自动关闭超时时间
            extendedTimeOut: "1000",                 // 加长展示时间
            showEasing: "swing",                     // 显示时的动画缓冲方式
            hideEasing: "linear",                    // 消失时的动画缓冲方式
            showMethod: "fadeIn",                    // 显示时的动画方式
            hideMethod: "fadeOut"                    // 消失时的动画方式
        };
        if (msg == null)
            msg = ''
        let len = msg.length
        if (len <= 10 && len > 0) {
            toastr.options.timeOut = "1800"
        } else if (len <= 20) {
            toastr.options.timeOut = "2800"
        } else if (len <= 30) {
            toastr.options.timeOut = "3800"
        } else if (len > 30) {
            toastr.options.timeOut = "4800"
        }
    }

    function info(toastr, msg, title) {
        toastrInit(toastr, msg)
        if (title === undefined)
            title = ''
        toastr.info(msg, title)
    }
    function success(toastr, msg, title) {
        toastrInit(toastr, msg)
        if (title == null)
            title = ''
        toastr.success(msg, title)
    }
    function error(toastr, msg, title) {
        toastrInit(toastr, msg)
        if (title == null)
            title = ''
        toastr.error(msg, title)
    }

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

            handleIdentityClick: function(x) {
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
            login: function() {
                if (this.user.identity !== 0 && !this.user.identity) {
                    info(this.$toastr, '请先选择职业', '提示：')
                    return
                } else if (this.user.id!==0 && !this.user.id) {
                    info(this.$toastr, '请先输入'+this.ID, '提示：')
                    return
                } else if (this.user.password === undefined || this.user.password == null) {
                    info(this.$toastr, '请先输入密码', '提示：')
                    return
                }

                this.$axios.post(
                    'http://localhost:8080/api/user/login', this.user
                ).then ((response) => {
                    window.console.log(response)
                    let flag = response.data.flag
                    if (flag == 'false') {
                        error(this.$toastr, '密码错误<br>或所选权限不存在该用户！')
                        return
                    } else {
                        success(this.$toastr, '登录成功！<br>单击右上角看看有没有新消息吧！')
                        sessionStorage.setItem("name", response.data.name)
                        eventBus.$emit('loginSuccess', response.data.name)
                        if (response.data.identity === '0')
                            window.location.href = '/#/admin/course'
                        return
                    }
                }).catch (function(response) {
                    window.console.log('！！！登录失败异常：')
                    window.console.log(response)
                });
            },

        },

    }
</script>

<style scoped>

    .container { font-family: Consolas, Inconsolata, "微软雅黑" }

</style>