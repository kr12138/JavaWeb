<template>
    <button @click=" t "></button>
</template>

<script>
    export default {
        name: "adminTeacher",
        methods: {
            t() {
                console.log('t()')
                let s = 'false'
                if(!s)
                    console.log('!s')
                if(s == 'false')
                    console.log('s == false')
            }
        }
    }
</script>

<style scoped>

</style>