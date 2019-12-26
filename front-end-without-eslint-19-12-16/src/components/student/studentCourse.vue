<template>
    <div class="container">
        <div class="container-fluid">
            <div class="row">
<!--                <label class="d-block col-md-1"> </label>-->
                <input type="number" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入课程编号"
                       id="idInput"
                       v-model=" searchingCID "
                       @keyup=" searchCByID ">
                <input type="text" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入课程名称"
                       id="nameInput"
                       v-model=" searchingCName "
                       @keyup=" searchCByName ">
                <span class="btn btn-info col-sm-12 col-md-3"
                      style="margin-left: 5px;"
                      @click=" getQuestionByCid "
                      > 搜索相关提问 <a class="glyphicon glyphicon-search"> </a> </span>
            </div>
        </div> <br>

        <div class="info" v-if=" searchingC !== null ">
            <div class="row">
                <div class="col-2 offset-2"> 课程编号：</div>
                <div class="col-6"> {{ searchingC.id }} </div> </div>
            <div class="row">
                <div class="col-2 offset-2"> 课程名称：</div>
                <div class="col-6"> {{ searchingC.name }} </div> </div>
            <div class="row">
                <div class="col-2 offset-2"> 课程说明：</div>
                <div class="col-6"> {{ searchingC.info }} </div> </div> <br>
            <div class="row">
                <button class="btn btn-info offset-md-2 col-md-8 offset-lg-4 col-lg-4"
                        @click=" newQuestion ">
                    <span class="glyphicon glyphicon-hand-right"></span>
                    我在这门课有疑问！
                </button>
            </div>
        </div>

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
                             @click=" searching( question ) "
                             > 查看 </button> </th>
            </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
    import {info, cError, success} from "../../myToastr.js";
    export default {
        name: "studentCourse",
        mounted() {
            this.getData();
        },
        data() {
            return {
                searchingCID: undefined,
                searchingCName: '',
                searchingC: null,
                titles: [ '问题编号', '问题标题', '问题内容', '提问时间', '回答情况', '查看详细' ],
                questions: [],
            }
        },
        methods: {
            getData() { // 初始化
                let tempCID = sessionStorage['searchingCID']
                if (tempCID !== undefined) {
                    sessionStorage.removeItem('searchingCID')
                    // console.log('Init ing stuCourse by ' + tempCID)
                    this.$axios.get(
                        'api/course/get/' + tempCID
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            this.searchingC = JSON.parse(response.data.course)
                            this.searchingCID = this.searchingC.id
                            this.searchingCName = this.searchingC.name
                        } else
                            cError(this.$toastr, '无法得到课程数据！', '错误：')
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
                if (this.searchingC !== null) {
                    this.$axios.get(
                        'api/question/getByCid/' + this.searchingC.id
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true')
                            this.questions = JSON.parse(response.data.qlist)
                        else
                            cError(this.$toastr, '无法得到提问数据！', '错误：')
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            searching(data) {   // 确定要查看的提问
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage['searchingQID'] = data.id
                sessionStorage['searchingQNAME'] = data.name
                location.href = '/#/student/question'
            },
            searchCByID() {  // 按课程编号查课程
                if(!this.searchingCID) {
                    // info(this.$toastr, '请先输入课程编号', '提示：')
                    this.searchingCName = ''
                    return
                }
                this.$axios.get(
                    'api/course/get/' + this.searchingCID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过ID查询到该课程！')
                        this.searchingC = JSON.parse(response.data.course)
                        this.searchingCName = this.searchingC.name
                    } else
                        this.searchingCName = ''
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            searchCByName() {   // 用课程名查ID
                if (!this.searchingCName) {
                    this.searchingCID = undefined
                    return
                }
                this.$axios.get(
                    'api/course/getByName/' + this.searchingCName
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过名称查询到该课程！')
                        this.searchingC = JSON.parse(response.data.course)
                        this.searchingCID = this.searchingC.id
                    } else
                        this.searchingCID = undefined
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getQuestionByCid() {    // 搜索某课程的提问
                if (this.searchingC.id === undefined || this.searchingC.name === '') {
                    info(this.$toastr, '请先选择课程！', '提示：')
                    return
                }
                this.$axios.get(
                    'api/question/getByCid/' + this.searchingC.id
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '查询提问列表成功！')
                        this.questions = JSON.parse(response.data.qlist)
                        document.getElementById('tableTitle').innerText
                            = this.searchingC.name + ' 提问列表'
                    } else
                        cError(this.$toastr, '无法得到提问数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            newQuestion() {
                sessionStorage['questioningCID'] = this.searchingC.id
                location.href = '/student/newQuestion'
            },

        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
    .info { font-size: 20px; }
</style>