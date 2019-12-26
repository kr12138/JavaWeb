<template>
    <div class="container">
        <div class="container-fluid">
            <div class="row">
<!--                <label class="d-md-block col-lg-1"> </label>-->
                <input type="number" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入教师工号"
                       id="idInput"
                       v-model=" searchingTID "
                       @keyup=" searchTByID "/>
                <input type="text" class="form-control col-sm-12 col-md-4"
                       style="margin-left: 5px;"
                       placeholder="在此输入教师姓名"
                       id="nameInput"
                       v-model=" searchingTName "
                       @keyup=" searchTByName ">
                <span class="btn btn-info col-sm-12 col-md-3"
                      style="margin-left: 5px;"
                      @click=" getCourseByUid "
                      > 搜索授课情况 <a class="glyphicon glyphicon-search"> </a> </span>
            </div>
        </div> <br>

        <div class="info" v-if=" searchingTeacher !== null ">
            <div class="row">
                <div class="col-2 offset-2"> 教师工号：</div>
                <div class="col-6"> {{ searchingTeacher.id }} </div> </div>
            <div class="row">
                <div class="col-2 offset-2"> 教师姓名：</div>
                <div class="col-6"> {{ searchingTeacher.name }} </div> </div>
            <div class="row">
                <div class="col-2 offset-2"> 教师简介：</div>
                <div class="col-6"> {{ searchingTeacher.info }} </div> </div> <br>
        </div>

        <h2 v-show=" courses.length !== 0 " id="tableTitle">  </h2> <br>
        <table class="table table-hover" v-if=" courses.length !== 0 ">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr v-for=" course in courses ">
                <th> {{ course.id }} </th>
                <th> {{ course.name }} </th>
                <th> {{ course.info }} </th>
                <th> <button class="btn btn-info"
                             @click=" searching( course ) "
                             > 查看 </button> </th>
            </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
    import {info, cError, success} from "../../myToastr.js";

    export default {
        name: "studentTeacher",
        data() {
            return {
                searchingTID: undefined,
                searchingTName: '',
                searchingTeacher: null,
                titles: [ '课程编号', '课程名称', '课程说明', '相关提问' ],
                teachers: [],
                courses: [],
            }
        },
        methods: {
            searchTByID() { // 用id查教师名
                if (!this.searchingTID) {
                    this.searchingTName = ''
                    return
                }
                this.$axios.get(
                    'api/teacher/get/' + this.searchingTID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过ID查询到该教师！')
                        this.searchingTeacher = JSON.parse(response.data.teacher)
                        this.searchingTName = this.searchingTeacher.name
                    } else
                        this.searchingTName = ''
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            searchTByName() {   // 用教师名查ID
                if (!this.searchingTName) {
                    this.searchingTID = undefined
                    return
                }
                this.$axios.get(
                    'api/teacher/getByName/' + this.searchingTName
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '已通过姓名查询到该教师！')
                        this.searchingTeacher = JSON.parse(response.data.teacher)
                        this.searchingTID = this.searchingTeacher.id
                    } else
                        this.searchingTID = undefined
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            getCourseByUid() {  // 搜索某教师的课程
                if (this.searchingTeacher.id === undefined || this.searchingTeacher.name === '') {
                    info(this.$toastr, '请先选择教师！', '提示：')
                    return
                }
                this.$axios.get(
                    'api/teaches/getCourseByUid/' + this.searchingTeacher.id
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '查询授课列表成功！')
                        this.courses = JSON.parse(response.data.clist)
                        document.getElementById('tableTitle').innerText
                            = this.searchingTeacher.name + ' 授课列表'
                    } else
                        cError(this.$toastr, '无法得到课程数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });

            },
            searching(course) { // 确定要查看提问的课程
                if (course === null) {
                    cError(this.$toastr, '正在查询空课程！', '错误：')
                    return
                }
                sessionStorage['searchingCID'] = course.id
                sessionStorage['searchingCNAME'] = course.name
                location.href = '/#/student/course'
            },
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
    .info { font-size: 20px; }
    a::before {
        vertical-align: middle;
        /*padding-right: 5px;*/
    }
</style>