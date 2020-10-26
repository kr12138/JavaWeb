<template>
    <div class="container">
        <div class="container-fluid">
            <div class="row mb-3">
                <label class="d-block col-md-2"> </label>
                <input type="number" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问课程编号"
                       v-model=" questioningCID "
                       @keyup=" searchCByID ">
                <input type="text" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问课程名称"
                       v-model=" questioningCName "
                       @keyup=" searchCByName ">
            </div>
            <div class="row">
                <label class="d-block col-md-2"> </label>
                <input type="number" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问教师工号"
                       v-model=" questioningTID "
                       @keyup=" searchTByID ">
                <input type="text" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问教师姓名"
                       v-model=" questioningTName "
                       @keyup=" searchTByName ">
            </div> <br>
            <div class="row">
                <label class="d-block col-sm-1 col-md-3 "> </label>
                <input type="text" class="form-control col-sm-10 col-md-6"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问标题"
                       v-model=" title "> <br>
                <label class="d-block col-sm-1 col-md-3 "> </label>
                <textarea class="form-control col-sm-10 col-md-6"
                          style="margin-left: 5px; height: 200px;"
                          placeholder="在此输入提问具体内容"
                          v-model=" content "> </textarea>
                <label class="d-block col-sm-1 col-md-3 "> </label>
                <div id="imgUP" class="col-sm-10 col-md-6" style="margin-top: 15px;">
                    <div class="custom-file mb-3">
                        <input type="file" class="custom-file-input"
                               id="inputFile" accept="image/*"
                               @change=" choosingImg( $event ) ">
                        <label class="custom-file-label"
                               for="inputFile"> {{ img.name ? img.name : "点我上传图片" }} </label>
<!--                        <button class="btn btn-info"-->
<!--                                @click=" up "-->
<!--                                :disabled=" !img.name "> 提交 </button>-->
                    </div>
                </div> <br>
            </div>
            <div class="row">
                <button class="btn btn-info offset-5 col-2"
                        @click=" newQ "> 提交 </button>
            </div>
        </div> <br>

    </div>
</template>

<script>
    import {info, cError, cSuccess} from "../../myToastr.js";

    export default {
        name: "studentNewQuestion",
        mounted() {
            if (sessionStorage['questioningCID'] !== undefined) {
                this.questioningCID = sessionStorage['questioningCID']
                sessionStorage.removeItem('questioningCID')
                console.log('Init ing stuNewQuestion by ' + this.questioningCID)
                this.searchCByID()
            }
        },
        data() {
            return {
                questioningCID: undefined,
                questioningCName: '',
                questioningTID: undefined,
                questioningTName: '',
                title: '',
                content: '',
                img: '',
                img64: '',
                flag: 0,
                questioningC: null,
                questioningT: null,
            }
        },
        methods: {
            searchCByID() {
                if (this.questioningCID !== undefined) {
                    this.$axios.get(
                        'api/course/' + this.questioningCID
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            cSuccess(this.$toastr, '成功获得课程信息！')
                            this.questioningC = JSON.parse(response.data.course)
                            this.questioningCName = this.questioningC.name
                        }
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            searchCByName() {
                if (this.questioningCName !== undefined) {
                    this.$axios.get(
                        'api/course?name=' + this.questioningCName
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            cSuccess(this.$toastr, '成功获得课程信息！')
                            this.questioningC = JSON.parse(response.data.course)
                            this.questioningCID = this.questioningC.id
                        }
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            searchTByID() {
                if (this.questioningTID !== undefined) {
                    this.$axios.get(
                        'api/teacher/' + this.questioningTID
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            cSuccess(this.$toastr, '成功获得教师信息！')
                            this.questioningT = JSON.parse(response.data.teacher)
                            this.questioningTName = this.questioningT.name
                        }
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            searchTByName() {
                if (this.questioningTName !== undefined) {
                    this.$axios.get(
                        'api/teacher?name=' + this.questioningTName
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            cSuccess(this.$toastr, '成功获得教师信息！')
                            this.questioningT = JSON.parse(response.data.teacher)
                            this.questioningTID = this.questioningT.id
                        }
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            choosingImg(e) {
                this.img = e.target.files[0]
                console.log(this.img)
            },
            upload() {  // 上传提问
                console.log('this.flag:' + this.flag + (this.flag === 1))
                if (this.flag !== 1) return
                console.log('uploading…')
                if (this.img) {
                    let reader = new FileReader()
                    reader.readAsDataURL(this.img)
                    reader.onload = e => {
                        this.img64 = e.target.result
                        console.log(this.img64)
                        // 以上是转为base64，接下来塞进数据库
                        this.$axios.post(
                            'api/question/new', {
                                "cid": this.questioningC.id,
                                "sid": sessionStorage['id'],
                                "tid": this.questioningT.id,
                                "title": this.title,
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
                        'api/question', {
                            "cid": this.questioningC.id,
                            "sid": sessionStorage['id'],
                            "tid": this.questioningT.id,
                            "title": this.title,
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
                this.$axios.get(
                    'api/teaches/cid/' + this.questioningC.id
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        let tlist = JSON.parse(response.data.tlist)
                        for (let i = 0; i < tlist.length; ++i) {
                            // console.log('tlist['+i+'].id='+tlist[i].id)
                            // console.log('this.questioningT.id'+this.questioningT.id)
                            if (tlist[i].id === this.questioningT.id) {
                                this.flag = 1
                                // console.log('this.flag:'+this.flag)
                                this.upload()
                            }
                        }
                    } else {
                        cError(this.$toastr, '无法检查授课教师数据！', '错误：')
                        return this.flag = 0
                    }
                    if (this.flag === 0)
                        cError(this.$toastr, '该教师没有开这门课！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                    return 0
                });
            },
            newQ() {    // 提交新数据
                this.flag = 0
                this.check()
            }
        }
    }
</script>

<style scoped>

</style>