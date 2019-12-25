<template>
    <div>
        <h3 class="modal-title" id="myModalLabel2"> 增删授课教师 </h3> <br>
        <div class="container-fluid">
            <h5> 课程编号 ： {{ searchingC.id }} </h5> <br>
            <h5> 课程名称 ： {{ searchingC.name }} </h5> <br>
            <table class="table table-hover col-12 col-md-10 offset-md-1 col-lg-8 offset-lg-2 col-xl-6 offset-xl-3">
                <thead>
                <tr>
                    <th> 教师id </th>
                    <th> 教师姓名 </th>
                    <th> 增删 </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th> <input type="number" class="form-control"
                                placeholder="在此输入新教师工号"
                                v-model=" newTeacherID "
                                @keyup=" searchT() "> </th>
                    <th> {{ newTeacherName }} </th>
                    <th> <button class="btn btn-info" @click=" addT "> 新增 </button> </th>
                </tr>
                <tr v-for=" t in searchingC.tlist ">
                    <th> {{ t.id }} </th>
                    <th> {{ t.name }} </th>
                    <th> <button class="btn btn-info" @click=" deleteT( t.id ) "> 删除 </button> </th>
                </tr>
                </tbody>
            </table>
        </div> <br> <br>
    </div>

</template>

<script>
    import {info, cError, cSuccess} from "@/myToastr";

    export default {
        name: "adminTeachesC",
        data() {
            return {
                searchingC: { id:'', name:'', tlist:[] },   // 增删教师的课程
                newTeacherID: undefined, // 新增教师ID
                newTeacherName: ''   // 新增教师名，需查询
            }
        },
        mounted() {
            let cid = sessionStorage.getItem('searchingCID')
            let cname = sessionStorage.getItem('searchingCNAME')
            // console.log('searchingC.id ', cid)
            // console.log('searchingC.name ', cname)
            if (cid !== undefined) {
                this.searchingC.id = cid
                // this.$set(this.searchingC, "id", cid)
                sessionStorage.removeItem('searchingCID')
                this.searchingC.name = cname
                // this.$set(this.searchingC, "name", cname)
                sessionStorage.removeItem('searchingCNAME')
                this.getByCid(cid)
            }
        },
        methods: {
            getByCid(cid) { // 从课程编号获得数据
                this.$axios.get(
                    'api/teaches/getByCid/' + cid
                ).then(response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.searchingC.tlist = JSON.parse(response.data.tlist)
                    else
                        cError(this.$toastr, '无法得到教师数据！', '错误：')
                }).catch(error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            searchT() {  // 查教师名
                if (!this.newTeacherID) {
                    this.newTeacherName = '无该教师！'
                    return
                }
                this.$axios.get(
                    'api/teacher/get/' + this.newTeacherID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.newTeacherName = JSON.parse(response.data.teacher).name
                    else
                        this.newTeacherName = '无该教师！'
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            addT() {    // 增加教师
                if (!this.searchingC.id && this.searchingC.id !== 0) {
                    cError(this.$toastr, '无课程编号', '错误：')
                    return
                } else if (!this.newTeacherID) {
                    info(this.$toastr, '请先输入教师工号', '提示：')
                    return
                } else if (!this.newTeacherName || this.newTeacherName === '无该教师！') {
                    info(this.$toastr, '请确认教师工号无误', '提示：')
                    return
                }
                this.$axios.post(
                    'api/teaches/add',{
                        uid: this.newTeacherID,
                        cid: this.searchingC.id
                    }).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        cSuccess(this.$toastr, '添加成功！')
                    else
                        cError(this.$toastr, '添加失败<br>可能已有该教师？')
                    this.getByCid(this.searchingC.id)
                }).catch ( error => {
                    console.log('！！！添加失败异常：')
                    console.log(error)
                });
            },
            deleteT(x) {    // 删除教师
                if (!this.searchingC.id && this.searchingC.id !== 0) {
                    cError(this.$toastr, '无课程编号', '错误：')
                    return
                } else if (!x && x !== 0) {
                    cError(this.$toastr, '无教师工号', '错误：')
                    return
                }
                this.$axios.post(
                    'api/teaches/delete',{
                        uid: x,
                        cid: this.searchingC.id
                    }).then ( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        cSuccess(this.$toastr, '删除成功！')
                    else
                        cError(this.$toastr, '删除失败<br>可能无该教师？')
                    this.getByCid(this.searchingC.id)
                }).catch ( error => {
                    console.log('！！！删除失败异常：')
                    console.log(error)
                });
            },
        },
    }
</script>

<style scoped>

</style>