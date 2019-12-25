<template>
    <div class="container">
        <h2> 教师列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr class="container-fluid">
                <th> <input type="number" class="form-control"
                            placeholder="在此输入新教师工号"
                            v-model=" newData.id "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新教师姓名"
                            v-model=" newData.name "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新教师职称"
                            v-model=" newData.prof "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新教师说明"
                            v-model=" newData.info "> </th>
                <th> <button class="btn btn-info" @click=" add "> 新增 </button> </th>
            </tr>
            <tr v-for=" teacher in teachers ">
                <th> {{ teacher.id }} </th>
                <th> {{ teacher.name }} </th>
                <th> {{ teacher.prof }} </th>
                <th> {{ teacher.info }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( teacher ) " > 删改 </button> </th>
                <th> <button class="btn btn-info"
                             @click=" searching( teacher ) " > 查看 </button> </th>
            </tr>
            </tbody>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改教师 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 教师编号 ： {{ changingData.id }} </h5> <br>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 教师姓名 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="name" v-model=" changingData.name ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 教师职称 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="prof" v-model=" changingData.prof ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 教师说明 </span>
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
        name: "adminTeacher",
        mounted() {
            this.getData();
        },
        data() {
            return {
                newData: { id: undefined, name: '', prof: '', info: '' },   // 新增教师
                changingData: {},   // 删改教师
                titles: [ '教师工号', '教师名称', '教师职称', '教师说明', '增删改', '授课' ],
                teachers: [],
            }
        },
        methods: {
            getData() {    // 初始化
                this.$axios.get(
                    'api/teacher/getAll'
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.teachers = JSON.parse(response.data.teachers)
                    else
                        cError(this.$toastr, '无法得到教师数据！', '错误：')
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
                document.getElementById('name').placeholder = data.name
                document.getElementById('prof').placeholder = data.prof
                document.getElementById('info').placeholder = data.info
            },
            add() { // 增加
                if (!this.newData.id && this.newData.id !== 0) {
                    info(this.$toastr, '请先输入编号', '提示：')
                    return
                } else if (!this.newData.name) {
                    info(this.$toastr, '请先输入姓名', '提示：')
                    return
                } else if (!this.newData.prof) {
                    info(this.$toastr, '请先输入职称', '提示：')
                    return
                }  else if (!this.newData.info) {
                    info(this.$toastr, '请先输入说明', '提示：')
                    return
                }
                this.$axios.post(
                    'api/teacher/add', this.newData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '添加成功！')
                        this.getData()
                    } else
                        cError(this.$toastr, '添加失败<br>可能已有该工号？')
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            del() {  // 删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无教师工号！请重试', '错误：')
                    return
                }
                this.$axios.post(
                    'api/teacher/delete', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '删除成功！')
                        this.getData()
                    }
                    else
                        cError(this.$toastr, '删除失败<br>可能无该编号？', '错误：')

                }).catch ( error => {
                    console.log('！！！删除失败异常：')
                    console.log(error)
                });
            },
            update() {  // 更改
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无教师工号！请重试', '错误：')
                    return
                } else if (!this.changingData.name) {
                    info(this.$toastr, '请先输入姓名', '提示：')
                    return
                } else if (!this.changingData.info) {
                    info(this.$toastr, '请先输入说明', '提示：')
                    return
                }
                this.$axios.put(
                    'api/teacher/update', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '更新成功！')
                        this.getData()
                    }
                    else
                        cError(this.$toastr, '更新失败<br>可能无该编号？')
                }).catch ( error => {
                    console.log('！！！更新失败异常：')
                    console.log(error)
                });
            },
            searching(data) {   // 确定要查看课程的教师
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                // eventBus.$emit('teachesT', data)
                sessionStorage.setItem('searchingTID', data.id)
                sessionStorage.setItem('searchingTNAME', data.name)
                location.href = '/#/admin/teachesT'
            },

        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>