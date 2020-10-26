<template>
    <div>
        <button class="btn btn-info"
                @click=" down "
                :disabled=" img!=='' "> 下载 </button> <br>
        <img :src=" img " alt="附图" v-show=" img !== '' ">
    </div>
</template>

<script>
    import {cError, cSuccess} from "../../myToastr.js";

    export default {
        name: 'imageDownload',
        data () {
            return {
                imgID: 17,
                img: '',
            }
        },
        methods:{
            down() {
                console.log('getting image with id: ' + this.imgID)
                this.$axios.get(
                    'api/image/' + this.imgID
                ).then(response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        // console.log(JSON.parse(response.data.image).img)
                        // console.log(unescape(JSON.parse(response.data.image).img))
                        cSuccess(this.$toastr, '成功下载图片！')
                        this.img = unescape(JSON.parse(response.data.image).img)
                    } else
                        cError(this.$toastr, '无法下载图片！', '错误：')
                }).catch(error => {
                    console.log('！！！请求图片失败异常：')
                    console.log(error)
                });
                console.log(this.img)
            }
        },
    }
</script>

<style scoped>

</style>