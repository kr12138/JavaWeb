<template>
    <div class="container">
        <h2> 学院列表 </h2> <br>
        <table class="table table-hover">
            <thead>
                <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
                <tr class="container-fluid">
                    <th> <input type="number" class="form-control"
                                placeholder="在此输入新学院编号"
                                v-model=" newData.id "> </th>
                    <th> <input type="text" class="form-control"
                                placeholder="在此输入新学院名称"
                                v-model=" newData.name "> </th>
                    <th> <input type="text" class="form-control"
                                placeholder="在此输入新学院说明"
                                v-model=" newData.info "> </th>
                    <th> <button class="btn btn-info" @click=" add "> 新增 </button> </th>
                </tr>
                <tr v-for=" dept in depts ">
                    <th> {{ dept.id }} </th>
                    <th> {{ dept.name }} </th>
                    <th> {{ dept.info }} </th>
                    <th> <button class="btn btn-info"
                                 data-toggle="modal" data-target="#changingModal"
                                 @click=" changing( dept ) " > 删改 </button> </th>
                </tr>
            </tbody>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改学院 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 学院编号 ： {{ changingData.id }} </h5> <br>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 学院名称 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="name" v-model=" changingData.name ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 学院说明 </span>
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
        name: "adminDept",
        mounted() {
            this.getData();
        },
        data() {
            return {
                newData: { id: undefined, name: '', info: '' },
                changingData: {},
                titles: [ '学院编号', '学院名称', '学院说明', '增删改' ],
                depts: [],
            }
        },
        methods: {
            getData() {    // 初始化
                this.$axios.get(
                    'api/depts'
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
            changing(data) {    // 确定删改对象
                if (data === null) {
                    cError(this.$toastr, '正在删改空对象！', '错误：')
                    return
                }
                this.changingData = data
                document.getElementById('name').placeholder = data.name
                document.getElementById('info').placeholder = data.info
            },
            add() { // 增加
                // console.log('add ', this.newDept)
                if (!this.newData.id && this.newData.id !== 0) {
                    info(this.$toastr, '请先输入编号', '提示：')
                    return
                } else if (!this.newData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.newData.info) {
                    info(this.$toastr, '请先输入说明', '提示：')
                    return
                }
                this.$axios.post(
                    'api/dept/', this.newData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '添加成功！')
                        this.getData()
                    } else
                        cError(this.$toastr, '添加失败<br>可能已有该编号？')
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            del() {  // 删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无学院编号！请重试', '错误：')
                    return
                }
                this.$axios.delete(
                    'api/dept/' + this.changingData.id
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '删除成功！')
                        this.getData()
                    } else
                        cError(this.$toastr, '删除失败<br>可能无该编号？', '错误：')
                }).catch ( error => {
                    console.log('！！！删除失败异常：')
                    console.log(error)
                });
            },
            update() {  // 更改
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无学院编号！请重试', '错误：')
                    return
                } else if (!this.changingData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.changingData.info) {
                    info(this.$toastr, '请先输入说明', '提示：')
                    return
                }
                this.$axios.put(
                    'api/dept/', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '更新成功！')
                        this.getData()
                    } else
                        cError(this.$toastr, '更新失败<br>可能无该编号？')
                }).catch ( error => {
                    console.log('！！！更新失败异常：')
                    console.log(error)
                });
            },
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>