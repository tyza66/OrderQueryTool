<template>
    <el-row>
        <el-col :span="24">
            <el-row style="height:15vh"></el-row>
            <el-row style="height:65vh">
                <el-col :span="8"></el-col>
                <el-col :span="8">
                    <el-row style="width: 100%;height:100%;">
                        <el-col :span="24">
                            <h2 style="text-align:center;line-height:60px;">用户注册</h2>
                            <el-form ref="form" :model="form" label-width="80px" style="width: 100%;height:100%;">
                                账号：<el-input v-model="form.username" placeholder="请输入账号" style="margin-bottom: 10px;"></el-input>
                                密码：
                                <el-input type="password" v-model="form.password" placeholder="请输入密码"
                                style="margin-bottom: 10px;"></el-input>
                                重复密码：
                                <el-input type="password" v-model="form.repassword" placeholder="请输入重复密码"
                                style="margin-bottom: 10px;"></el-input>
                                姓名：
                                <el-input placeholder="请输入姓名" v-model="form.name"  style="margin-bottom: 10px;"></el-input>
                                邮箱:
                                <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
                                <div class="login-butts">
                                    <el-button type="primary" @click="regist()">注册</el-button>
                                    <el-button type="primary" @click="login()">登录</el-button>
                                </div>
                            </el-form>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <el-row style="height:20vh"></el-row>
        </el-col>
    </el-row>
</template>

<style></style>

<script>
import request from '@/utils/request';

export default {
    name: 'RegistView',
    components: {

    },
    data() {
        return {
            form: {
                username: '',
                password: '',
                repassword: '',
                name: '',
                email: ''
            }
        }
    },
    methods: {
        regist() {
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
            if (this.form.repassword == "") {
                this.$message({
                    message: '请输入重复密码',
                    type: 'warning'
                });
                return;
            }
            if (this.form.password != this.form.repassword) {
                this.$message({
                    message: '两次密码不一致',
                    type: 'warning'
                });
                return;
            }
            if (this.form.name == "") {
                this.$message({
                    message: '请输入姓名',
                    type: 'warning'
                });
                return;
            }
            if (this.form.email == "") {
                this.$message({
                    message: '请输入邮箱',
                    type: 'warning'
                });
                return;
            }
            const regEmail = /^([a-zA-Z]|[0-9])(\w)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
            //对邮箱进行正则匹配
            if (!regEmail.test(this.form.email)) {
                this.$message({
                    message: '邮箱格式不正确',
                    type: 'warning'
                });
                return;
            }
            request.post("/user/register",{
                "userAccount": this.form.username,
                "password": this.form.password,
                "userName": this.form.name,
                "email": this.form.email
            }).then(res=>{
                if(res.code == 200){
                    this.$message({
                        message: '注册成功，一秒后跳转到登录界面',
                        type: 'success'
                    });
                    setTimeout(() => {
                        this.$router.push({ path: '/login' })
                    }, 1000);
                }else{
                    this.$message({
                        message: res.error,
                        type: 'warning'
                    });
                }
            }).catch(err => {
                console.log(err);
            })
        },
        login() {
            this.$router.push({ path: '/login' })
        }
    }
}
</script>