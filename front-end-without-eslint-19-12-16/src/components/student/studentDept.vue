<template>
    <div class="container"> <br>
        <div class="container-fluid">
            <span class="dropdown">
                <button type="button" class="btn btn-outline-info dropdown-toggle"
                        id="deptMenu"
                        data-toggle="dropdown"> 按开课学院 </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#"
                       v-for=" dept in depts "
                       @click=" handleDeptClick( dept ) "> {{ dept.name }}
                    </a>
                </div>
            </span>
            <span class="btn btn-info"
                  style="margin-left: 3px;"
                  @click=" getCourseByDept "
                  > 搜索 <a class="glyphicon glyphicon-search"> </a> </span>
        </div> <br>

        <div v-if=" searchingDept !== null " class="row">
            <span class="col-2 offset-2"> 学院编号：</span>
            <span class="col-6"> {{ searchingDept.id }} </span> </div>
        <div v-if=" searchingDept !== null " class="row">
            <div class="col-2 offset-2"> 学院名称：</div>
            <div class="col-6"> {{ searchingDept.name }} </div> </div>
        <div v-if=" searchingDept !== null " class="row">
            <div class="col-2 offset-2"> 学院简介：</div>
            <div class="col-6"> {{ searchingDept.info }} </div> </div> <br>

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
        name: "studentDept",
        mounted() {
            this.getData();
        },
        data() {
            return {
                searchingDept: null,
                titles: [ '课程编号', '课程名称', '课程说明', '相关提问' ],
                depts: [],
                courses: [],
            }
        },
        methods: {
            getData() {    //初始化
                this.$axios.get(
                    'api/dept/getAll',
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.depts = JSON.parse(response.data.depts)
                    else
                        cError(this.$toastr, '无法得到学院数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            handleDeptClick(dept) { // 更改搜索中的学院
                this.searchingDept = dept
                document.getElementById('deptMenu').innerText = dept.name
            },
            getCourseByDept() { // 搜索某学院的课程
                if (this.searchingDept.name === '') {
                    info(this.$toastr, '请先选择学院！', '提示：')
                    return
                }
                this.$axios.get(
                    'api/course/getByDept/' + this.searchingDept.name
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '查询开课列表成功！')
                        this.courses = JSON.parse(response.data.clist)
                        document.getElementById('tableTitle').innerText
                            = this.searchingDept.name + ' 开课列表'
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
                sessionStorage.setItem('searchingCID', course.id)
                sessionStorage.setItem('searchingCNAME', course.name)
                location.href = '/#/student/course'
            },
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
    div {
        font-size: 20px;
    }
    a::before {
        vertical-align: middle;
        /*padding-right: 5px;*/
    }
</style>