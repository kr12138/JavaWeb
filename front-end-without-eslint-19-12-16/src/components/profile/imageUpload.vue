<template>
    <div id="imgUP" class="container-fluid">
        <h4> 当前头像 </h4>
        <img :src=" avatar " class="img-thumbnail"
             style="box-shadow: gray 5px 3px 3px; height: 200px; width: 200px;"> <br> <br>
        <div class="row">
            <div class="custom-file mb-3 col-sm-6 offset-sm-3">
                <input type="file" class="custom-file-input"
                       id="inputFile" accept="image/*"
                       @change=" handleClick( $event ) ">
                <label class="custom-file-label"
                       for="inputFile"
                       > {{ img.name ? img.name : "点击上传头像" }} </label> <br> <br>
                <button class="btn btn-info"
                        @click=" up "
                        :disabled=" !img.name "> 提交 </button>
            </div>
        </div> <br> <br>
    </div>
</template>

<script>
    import {cInfo, cError, cSuccess} from "../../myToastr.js";

    export default {
        name: 'imageUpload',
        mounted() {
            this.getAvatar()
        },
        data () {
            return {
                avatar: '',
                img: '',
                base64: '',
            }
        },
        methods:{
            getAvatar() {
                let id = sessionStorage['id']
                if (id != 0 && !id) {
                    cError(this.$toastr, '用户已退出！<br>请重新登陆！', '错误：')
                    location.href = '/#/frontPage/login'
                }
                this.$axios.get(
                    'api/image/get/' + id
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '成功下载最新头像数据！')
                        this.avatar = unescape(JSON.parse(response.data.image).img)
                    } else
                        cError(this.$toastr, '无法下载头像数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            handleClick(e) {
                this.img = e.target.files[0]
            },
            up() {
                // console.log(this.img)
                let id = sessionStorage['id']
                if (id !== 0 && !id) {
                    cError(this.$toastr, '用户已退出！<br>请重新登陆！', '错误：')
                    location.href = '/#/frontPage/login'
                }
                if (this.img) {
                    let reader = new FileReader()
                    reader.readAsDataURL(this.img)
                    reader.onload = e => {
                        this.base64 = e.target.result
                        console.log(this.base64)
                        // 以上是转为base64，接下来塞进数据库
                        this.$axios.post(
                            'api/image/new', {
                                "id": id,
                                "img": this.base64
                        }).then( response => {
                            console.log(response)
                            if (response.data.flag === 'true')
                                cSuccess(this.$toastr, '成功上传图片数据！')
                            else
                                cError(this.$toastr, '无法上传图片数据！', '错误：')
                            this.getAvatar()
                        }).catch( error => {
                            console.log('！！！请求数据失败异常：')
                            console.log(error)
                        });
                    }
                } else
                    cInfo(this.$toastr, '请先上传图片！', '提示：')
            }
        }
    }
</script>

<style>
    #imgUP {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
