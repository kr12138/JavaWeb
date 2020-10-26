<template>
    <div class="container">
        <div class="container-fluid">
            <div class="row">
                <label class="d-block col-md-2"> </label>
                <input type="number" class="form-control col-sm-6 col-md-5"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问编号"
                       id="idInput"
                       v-model=" searchingQID ">
                <span class="btn btn-info col-sm-4 col-lg-3"
                      style="margin-left: 5px;"
                      @click=" getPageByID( 0 ) "
                      > 按编号精确搜索 <a class="glyphicon glyphicon-search"> </a> </span>
            </div> <br>
            <div class="row">
                <label class="d-block col-md-2"> </label>
                <input type="text" class="form-control col-sm-6 col-md-5"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问标题"
                       id="titleInput"
                       v-model=" searchingQTitle ">
                <span class="btn btn-info col-sm-4 col-lg-3"
                      style="margin-left: 5px;"
                      @click=" getPageByTitleContaining( 0 ) "
                      > 按标题模糊搜索 <a class="glyphicon glyphicon-search"> </a> </span>
            </div> <br>
            <div class="row">
                <label class="d-block col-md-2"> </label>
                <input type="text" class="form-control col-sm-6 col-md-5"
                       style="margin-left: 5px;"
                       placeholder="在此输入提问标题"
                       id="contentInput"
                       v-model=" searchingQContent ">
                <span class="btn btn-info col-sm-4 col-lg-3"
                      style="margin-left: 5px;"
                      @click=" getPageByContentContaining( 0 ) "
                      > 按内容模糊搜索 <a class="glyphicon glyphicon-search"> </a> </span>
            </div> <br>
        </div> <br>

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
        <div v-show=" questions.length !== 0 ">
            <button class="btn btn-outline-info page" @click=" getPage( 0 ) "> 首页 </button>
            <button class="btn btn-outline-info page" @click=" getPage( page-1 ) "> &lt;&lt;上一页 </button>
            <button class="btn btn-outline-info page"> 第 {{ page+1 }} 页 </button>
            <button class="btn btn-outline-info page" @click=" getPage( page+1 ) "> 下一页&gt;&gt; </button>
            <button class="btn btn-outline-info page" @click=" getPage( totalPages-1 ) "> 末页 </button>
        </div>
    </div>
</template>

<script>
    import {info, cInfo, cError, success} from "../../myToastr.js";
    export default {
        name: "studentCourse",
        mounted() {
            this.getData();
        },
        data() {
            return {
                searchingQID: undefined,
                searchingQTitle: '',
                searchingQContent: '',
                searchingQ: null,
                titles: [ '编号', '提问标题', '提问内容', '提问时间', '情况', '详细' ],
                questions: [],
                searchingMethod: 0, // 0：id，1：title，2：content
                page: 0,
                totalPages: undefined,
            }
        },
        methods: {
            getData() { // 初始化
                let tempQID = sessionStorage['searchingQID']
                if (tempQID !== undefined) {
                    sessionStorage.removeItem('searchingQID')
                    console.log('Init ing stuCourse by ' + tempQID)
                    this.$axios.get(
                        'api/question/' + tempQID
                    ).then( response => {
                        console.log(response)
                        if (response.data.flag === 'true') {
                            this.searchingQ = JSON.parse(response.data.question)
                            this.searchingQID = this.searchingQ.id
                            this.getPageByID(0)
                        } else
                            cError(this.$toastr, '无法得到提问数据！', '错误：')
                    }).catch( error => {
                        console.log('！！！请求数据失败异常：')
                        console.log(error)
                    });
                }
            },
            getPageByID(page) { // 按提问编号查提问
                if (!this.searchingQID) {
                    info(this.$toastr, '请先输入编号', '提示：')
                    return
                }
                this.searchingMethod = 0
                this.$axios.get(
                    'api/question/page/' + page + '/id/' + this.searchingQID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        this.questions = JSON.parse(response.data.qlist)
                        if (this.questions.length === 0)
                            info(this.$toastr, '无相关提问！', '提示：')
                        else
                            success(this.$toastr, '已通过ID查询到该提问！')
                        // this.searchingQ = this.questions[0]
                        this.page = page
                        this.totalPages = JSON.parse(response.data.totalPages)
                    } else
                        cError(this.$toastr, '查询提问失败', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getPageByTitleContaining(page) {
                if (!this.searchingQTitle) {
                    info(this.$toastr, '请先输入标题', '提示：')
                    return
                }
                this.searchingMethod = 1
                this.$axios.get(
                    'api/question/page/' + page + '/titleContaining/' + this.searchingQTitle
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        this.questions = JSON.parse(response.data.qlist)
                        if (this.questions.length === 0)
                            info(this.$axios, '无相关提问！', '提示：')
                        else
                            success(this.$toastr, '已通过标题查询到该提问！')
                        this.page = page
                        this.totalPages = JSON.parse(response.data.totalPages)
                    } else
                        cError(this.$toastr, '查询提问失败', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getPageByContentContaining(page) {
                if (!this.searchingQContent) {
                    info(this.$toastr, '请先输入内容', '提示：')
                    return
                }
                this.searchingMethod = 2
                this.$axios.get(
                    'api/question/page/' + page + '/contentContaining/' + this.searchingQContent
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        this.questions = JSON.parse(response.data.qlist)
                        if (this.questions.length === 0)
                            info(this.$toastr, '无相关提问！', '提示：')
                        else
                            success(this.$toastr, '已通过内容查询到该提问！')
                        this.page = page
                        this.totalPages = JSON.parse(response.data.totalPages)
                    } else
                        cError(this.$toastr, '查询提问失败', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getPage(page) {
                console.log('正在请求页码：'+page)
                if (page<0) {
                    cInfo(this.$toastr, '已是首页，无上一页', '提示：')
                    return
                } else if (page >= this.totalPages) {
                    cInfo(this.$toastr, '已是末页，无下一页', '提示：')
                    return
                }
                if (this.searchingMethod === 0)
                    this.getPageByID(page)
                else if (this.searchingMethod === 1)
                    this.getPageByTitleContaining(page)
                else if (this.searchingMethod === 2)
                    this.getPageByContentContaining(page)
                else cError(this.$toastr, '未找到搜索方案', '错误：')
            },
            showQDetail(question) {
                sessionStorage['showingQID'] = question.id
                location.href = '/#/student/questionShow'
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