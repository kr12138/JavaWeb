<template>
    <div class="container-fluid" v-show=" showingQ !== null ">
        <h2> {{ showingQ.title }} </h2>
    </div>
</template>

<script>
    import {info, cInfo, cError, success} from "../myToastr.js";
    export default {
        name: "questionShow",
        mounted() {
            this.getData();
        },
        data() {
            return {
                showingQID: undefined,
                showingQ: null,
                titles: [ '回答编号', '回答内容', '回答时间', '回答情况', '查看详细' ],
                questions: [],
            }
        },
        methods: {
            getData() { // 初始化
                let tempQID = sessionStorage['showingQID']
                if (tempQID === undefined) {
                    cError(this.$axios, '无提问ID，请重新从提问列表进入本页面！', '错误：')
                    if (sessionStorage['identity'] === 0)
                        location.href = '/#/admin/question'
                    else if (sessionStorage['identity'] === 2)
                        location.href = '/#/teacher/course'
                    else
                        location.href = '/#/student/question'
                    return
                }
                // sessionStorage.removeItem('showingQID') // 计划在提交回复时使用它，因此不删
                console.log('Showing Question ' + tempQID)
                this.$axios.get(
                    'api/question/get/' + tempQID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true')
                        this.showingQ = JSON.parse(response.data.question)
                    else
                        cError(this.$toastr, '无法得到提问数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });

            },

        },

    }

</script>

<style scoped>
    .container-fluid { font-family: Consolas, Inconsolata, "微软雅黑" }
    span::before {
        vertical-align: middle;
        /*padding-right: 5px;*/
    }
</style>