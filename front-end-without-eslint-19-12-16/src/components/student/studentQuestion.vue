<template>
    <div class="container">
        <div class="container-fluid">
            <div class="row">
                <label class="d-block col-md-2"> </label>
                <input type="number" class="form-control col-sm-4 col-md-3"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问编号"
                       id="idInput"
                       v-model=" searchingQID "
                       @keyup=" searchQByID() ">
                <input type="text" class="form-control col-sm-4 col-md-3"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问名称"
                       id="nameInput"
                       v-model=" searchingQName "
                       @keyup=" searchQByName ">
                <span class="btn btn-info col-sm-2"
                      style="margin-left: 5px;"
                      @click=" getAnswerByCid "
                > 搜索 <a class="glyphicon glyphicon-search"> </a> </span>
            </div>
        </div> <br>

        <div v-if=" searchingQ !== null " class="row">
            <span class="col-2 offset-2"> 提问编号：</span>
            <span class="col-6"> {{ searchingQ.id }} </span> </div>
        <div v-if=" searchingQ !== null " class="row">
            <div class="col-2 offset-2"> 提问名称：</div>
            <div class="col-6"> {{ searchingQ.name }} </div> </div>
        <div v-if=" searchingQ !== null " class="row">
            <div class="col-2 offset-2"> 提问说明：</div>
            <div class="col-6"> {{ searchingQ.info }} </div> </div> <br>

        <h2 v-show=" answers.length !== 0 " id="tableTitle">  </h2> <br>

        <table class="table table-hover" v-if=" answers.length !== 0 ">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr v-for=" answer in answers ">
                <th> {{ answer.id }} </th>
                <th> {{ answer.title }} </th>
                <th> {{ answer.date }} </th>
                <th> {{ answer.read ? "已读" : "未读" }} </th>
                <th> <button class="btn btn-info"
                             @click=" searching( answer ) "
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
                searchingQID: undefined,
                searchingQName: undefined,
                searchingQ: null,
                titles: [ '回答编号', '回答内容', '回答时间', '回答情况', '查看详细' ],
                answers: [],
            }
        },
        methods: {
            getData() { // 初始化
                let tempQID = sessionStorage['searchingQID']
                if(tempQID !== undefined) {
                    sessionStorage.removeItem('searchingQID')
                    console.log('Init ing stuCourse by ' + tempQID)
                    this.$axios.get(
                        'api/question/get/' + tempQID
                    ).then(response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            this.searchingQ = JSON.parse(response.data.question)
                            this.searchingQID = this.searchingQ.id
                            this.searchingQName = this.searchingQ.name
                        } else
                            cError(this.$toastr, '无法得到提问数据！', '错误：')
                    }).catch(error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
                if (this.searchingQ !== null) {
                    this.$axios.get(
                        'api/answer/getByQid/' + this.searchingQ.id
                    ).then(response => {
                        console.log(response)
                        if (response.data.flag === 'true')
                            this.answers = JSON.parse(response.data.alist)
                        else
                            cError(this.$toastr, '无法得到回答数据！', '错误：')
                    }).catch(error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            searching(data) {   // 确定要查看的回答
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage['searchingAID'] = data.id
                sessionStorage['searchingANAME'] = data.name
                location.href = '/#/student/answer'
            },
            searchQByID() {  // 按提问编号查提问
                if(!this.searchingQID) {
                    // info(this.$toastr, '请先输入提问编号', '示：')
                    this.searchingQName = ''
                    return
                }
                this.$axios.get(
                    'api/question/get/' + this.searchingQID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过ID查询到该提问！')
                        this.searchingQ = JSON.parse(response.data.question)
                        this.searchingQName = this.searchingQ.name
                    } else
                        this.searchingQName = ''
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            searchQByName() {   // 用提问名查ID
                if (!this.searchingQName) {
                    this.searchingQID = undefined
                    return
                }
                this.$axios.get(
                    'api/question/getByName/' + this.searchingQName
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过名称查询到该提问！')
                        this.searchingQ = JSON.parse(response.data.question)
                        this.searchingQID = this.searchingQ.id
                    } else
                        this.searchingQID = undefined
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getAnswerByCid() {    // 搜索某提问的回答
                if (this.searchingQ.id === undefined || this.searchingQ.name === '') {
                    info(this.$toastr, '请先选择提问！', '示：')
                    return
                }
                this.$axios.get(
                    'api/answer/getByQid/' + this.searchingQ.id
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '查询回答列表成功！')
                        this.answers = JSON.parse(response.data.alist)
                        document.getElementById('tableTitle').innerText
                            = this.searchingQ.name + ' 回答列表'
                    } else
                        cError(this.$toastr, '无法得到回答数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });

            },

        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
    span::before {
        vertical-align: middle;
        /*padding-right: 5px;*/
    }
</style>