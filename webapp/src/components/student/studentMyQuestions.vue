<template>
    <div class="container">
        <h2> 我的历史提问 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr v-for=" question in questions ">
                <th> {{ question.id }} </th>
                <th> {{ question.title.length > 9 ?
                        question.title.substr(0,9) + '…' :
                        question.title }} </th>
                <th> {{ question.content.length > 9 ?
                        question.content.substr(0,9) + '…' :
                        question.content }} </th>
                <th> {{ question.date }} </th>
                <th> {{ question.read ? "已被解答" : "未被解答" }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( question ) " > 删改 </button> </th>
                <th> <button class="btn btn-info"
                             @click=" searching( question ) " > 查看 </button> </th>
            </tr>
            </tbody>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改提问 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 提问编号 ： {{ changingData.id }} </h5> <br>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 提问标题 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="name" v-model=" changingData.title ">
                        </div>
                        <div class="input-group mb-3">
                            <textarea class="form-control"
                                      style="height: 200px;"
                                      v-model=" changingData.content "> </textarea>
                        </div>
<!--                        <div class="input-group mb-3">-->
<!--                            <span class="input-group-prepend">-->
<!--                                <span class="input-group-text"> 提问说明 </span>-->
<!--                            </span>-->
<!--                            <input type="text" class="form-control"-->
<!--                                   id="info" v-model=" changingData.info ">-->
<!--                        </div> <br> <br>-->
                        <div class="row">
                            <button type="button" class="btn btn-info col-2 offset-3"
                                    data-dismiss="modal" aria-hidden="true"
                                    @click=" update "> 更新 </button>
                            <button type="button" class="btn btn-secondary col-2 offset-2"
                                    data-dismiss="modal" aria-hidden="true"
                                    @click=" del "> 删除 </button>
                        </div>
                    </div> <br> <br>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div><!-- /.modaldiv -->
    </div>
</template>

<script>
    import {info, cError, cSuccess} from "../../myToastr.js";
    export default {
        name: "studentMyQuestions",
        mounted() {
            this.getData();
        },
        data() {
            return {
                changingData: {},
                titles: [ '编号', '提问标题', '提问内容', '提问时间', '情况', '删改', '详细' ],
                depts: [],
                questions: [],
            }
        },
        methods: {
            getData() {    // 初始化
                this.$axios.get(
                    'api/questions'
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.questions = JSON.parse(response.data.questions)
                    else
                        cError(this.$toastr, '无法得到提问数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            changing(data) {    // 确定删改对象
                if (data === null) {
                    cError(this.$toastr, '正在删改空对象！', '错误：')
                    return
                }
                this.changingData = data
            },
            del() {  // 删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无提问编号！请重试', '错误：')
                    return
                }
                this.$axios.post(
                    'api/question/myDelete', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '删除失败<br>可能无该编号？', '错误：')
                    else {
                        cSuccess(this.$toastr, '删除成功！')
                        this.getData()
                    }
                }).catch ( error => {
                    console.log('！！！删除失败异常：')
                    console.log(error)
                });
            },
            update() {  // 更改
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无提问编号！请重试', '错误：')
                    return
                } else if (!this.changingData.title) {
                    info(this.$toastr, '请先输入标题', '提示：')
                    return
                } else if (!this.changingData.content) {
                    info(this.$toastr, '请先输入内容', '提示：')
                    return
                }
                this.$axios.put(
                    'api/question/myUpdate', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '更新失败<br>可能无该编号？')
                    else {
                        cSuccess(this.$toastr, '更新成功！')
                        this.getData()
                    }
                }).catch ( error => {
                    console.log('！！！更新失败异常：')
                    console.log(error)
                });
            },
            searching(data) {   // 确定要查看的提问
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage.setItem('showingQID', data.id)
                location.href = '/#/student/questionShow'
            },
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>