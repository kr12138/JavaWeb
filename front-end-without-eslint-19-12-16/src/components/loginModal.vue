<template>
    <!-- 模态框（Modal） -->
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title" id="myModalLabel">
                        登录
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    
                </div>
                <div class="modal-body">

                    <login/>

                </div> <br> <br>

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</template>

<script>
    import login from "./login.vue";

    export default {
        name: "loginModal",
        components: {
            login,
        },

    }
</script>

<style scoped>

    template { font-family: Consolas, Inconsolata, "微软雅黑" }

</style>