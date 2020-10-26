<template>
    <div class="container">
        <h2> 提问列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>

            <tr v-for=" answer in answers ">
                <th> {{ answer.id }} </th>
                <th> {{ answer.content }} </th>
                <th> {{ answer.date }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( answer ) " > 删改 </button> </th>
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
<!--                        <div class="input-group mb-3">-->
<!--                            <span class="input-group-prepend">-->
<!--                                <span class="input-group-text"> 提问标题 </span>-->
<!--                            </span>-->
<!--                            <input type="text" class="form-control"-->
<!--                                   id="name" v-model=" changingData.title ">-->
<!--                        </div>-->
                        <div class="input-group mb-3">
                            <textarea class="form-control"
                                      style="height: 200px;"
                                      placeholder="在此输入回答具体内容"
                                      v-model=" changingData.content "> </textarea>
                        </div> <br> <br>
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
        name: "adminAnswer",
        mounted() {
            this.getData();
        },
        data() {
            return {
                changingData: {},
                titles: [ '回答编号', '回答内容', '回答时间', '删改' ],
                answers: [],
            }
        },
        methods: {
            getData() {    // 初始化
                this.$axios.get(
                    'api/answers'
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.answers = JSON.parse(response.data.alist)
                    else
                        cError(this.$toastr, '无法得到回答数据！', '错误：')
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
                this.$axios.delete(
                    'api/answer/', this.changingData.id
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
                } else if (!this.changingData.content) {
                    info(this.$toastr, '请先输入内容', '提示：')
                    return
                }
                this.$axios.put(
                    'api/answer/', this.changingData
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
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>