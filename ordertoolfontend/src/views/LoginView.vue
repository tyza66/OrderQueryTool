<template>
    <el-row>
        <el-col :span="24">
            <el-row style="height:25vh"></el-row>
            <el-row style="height:33vh">
                <el-col :span="8"></el-col>
                <el-col :span="8">
                    <el-row style="width: 100%;height:100%;">
                        <el-col :span="24">
                            <h2 style="text-align:center;line-height:60px;">用户登录</h2>
                            <el-form ref="form" :model="form" label-width="80px" style="width: 100%;height:100%;">
                                账号：
                                <el-input v-model="form.username" placeholder="请输入账号" style="margin-bottom: 10px;"></el-input>
                                密码：
                                <el-input type="password" v-model="form.password" placeholder="请输入密码"
                                ></el-input>
                                <div class="login-butts">
                                    <el-button type="primary" @click="login()">登录</el-button>
                                    <el-button type="primary" @click="regist()">注册</el-button>
                                </div>
                            </el-form>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <el-row style="height:33vh"></el-row>
        </el-col>
    </el-row>
</template>

<style>
.login-butts {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-top: 15px;
}
</style>

<script>
import request from "../utils/request";

export default {
    name: 'LoginView',
    components: {

    },
    data() {
        return {
            form: {
                username: '',
                password: ''
            }
        }
    },
    methods: {
        setCookie(key, value) {
            // 构建新的cookie字符串  
            var cookie = key + '=' + value + ';';
            // 将新cookie字符串添加到现有的cookie字符串中  
            var existingCookies = document.cookie.split(';');
            for (var i = 0; i < existingCookies.length; i++) {
                if (existingCookies[i].trim().indexOf(key) === 0) {
                    // 如果找到指定的cookie键，则用新的值替换它  
                    existingCookies[i] = cookie;
                    break;
                }
            }
            //如果不存在就在后面直接拼接
            if (i == existingCookies.length) {
                existingCookies.push(cookie)
            }
            // 将修改后的cookie字符串重新组合并设置回document.cookie属性  
            document.cookie = existingCookies.join('');
        }, getCookie(key) {
            // 将所有cookie键和值存储在一个数组中  
            var cookies = document.cookie.split(';');
            // 遍历数组，查找指定键的cookie  
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i].trim();
                // 检查cookie是否以键值对的形式存在  
                if (cookie.indexOf('=')) {
                    var cookieParts = cookie.split('=');
                    // 如果cookie的第一个部分等于指定的键，则返回cookie的值  
                    if (cookieParts[0] === key) {
                        return cookieParts[1];
                    }
                }
            }
            // 如果没有找到指定的cookie键，则返回null或任何你指定的默认值  
            return null;
        }, login() {
            if (this.form.username == "") {
                this.$message({
                    message: '请输入账号',
                    type: 'warning'
                });
                return;
            }
            if (this.form.password == "") {
                this.$message({
                    message: '请输入密码',
                    type: 'warning'
                });
                return;
            }
            request.post("/user/login", {
                "userAccount": this.form.username,
                "password": this.form.password
            }).then(res => {
                console.log(res)
                if (res.code == 200) {
                    this.$message({
                        message: '登陆成功，一秒后将跳转',
                        type: 'success'
                    });
                    this.setCookie("satoken", res.token)
                    //this.$router.push('/bill')
                    //直接跳转到登录界面
                    window.location.href = "../bill"
                }else{
                    this.$message({
                        message: "账号或密码错误",
                        type: 'warning'
                    });
                }
            })
        },
        regist() {
            this.$router.push('/regist')
        }
    }
}
</script>