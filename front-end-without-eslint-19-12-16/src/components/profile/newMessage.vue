<template>
    <div class="container-fluid">

        <h2> {{ name }} 新消息列表 </h2>

        <h2 v-show=" questions.length !== 0 " id="tableTitle">  </h2> <br>

        <table class="table table-hover" v-if=" questions.length !== 0 ">
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
                             @click=" showQDetail( question ) "
                             > 查看 </button> </th>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {cError, cSuccess} from "../../myToastr.js";

    export default {
        name: "newMessage",
        data() {
            return {
                name: '',
                titles: [ '编号', '提问标题', '提问内容', '提问时间', '情况', '详细' ],
                questions: [],
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
            this.$axios.get(
                'api/user/getNewMessageByUid/' + sessionStorage['id']
            ).then( response => {
                console.log(response)
                if (response.data.flag === 'true') {
                    cSuccess(this.$toastr, '请求新消息成功！', '提示：')
                    this.questions = JSON.parse(response.data.qlist)
                } else
                    cError(this.$toastr, '请求新消息失败！', '错误：')

            }).catch( error => {
                console.log('！！！请求数据失败异常：')
                console.log(error)
            });
        },
        methods: {
            showQDetail(question) {
                sessionStorage['showingQID'] = question.id
                if (sessionStorage['identity'] == 2)
                    location.href = '/#/teacher/questionShow'
                else
                    location.href = '/#/student/questionShow'
            },
        },
    }
</script>

<style scoped>

</style>