<template>
    <div class="container-fluid">

        <h2> {{ name }} 回复列表 </h2>

        <h2 v-show=" answers.length !== 0 " id="tableTitle">  </h2> <br>

        <table class="table table-hover" v-if=" answers.length !== 0 ">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr v-for=" answer in answers ">
                <th> {{ answer.qid }} </th>
                <th> {{ answer.aid }} </th>
                <th> {{ answer.title}} </th>
                <th> {{ answer.content }} </th>
                <th> {{ answer.date }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( answer ) "
                             > 删改 </button> </th>
            </tr>
            </tbody>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改回答 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 回答编号 ： {{ changingData.aid }} </h5> <br>
                        <div class="input-group mb-3">
                            <textarea class="form-control"
                                      style="height: 200px;"
                                      v-model=" changingData.content "> </textarea>
                        </div>
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
    import {cError, cSuccess} from "@/myToastr";

    export default {
        name: "teacherMyAnswers",
        data() {
            return {
                name: '',
                changingData: {},
                titles: [ '提问编号', '回答编号', '提问标题', '我的回答', '回复时间', '删改' ],
                answers: [],
            }
        },
        mounted() {
            if (sessionStorage['id'] == undefined) {
                cError(this.$toastr, '登录已过期，请重新登录！', '错误：')
                location.href = '/#/frontPage/login'
            }
            if (sessionStorage['name'])
                this.name = sessionStorage['name']
            else
                this.name = ''
            this.getData()
        },
        methods: {
            getData() {
                this.$axios.get(
                    'api/answer/getIntroByTid/' + sessionStorage['id']
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '请求回答数据成功！', '提示：')
                        this.answers = JSON.parse(response.data.rlist)
                    } else
                        cError(this.$toastr, '请求回答数据失败！', '错误：')

                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            changing(data) {
                if (data.aid != 0 && !data.aid)
                    cError(this.$toastr, '无回答编号！', '错误：')
                else
                    this.changingData = data
            },
            del() {  // 删除
                if (!this.changingData.aid && this.changingData.aid !== 0) {
                    cError(this.$toastr, '无回答编号！请重试', '错误：')
                    return
                }
                this.changingData.id = this.changingData.aid
                this.$axios.post(
                    'api/answer/myDelete', this.changingData
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
                if (!this.changingData.aid && this.changingData.aid !== 0) {
                    cError(this.$toastr, '无回答编号！请重试', '错误：')
                    return
                } else if (!this.changingData.content) {
                    info(this.$toastr, '请先输入内容', '提示：')
                    return
                }
                this.changingData.id = this.changingData.aid
                this.changingData.tid = sessionStorage['id']
                this.$axios.put(
                    'api/answer/myUpdate', this.changingData
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
        },
    }
</script>

<style scoped>

</style>