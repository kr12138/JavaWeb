<template>
    <!-- 轮播 -->
    <div id="demo" class="carousel slide" data-ride="carousel">
        <!-- 指示符 -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- 轮播图片 -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="../img/1.png" class="img-fluid">
            </div>
            <div class="carousel-item">
                <img src="../img/2.png" class="img-fluid">
            </div>
            <div class="carousel-item">
                <img src="../img/3.png" class="img-fluid">
            </div>
        </div>

        <!-- 左右切换按钮 -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>

    </div>
</template>

<script>
    export default {
        name: "home"
    }
</script>

<style scoped>

</style>