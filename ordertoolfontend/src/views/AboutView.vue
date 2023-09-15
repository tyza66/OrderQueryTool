<template>
    <el-row>
        <el-col :span="24" class="about-bg">
            <div class="about-about">
                <h1 style="text-align: center;">订单查询</h1>
                <h3 style="text-align: center;margin-top:25px;">版本号：1.0</h3>
                <h3 style="text-align: center;">开发者：Github-tyza66、微信公众号-程序员孙达明、抖音-程序员孙达明、哔哩哔哩-程序员孙达明</h3>
                <h3 style="text-align: center;">本软件遵循MIT开源协议（MIT License）</h3>
            </div>
        </el-col>
    </el-row>
</template>

<style>
.about-bg{
    height: 100vh;
}
.about-about{
    margin-top: 120px;
    height: 420px;
    width: 100%;
}
</style>

<script>
export default {
    name: 'AboutView',
    components: {

    },
    data() {
        return {
        }
    },
    methods: {

    }
}
</script>