export function toastrInit(toastr, msg) {
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

export function info(toastr, msg, title) {
    toastrInit(toastr, msg)
    if (title === undefined)
        title = ''
    toastr.info(msg, title)
}
export function warning(toastr, msg, title) {
    toastrInit(toastr, msg)
    if (title == null)
        title = ''
    toastr.warning(msg, title)
}
export function success(toastr, msg, title) {
    toastrInit(toastr, msg)
    if (title == null)
        title = ''
    toastr.success(msg, title)
}
export function error(toastr, msg, title) {
    toastrInit(toastr, msg)
    if (title == null)
        title = ''
    toastr.error(msg, title)
}