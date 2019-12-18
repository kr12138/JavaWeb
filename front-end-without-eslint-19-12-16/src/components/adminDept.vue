<template>
    <div class="container">
        <h2> 学院列表 </h2> <br>
        <table class="table table-hover">
            <thead>
            <tr>
                <th> 学院编号 </th>
                <th> 学院名称 </th>
                <th> 学院说明 </th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="t in dept">
                    <th v-for="tt in t"> {{tt}} </th>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "adminDept",
        mounted() {
            this.getDepts();
        },
        data() {
            return {
                dept: [
                    // {id:1, name:'光电', info:'光电结棍！'},
                    // {id:2, name:'计院', info:'光电结棍和我计院有什么关系吗？'},
                    // {id:3, name:'理院', info:'计院不结棍不代表我理院结棍'},
                ],
            }
        },
        methods: {
            getDepts: function () {
                this.$axios({
                    method: 'get',
                    url: 'http://localhost:8080/api/dept/getAll',
                    responseType: 'json'
                }).then((response)=> {
                    console.log(response)
                }).catch((error)=> {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });

            }
        }
    }
</script>

<style scoped>
    .container { font-family: Consolas, Inconsolata, "微软雅黑" }
</style>