<template>
    <div class="container">
        <studentQuestionShow> </studentQuestionShow>

        <div class="container-fluid"> <br> <br>
            <div class="row">
                <label class="d-block col-sm-1 col-md-3 "> </label>
                <textarea class="form-control col-sm-10 col-md-6"
                          style="margin-left: 5px; height: 200px;"
                          placeholder="在此输入回答具体内容"
                          v-model=" content "> </textarea>
                <label class="d-block col-sm-1 col-md-3 "> </label>
                <div id="imgUP" class="col-sm-10 col-md-6" style="margin-top: 15px;">
                    <div class="custom-file mb-3">
                        <input type="file" class="custom-file-input"
                               id="inputFile" accept="image/*"
                               @change=" choosingImg( $event ) ">
                        <label class="custom-file-label"
                               for="inputFile"> {{ img.name ? img.name : "点我上传图片" }} </label>
                    </div>
                </div> <br>
            </div>
            <div class="row">
                <button class="btn btn-info offset-5 col-2"
                        @click=" newA "> 提交 </button>
            </div>
        </div> <br>
    </div>
</template>

<script>
    import {info, cError, cSuccess} from "../../myToastr.js";
    import studentQuestionShow from "@/components/student/studentQuestionShow";

    export default {
        name: "teacherQuestionShow",
        components: {
            studentQuestionShow,
        },
        mounted() {
            if (sessionStorage['showingQID'] !== undefined) {
                this.showingQID = sessionStorage['showingQID']
                // sessionStorage.removeItem('showingQID')  // 不方便调试，不删了
                console.log('Init ing teacherQuestionShow by ' + this.showingQID)}
        },
        data() {
            return {
                showingQID: undefined,
                content: '',
                img: '',
                img64: '',
                flag: 0,
            }
        },
        methods: {
            choosingImg(e) {
                this.img = e.target.files[0]
                console.log(this.img)
            },
            upload() {  // 上传提问
                console.log('uploading…')
                if (this.img) {
                    let reader = new FileReader()
                    reader.readAsDataURL(this.img)
                    reader.onload = e => {
                        this.img64 = e.target.result
                        console.log(this.img64)
                        // 以上是转为base64，接下来塞进数据库
                        this.$axios.post(
                            'api/answer/new', {
                                "qid": this.showingQID,
                                "tid": sessionStorage['id'],
                                "content": this.content,
                                "img": this.img64
                        }).then( response => {
                            console.log(response)
                            if (response.data.flag === 'true')
                                cSuccess(this.$toastr, '成功上传数据！')
                            else
                                cError(this.$toastr, '无法上传数据！请重试！', '错误：')
                        }).catch( error => {
                            console.log('！！！请求数据失败异常：')
                            console.log(error)
                        });
                    }
                } else {
                    this.$axios.post(
                        'api/answer/new', {
                            "qid": this.showingQID,
                            "tid": sessionStorage['id'],
                            "content": this.content,
                            "img": ''
                    }).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true')
                            cSuccess(this.$toastr, '成功上传数据！')
                        else
                            cError(this.$toastr, '无法上传数据！请重试！', '错误：')
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                        return 0
                    });
                }
            },
            check() {   // 返回是否能提交新数据，再上传
                if (this.questioningC === null) {
                    info(this.$toastr, '请先确定课程', '提示：')
                    return this.flag = 0
                } else if (this.questioningT === null) {
                    info(this.$toastr, '请先确定教师', '提示：')
                    return this.flag = 0
                } else if (this.title === '') {
                    info(this.$toastr, '请先输入标题', '提示：')
                    return this.flag = 0
                } else if (this.content === '') {
                    info(this.$toastr, '请先输入内容', '提示：')
                    return this.flag = 0
                } else if (sessionStorage['id'] === undefined) {
                    cError(this.$toastr, '无用户权限！请重新登陆！', '错误：')
                }
                this.upload()
            },
            newA() {    // 提交新数据
                this.flag = 0
                this.check()
            }
        }
    }
</script>

<style scoped>

</style>