<template>
    <div class="container-fluid" v-show=" title !== '' ">
        <h2> {{ title }} </h2>
        <div class="card">
            <div class="row" >
                <div class="col-3"> <br>
                    <img :src=" avatar "
                         class="img-thumbnail"
                         style="height: 100px; box-shadow: gray 5px 5px 3px;"> <br>
                    <div class="glyphicon glyphicon-bookmark"
                         style="color: slategray; text-shadow: #bcbcbc 2px 1px 2px;"
                         > 学生 </div> <br>
                    {{ name }}
                </div>
                <div class="offset-1 col-7"
                     style="font-size: 24px; text-align: left"> <br>
                    {{ content }} <br> <br>
                    <img :src=" img " v-if=" img !== '' " class="img-fluid">
                    <div class="col-5 offset-6"
                         style="font-size: 18px; text-align: right"> <br> <br>
                        发表于 {{ date }}
                    </div>
                </div>
            </div> <br>
        </div>
        <div class="card" v-for=" ans in answerDetails ">
            <div class="row">
                <div class="col-3"> <br>
                    <img :src=" ans.avatar "
                         class="img-thumbnail"
                         style="height: 100px; box-shadow: gray 5px 5px 3px;"> <br>
                    <div class="glyphicon glyphicon-book"
                         style="color: slategray; text-shadow: #bcbcbc 2px 1px 2px;"
                         > {{ ans.prof }} </div> <br>
                    {{ ans.name }}
                </div>
                <div class="offset-1 col-7"
                     style="font-size: 24px; text-align: left"> <br>
                    {{ ans.content }} <br> <br>
                    <img :src=" ans.img " v-if=" ans.img !== '' " class="img-fluid">
                    <div class="col-5 offset-6"
                         style="font-size: 18px; text-align: right"> <br> <br>
                        发表于 {{ ans.date }}
                    </div>
                </div>
            </div> <br>
        </div>
    </div>
</template>

<script>
    import {info, cInfo, cError, success} from "../../myToastr.js";
    export default {
        name: "studentQuestionShow",
        mounted() {
            this.getData();
        },
        data() {
            return {
                showingQID: undefined,
                showingQ: null,
                title: '',
                content: '',
                avatar: '',
                name: '',
                date: '',
                img: '',
                answerDetails: [],
            }
        },
        methods: {
            getData() { // 初始化
                let tempQID = sessionStorage['showingQID']
                if (tempQID === undefined) {
                    cError(this.$toastr, '无提问ID，请重新从提问列表进入本页面！', '错误：')
                    if (sessionStorage['identity'] == 0)
                        location.href = '/#/admin/question'
                    else if (sessionStorage['identity'] == 2)
                        location.href = '/#/teacher/course'
                    else if (sessionStorage['identity'] == 1)
                        location.href = '/#/student/question'
                    else
                        cError(this.$toastr, '无权限身份，请重新登录！', '错误：')
                    return
                }
                // sessionStorage.removeItem('showingQID') // 计划在提交回复时使用它，因此不删
                console.log('Showing Question ' + tempQID)
                this.$axios.get(
                    'api/question/getDetails/' + tempQID
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '成功得到提问数据！')
                        // this.showingQ = JSON.parse(response.data.question)
                        this.title = response.data.title
                        this.content = response.data.content
                        if (response.data.avatar)
                            this.avatar = unescape(response.data.avatar)
                        else
                            this.avatar = ''
                        this.name = response.data.name
                        this.date = response.data.date
                        if (response.data.img)
                            this.img = unescape(response.data.img)
                        else
                            this.img = null
                    } else
                        cError(this.$toastr, '无法得到提问数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });
                this.$axios.get(
                    'api/answer/getDetailsByQid/' + tempQID + '/' + sessionStorage['id']
                ).then( response => {
                    console.log(response)
                    if (response.data.flag === 'true') {
                        success(this.$toastr, '成功得到回答数据！')
                        this.answerDetails = JSON.parse(response.data.rlist)
                        let sz = this.answerDetails.length
                        for (let i=0; i<sz; ++i) {
                            this.answerDetails[i].avatar = unescape(this.answerDetails[i].avatar)
                            this.answerDetails[i].img = unescape(this.answerDetails[i].img)
                        }
                        // this.title = response.data.title
                        // this.content = response.data.content
                        // if (response.data.avatar)
                        //     this.avatar = unescape(response.data.avatar)
                        // else
                        //     this.avatar = ''
                        // this.name = response.data.name
                        // this.date = response.data.date
                        // if (response.data.img)
                        //     this.img = unescape(response.data.img)
                        // else
                        //     this.img = null
                    } else
                        cError(this.$toastr, '无法得到回答数据！', '错误：')
                }).catch( error => {
                    console.log('！！！请求数据失败异常：')
                    console.log(error)
                });

            },

        },

    }

</script>

<style scoped>
    .container-fluid {
        font-family: Consolas, Inconsolata, "微软雅黑";
        font-size: 20px;
    }
    div::before {
        vertical-align: middle;
        /*padding-right: 5px;*/
    }
</style>