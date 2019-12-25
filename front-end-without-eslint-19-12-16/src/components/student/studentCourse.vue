<template>
    <div class="container">
        <div class="container-fluid">
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <input type="number" class="form-control"
                       placeholder="按教师工号"
                       v-model=" searchingTeacherID "
                       @keyup=" searchT() ">
                <div class="input-group-append">
                    <span class="input-group-text glyphicon glyphicon-search"
                    > 搜索 </span>
                    <!-- style="text-shadow: #aaaaaa 2px 1px 1px;"-->
                </div>
            </div>
            <div class="input-group mb-3 col-lg-8 offset-lg-2">
                <div class="dropdown">
                    <button type="button" class="btn btn-outline-secondary dropdown-toggle"
                            id="deptMenu"
                            data-toggle="dropdown"> 按开课学院 </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#"
                           v-for=" dept in depts "
                           @click=" handleDeptClick( dept.name ) "> {{ dept.name }}
                        </a>
                    </div>
                </div>
                <div class="input-group-append">
                    <span class="input-group-text glyphicon glyphicon-search"
                          style="margin-left: 3px;"
                    > 搜索 </span>
                </div>
            </div>
            <div id="courseName"> {{ searchingTeacherName }} </div>
        </div> <br>

        <h2> 课程列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr v-for=" course in courses ">
                <th> {{ course.id }} </th>
                <th> {{ course.name }} </th>
                <th> {{ course.dept }} </th>
                <th> {{ course.info }} </th>
                <th> <button class="btn btn-info"
                             @click=" searching( course ) " > 查看 </button> </th>
            </tr>
            </tbody>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改课程 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 课程编号 ： {{ changingData.id }} </h5> <br>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 课程名称 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="name" v-model=" changingData.name ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 开课学院 </span>
                            </span>
                            <div class="dropdown">
                                <button type="button" class="btn btn-info dropdown-toggle"
                                        id="deptMenu2"
                                        data-toggle="dropdown"> 在此更改开课学院 </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#"
                                       v-for=" dept in depts "
                                       @click=" handleDeptClick2( dept.name ) "> {{ dept.name }}
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 课程说明 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="info" v-model=" changingData.info ">
                        </div> <br> <br>
<!--                        <div class="row">-->
<!--                            <button type="button" class="btn btn-info col-2 offset-3"-->
<!--                                    data-dismiss="modal" aria-hidden="true"-->
<!--                                    @click=" update "> 更新 </button>-->
<!--                            <button type="button" class="btn btn-secondary col-2 offset-2"-->
<!--                                    data-dismiss="modal" aria-hidden="true"-->
<!--                                    @click=" del "> 删除 </button>-->
<!--                        </div>-->
                    </div> <br> <br>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div><!-- /.modaldiv -->
    </div>
</template>

<script>
    import {info, cError, cSuccess} from "../../myToastr.js";
    export default {
        name: "studentCourse",
        mounted() {
            this.getData();
        },
        data() {
            return {
                searchingTeacherID: undefined,
                searchingTeacherName: '',
                newData: { id: undefined, name: '', dept: '', info: '' },
                changingData: {},
                titles: [ '课程编号', '课程名称', '开课学院', '课程说明', '授课教师', '相关提问' ],
                depts: [],
                courses: [],
            }
        },
        methods: {
            getData() {    // 初始化
                console.log(sessionStorage['searchingCID'])
                this.$axios.get(
                        'api/course/getAll'
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.courses = JSON.parse(response.data.courses)
                    else
                        cError(this.$toastr, '无法得到课程数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
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
            handleDeptClick(name) { // 更改新课程学院
                this.newData.dept = name
                document.getElementById('deptMenu').innerText = name
            },
            handleDeptClick2(name) {    // 更改待删改课程学院
                this.changingData.dept = name
                document.getElementById('deptMenu2').innerText = name
            },
            changing(data) {    // 确定删改对象
                if (data === null) {
                    cError(this.$toastr, '正在删改空对象！', '错误：')
                    return
                }
                this.changingData = data
                document.getElementById('name').placeholder = data.name
                document.getElementById('deptMenu2').innerText = data.dept
                document.getElementById('info').placeholder = data.info
            },
            searching(data) {   // 确定要查看教师的课程
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage.setItem('searchingCID', data.id)
                sessionStorage.setItem('searchingCNAME', data.name)
                location.href = '/#/admin/teachesC'
            },
            searchT() {  // 按教师工号查教师名
                this.$axios.get(
                    'api/teacher/get/' + this.searchingTeacherID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.searchingTeacherName = JSON.parse(response.data.teacher).name
                    else
                        this.searchingTeacherName = '无该教师！'
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