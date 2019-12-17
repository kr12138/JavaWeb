<template>
    <!-- 模态框（Modal） -->
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title" id="myModalLabel">
                        登出
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    
                </div>
                <div class="modal-body"> <br>

                    <h3> 确定要登出吗？ </h3> <br> <br>

                    <div class="row">
                        <button type="button" class="btn btn-secondary col-2 offset-3" name="identity" @click="logout"> 确定 </button>
                        <button type="button" class="btn btn-info col-2 offset-2" data-dismiss="modal" aria-hidden="true"> 取消 </button>
                    </div>

                </div> <br> <br>

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
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

        name: "logoutModal",

        methods: {
            logout: function() {
                if (sessionStorage.name !== 0 && !sessionStorage.name) {
                    error(this.$toastr, '并没有用户名，可能您未成功登陆或已登出', '提示：')
                    return
                }
                // else if (!sessionStorage.identity) {
                //     error(this.$toastr, '并没有权限，可能您未成功登陆或已登出', '提示：')
                //     return
                // }

                sessionStorage.clear()
                eventBus.$emit('logoutSuccess')
                success(this.$toastr, '您已成功登出！')
                window.location.href = '/#/'

                // this.$axios.post(
                //     'http://localhost:8080/api/user/login', this.user
                // ).then ((response) => {
                //     window.console.log(response)
                //     let flag = response.data.flag
                //     if (flag == 'false') {
                //         error(this.$toastr, '密码错误<br>或所选权限不存在该用户！')
                //         return
                //     } else {
                //         success(this.$toastr, '登录成功！<br>单击右上角看看有没有新消息吧！')
                //         sessionStorage.setItem("name", response.data.name)
                //         eventBus.$emit('loginSuccess', response.data.name)
                //         if (response.data.identity === '0')
                //             window.location.href = '/#/admin/course'
                //         return
                //     }
                // }).catch (function(response) {
                //     window.console.log('！！！登录失败异常：')
                //     window.console.log(response)
                // });
            },

        },

    }
</script>

<style scoped>

</style>