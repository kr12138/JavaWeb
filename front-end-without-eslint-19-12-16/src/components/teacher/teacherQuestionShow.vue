<template>
    <div>
        <student-question-show> </student-question-show>
    </div>
</template>

<script>
    import {info, cInfo, cError, success} from "../../myToastr.js";
    import studentQuestionShow from "@/components/student/studentQuestionShow";
    export default {
        name: "teacherQuestionShow",
        components: {
            studentQuestionShow,
        },
    }
</script>

<style scoped>

</style>