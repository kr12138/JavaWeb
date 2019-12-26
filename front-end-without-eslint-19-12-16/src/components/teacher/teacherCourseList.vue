<template>
    <div class="container">
        <h2> 我的开课 </h2> <br>
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

    </div>
</template>

<script>
    import {info, cError, cSuccess} from "@/myToastr";
    export default {
        name: "teacherCourseList",
        mounted() {
            let tid = sessionStorage['id']
            if (tid != 0 && !tid) {
                cError(this.$toastr, '用户已退出！<br>请重新登陆！', '错误：')
                location.href = '/#/frontPage/login'
            } else {
                this.getByUid(tid)
            }
        },
        data() {
            return {
                titles: [ '课程编号', '课程名称', '开课学院', '课程说明', '同学提问' ],
                courses: [],
            }
        },
        methods: {
            getByUid(uid) { // 从教师工号获得数据
                this.$axios.get(
                    'api/teaches/getByUid/' + uid
                ).then(response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.courses = JSON.parse(response.data.clist)
                    else
                        cError(this.$toastr, '无法得到授课数据！', '错误：')
                }).catch(error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
            },
            searching(data) {   // 确定要查看提问的课程
                if (data === null) {
                    cError(this.$toastr, '正在查询空对象！', '错误：')
                    return
                }
                sessionStorage.setItem('searchingCID', data.id)
                location.href = '/#/teacher/course'
            },
        },

    }
</script>

<style scoped>

</style>