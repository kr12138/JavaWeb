<template>
    <div id="imgUP">
        <div class="custom-file mb-3">
            <input type="file" class="custom-file-input"
                   id="inputFile" accept="image/*"
                   @change=" handleClick( $event ) ">
            <label class="custom-file-label"
                   for="inputFile"> {{ img.name ? img.name : "点击上传图片" }} </label>
            <button class="btn btn-info"
                    @click=" up "
                    :disabled=" !img.name "> 提交 </button>
        </div> <br> <br>

<!--        <img :src=" base64 ">-->

    </div>
</template>

<script>
    import {cError, cSuccess} from "@/myToastr";

    export default {
        name: 'imageUpload',
        data () {
            return {
                img: {},
                base64: '',
            }
        },
        methods:{
            handleClick(e) {
                this.img = e.target.files[0]
            },
            up() {
                console.log(this.img)
                if (this.img) {
                    let reader = new FileReader()
                    reader.readAsDataURL(this.img)
                    let that = this
                    reader.onload = function (e) {
                        that.base64 = e.target.result
                        console.log(that.base64)
                        // 以上是转为base64，接下来塞进数据库
                        that.$axios.post(
                            'api/image/new', that.base64
                        ).then(response => {
                            console.log(response)
                            if (response.data.flag === 'true')
                                cSuccess(that.$toastr, '成功上次图片数据！')
                            else
                                cError(that.$toastr, '无法上传图片数据！', '错误：')
                        }).catch(error => {
                            console.log('！！！请求数据失败异常：')
                            console.log(error)
                        });
                    }
                }
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
