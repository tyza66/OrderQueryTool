<template>
  <el-config-provider :locale="locale">
    <el-row class="main">
      <el-col :span="4">
        <div class="main-left">
          <div class="main-title">
            订单查询页面
          </div>
          <div class="main-item" @click="routerTo(0)" v-show="!logined">
            登录
          </div>
          <div class="main-item" @click="routerTo(1)" v-show="!logined">
            注册
          </div>
          <div class="main-item" @click="routerTo(2)" v-show="logined">
            订单
          </div>
          <div class="main-item" @click="routerTo(3)">
            关于
          </div>
        </div>
      </el-col>
      <el-col :span="20">
        <RouterView></RouterView>
      </el-col>
    </el-row>
  </el-config-provider>
</template>

<script>
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import request from "./utils/request";

export default {
  name: 'App',
  components: {
    [ElConfigProvider.name]: ElConfigProvider
  },
  data(){
    return{
      locale: zhCn,
      logined: false
    }
  },
  mounted() {
    this.checkLogin()
  },
  methods: {
    checkLogin() {
      request.get('/user/check', {
        headers: {
          "satoken": this.getCookie("satoken")
        }
      }
      ).then(res => {
        console.log(res);
        if (res.code == 200) {
          this.logined = true
        }else{
          this.logined = false
        }
      }).catch(err => {
        console.log(err);
      })
    },
    routerTo(id) {
      if (id == 0) {
        this.$router.push({ path: '/login' })
      } else if (id == 1) {
        this.$router.push({ path: '/regist' })
      } else if (id == 2) {
        this.$router.push({ path: '/bill' })
      } else if (id == 3) {
        this.$router.push({ path: '/about' })
      }
    }, setCookie(key, value) {
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
        }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.main {
  height: 100%;
}

.main-left {
  width: 100%;
  height: 100vh;
  float: left;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgb(160, 228, 206);
}

.main-title {
  font-size: 30px;
  text-align: center;
  margin-top: 30px;
  font-family: 'Courier New', Courier, monospace;
}

.main-item {
  font-size: 20px;
  text-align: center;
  margin-top: 20px;
  width: 80%;
  height: 40px;
  border: 1px solid #223067;
  border-radius: 5px;
  text-align: center;
  line-height: 40px;
  cursor: pointer;
}

RouterView {
  height: 100vh;
}</style>
