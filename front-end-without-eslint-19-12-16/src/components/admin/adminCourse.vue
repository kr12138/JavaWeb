<template>
    <div class="container">
        <h2> 课程列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr class="container-fluid">
                <th> <input type="number" class="form-control"
                            placeholder="在此输入新课程编号"
                            v-model=" newData.id "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新课程名称"
                            v-model=" newData.name "> </th>
                <th>
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-secondary dropdown-toggle"
                                id="deptMenu"
                                data-toggle="dropdown"> 在此选择新开课学院 </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#"
                               v-for=" dept in depts "
                               @click=" handleDeptClick( dept.name ) "> {{ dept.name }}
                            </a>
                        </div>
                    </div>
                </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新课程说明"
                            v-model=" newData.info "> </th>
                <th> <button class="btn btn-info" @click=" add "> 新增 </button> </th>
            </tr>
            <tr v-for=" course in courses ">
                <th> {{ course.id }} </th>
                <th> {{ course.name }} </th>
                <th> {{ course.dept }} </th>
                <th> {{ course.info }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( course ) " > 删改 </button> </th>
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
        name: "adminCourse",
        mounted() {
            this.getData();
        },
        data() {
            return {
                newData: { id: undefined, name: '', dept: '', info: '' },
                changingData: {},
                titles: [ '课程编号', '课程名称', '开课学院', '课程说明', '增删改', '教师'],
                depts: [],
                courses: [],
            }
        },
        methods: {
            getData() {    //初始化
                // this.$axios.get(
                //     'http://localhost:8080/api/course/getAll'
                console.log('getting by token: ')
                console.log(sessionStorage['token'])
                this.$axios({
                        methods: 'get',
                        url: 'http://localhost:8080/api/course/getAll',
                        // headers: { 'token': sessionStorage['token'] }
                    }
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
                    'http://localhost:8080/api/dept/getAll',
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
            handleDeptClick(name) { //更改新课程学院
                this.newData.dept = name
                document.getElementById('deptMenu').innerText = name
            },
            handleDeptClick2(name) {    //更改待删改课程学院
                this.changingData.dept = name
                document.getElementById('deptMenu2').innerText = name
            },
            changing(data) {    //确定删改对象
                if (data === null) {
                    cError(this.$toastr, '正在删改空对象！', '错误：')
                    return
                }
                this.changingData = data
                document.getElementById('name').placeholder = data.name
                document.getElementById('deptMenu2').innerText = data.dept
                document.getElementById('info').placeholder = data.info
            },
            add() { //增加
                if (!this.newData.id && this.newData.id !== 0) {
                    info(this.$toastr, '请先输入编号', '提示：')
                    return
                } else if (!this.newData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.newData.dept) {
                    info(this.$toastr, '请先选择学院', '提示：')
                    return
                }  else if (!this.newData.info) {
                    info(this.$toastr, '请先输入说明', '提示：')
                    return
                }
                this.$axios.post(
                    'http://localhost:8080/api/course/add', this.newData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '添加失败<br>可能已有该编号？')
                    else {
                        cSuccess(this.$toastr, '添加成功！')
                        this.getData()
                    }
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            del() {  //删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无课程编号！请重试', '错误：')
                    return
                }
                this.$axios.post(
                    'http://localhost:8080/api/course/delete', this.changingData
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
            update() {  //更改
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无课程编号！请重试', '错误：')
                    return
                } else if (!this.changingData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.changingData.dept) {
                    info(this.$toastr, '请先选择学院', '提示：')
                    return
                }
                this.$axios.put(
                    'http://localhost:8080/api/course/update', this.changingData
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
            searching(data) {   //确定要查看教师的课程
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage.setItem('searchingCID', data.id)
                sessionStorage.setItem('searchingCNAME', data.name)
                location.href = '/#/admin/teachesC'
            },
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>