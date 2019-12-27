<template>
    <div class="container">
        <h2> 用户列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr> <th v-for=" title in titles "> {{ title }} </th> </tr>
            </thead>
            <tbody>

            <tr v-for=" user in users ">
                <th> {{ user.id }} </th>
                <th> {{ user.name }} </th>
                <th> <button class="btn btn-info"
                             @click=" add( user ) " > 赋予 </button> </th>
                <th> <button class="btn btn-info"
                             @click=" del( user ) " > 取回 </button> </th>
            </tr>

            </tbody>

        </table>

    </div>
</template>

<script>
    import {info, cInfo, cError, cSuccess} from "../../myToastr.js";
    export default {
        name: "teacherQuestionAuth",
        mounted() {
            this.getData();
        },
        data() {
            return {
                newData: { id: undefined, name: '', password: '', identity: undefined },
                titles: [ '用户ID', '用户昵称', '赋予权限', '取回权限' ],
                users: [],
            }
        },
        methods: {
            getData() {    // 初始化
                this.$axios.get(
                    'api/user/getAllStudents'
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        cSuccess(this.$toastr, '成功得到学生数据！')
                        this.users = JSON.parse(response.data.slist)
                    } else {
                        cError(this.$toastr, '无法得到用户数据！', '错误：')
                    }
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            add(user) { // 增加
                this.$axios.post(
                    'api/questionAuth/add', {
                        "sid": user.id,
                        "tid": sessionStorage['id']
                }).then ( response => {
                    console.log(response)
                    if (!response.data.flag || response.data.flag === 'false')
                        cError(this.$toastr, '添加失败<br>可能已有该编号？')
                    else {
                        cSuccess(this.$toastr, '赋予成功！')
                    }
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            del(user) {  // 删除
                if (!this.changingData.id && this.changingData.id !== 0) {
                    cError(this.$toastr, '无用户编号！请重试', '错误：')
                    return
                }
                this.$axios.post(
                    'api/user/delete', {
                        "sid": user.id,
                        "tid": sessionStorage['id']
                }).then ( response => {
                    console.log(response)
                    if (!response.data.flag || response.data.flag === 'false')
                        cError(this.$toastr, '删除失败<br>可能无该编号？', '错误：')
                    else {
                        cSuccess(this.$toastr, '取回成功！')
                    }
                }).catch ( error => {
                    console.log('！！！删除失败异常：')
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