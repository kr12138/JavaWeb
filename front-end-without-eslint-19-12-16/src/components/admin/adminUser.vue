<template>
    <div class="container">
        <h2> 用户列表 [ {{ page+1 }} / {{ totalPages }} ] </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>
            <tr class="container-fluid">
                <th> <input type="number" class="form-control"
                            placeholder="在此输入新用户ID"
                            v-model=" newData.id "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新用户昵称"
                            v-model=" newData.name "> </th>
                <th> <input type="text" class="form-control"
                            placeholder="在此输入新用户密码"
                            v-model=" newData.password "> </th>
                <th>
<!--                    <input type="number" class="form-control"-->
<!--                           placeholder="(0:管理 1:学生 2:教师) 在此输入新用户权限"-->
<!--                           v-model=" newData.identity "> -->
                    <div class="dropdown">
                        <button type="button" class="btn btn-info dropdown-toggle"
                                id="identityMenu"
                                data-toggle="dropdown"> 在此选择新用户权限 </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#"
                               @click=" handleIdentityClick( 0 ) "> 0（管理员）
                            </a>
                            <a class="dropdown-item" href="#"
                               @click=" handleIdentityClick( 1 ) "> 1（学生）
                            </a>
                            <a class="dropdown-item" href="#"
                               @click=" handleIdentityClick( 2 ) "> 2（教师）
                            </a>
                        </div>
                    </div>
                </th>
                <th> <button class="btn btn-info" @click=" add "> 增加 </button> </th>
            </tr>
            <tr v-for=" user in users ">
                <th> {{ user.id }} </th>
                <th> {{ user.name }} </th>
                <th> {{ user.password }} </th>
                <th> {{ identityInfo[user.identity] }} </th>
                <th> <button class="btn btn-info"
                             data-toggle="modal" data-target="#changingModal"
                             @click=" changing( user ) " > 删改 </button> </th>
            </tr>
            </tbody>
            <div>

            </div>
        </table>

        <!-- 模态框（Modal） -->
        <div class="modal fade" tabindex="-1" role="dialog" id="changingModal"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel"> 删改用户 </h4>
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true"> &times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5> 用户编号 ： {{ changingData.id }} </h5> <br>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 用户昵称 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="name" v-model=" changingData.name ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 用户密码 </span>
                            </span>
                            <input type="text" class="form-control"
                                   id="password" v-model=" changingData.password ">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-prepend">
                                <span class="input-group-text"> 用户权限 </span>
                            </span>
<!--                            <input type="number" class="form-control"-->
<!--                                   id="identity" v-model=" changingData.identity ">-->
                            <div class="dropdown">
                                <button type="button" class="btn btn-info dropdown-toggle"
                                        id="identityMenu2"
                                        data-toggle="dropdown"> 在此选择新用户权限 </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#"
                                       @click=" handleIdentityClick2( 0 ) "> 0(管理员)
                                    </a>
                                    <a class="dropdown-item" href="#"
                                       @click=" handleIdentityClick2( 1 ) "> 1(学生)
                                    </a>
                                    <a class="dropdown-item" href="#"
                                       @click=" handleIdentityClick2( 2 ) "> 2(教师)
                                    </a>
                                </div>
                            </div>
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
        <button class="btn btn-outline-info page" @click=" getData( 0 ) "> 首页 </button>
        <button class="btn btn-outline-info page" @click=" getData( page-1 ) "> &lt;&lt;上一页 </button>
        <button class="btn btn-outline-info page"> 第 {{ page+1 }} 页 </button>
        <button class="btn btn-outline-info page" @click=" getData( page+1 ) "> 下一页&gt;&gt; </button>
        <button class="btn btn-outline-info page" @click=" getData( totalPages-1 ) "> 末页 </button>
    </div>
</template>

<script>
    import {info, cInfo, cError, cSuccess} from "../../myToastr.js";
    export default {
        name: "adminUser",
        mounted() {
            this.getData(0);
        },
        data() {
            return {
                newData: { id: undefined, name: '', password: '', identity: undefined },
                page: 0,
                totalPages: undefined,
                changingData: {},
                titles: [ '用户ID', '用户昵称', '用户密码', '用户权限', '更改' ],
                identityInfo: ['0(管理员)', '1(学生)', '2(教师)'],
                users: [],
            }
        },
        methods: {
            getData(page) {    //初始化
                console.log('正在请求页码：'+page)
                if (page<0) {
                    cInfo(this.$toastr, '已是首页，无上一页', '提示')
                    return
                } else if (page >= this.totalPages) {
                    cInfo(this.$toastr, '已是末页，无下一页', '提示')
                    return
                }
                this.$axios.get(
                    'http://localhost:8080/api/user/getAll/'+page
                ).then( response => {
                    console.log(response)
                    if (response.data.flag !== 'true')
                        cError(this.$toastr, '无法得到用户数据！', '错误：')
                    else {
                        this.page = page
                        this.users = JSON.parse(response.data.users)
                        this.totalPages = JSON.parse(response.data.totalPages)
                    }
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            handleIdentityClick(x) {    //更改新用户权限
                this.newData.identity = x
                document.getElementById('identityMenu').innerText = this.identityInfo[x]
            },
            handleIdentityClick2(x) {   //更改待删改用户权限
                this.changingData.identity = x
                document.getElementById('identityMenu2').innerText = this.identityInfo[x]
            },
            add() { //增加
                // console.log('add ', this.newDept)
                if (!this.newData.id && this.newData.id !== 0) {
                    info(this.$toastr, '请先输入编号', '提示：')
                    return
                } else if (!this.newData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.newData.password) {
                    info(this.$toastr, '请先输入密码', '提示：')
                    return
                } else if (!this.newData.identity && this.newData.identity !== 0) {
                    info(this.$toastr, '请先输入权限', '提示：')
                    return
                }
                this.$axios.post(
                    'http://localhost:8080/api/user/add', this.newData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '添加失败<br>可能已有该编号？')
                    else {
                        cSuccess(this.$toastr, '添加成功！')
                        this.getData(this.page)
                    }
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            changing(data) {    //确定删改对象
                if (data === null) {
                    cError(this.$toastr, '正在删改空对象！', '错误：')
                    return
                }
                this.changingData = data
                document.getElementById('name').placeholder = data.name
                document.getElementById('password').placeholder = data.password
                document.getElementById('identityMenu2').innerText = this.identityInfo[data.identity]
            },
            del() {  //删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无用户编号！请重试', '错误：')
                    return
                }
                this.$axios.post(
                    'http://localhost:8080/api/user/delete', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '删除失败<br>可能无该编号？', '错误：')
                    else {
                        cSuccess(this.$toastr, '删除成功！')
                        this.getData(this.page)
                    }
                }).catch ( error => {
                    console.log('！！！删除失败异常：')
                    console.log(error)
                });
            },
            update() {  //更改
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无用户编号！请重试', '错误：')
                    return
                } else if (!this.changingData.name) {
                    info(this.$toastr, '请先输入名称', '提示：')
                    return
                } else if (!this.changingData.password) {
                    info(this.$toastr, '请先输入密码', '提示：')
                    return
                } else if (!this.changingData.identity && this.newData.identity !== '0') {
                    info(this.$toastr, '请先输入权限', '提示：')
                    return
                }
                this.$axios.put(
                    'http://localhost:8080/api/user/update', this.changingData
                ).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'false')
                        cError(this.$toastr, '更新失败<br>可能无该编号？')
                    else {
                        cSuccess(this.$toastr, '更新成功！')
                        this.getData(this.page)
                    }
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
    .page { margin: 5px; }
</style>