<template>
    <el-row>
        <el-col :span="24" class="bill-bg">
            <el-row style="height:10vh;">
                <el-row style="height:5vh;width:100%;">
                    <el-col :span="6" class="bill-search">
                        用户ID：<el-input size="small" style="display:inline;width:150px;" placeholder="根据备注信息查询"
                            v-model="searchTable.userId"></el-input>
                    </el-col>
                    <el-col :span="6" class="bill-search">
                        页码：<el-select size="small" style="display:inline;width:150px;" placeholder="收入还是支出"
                            v-model="this.searchTable.page">
                            <el-option v-for="i in pages" :label="i" :value="i" :key="i" />
                        </el-select>
                    </el-col>
                    <el-col :span="6" class="bill-search">
                    </el-col>
                    <el-col :span="6" class="bill-search">
                        共{{ sum }}条记录，每页{{ searchTable.size }}条，当前第{{ searchTable.page }}页
                    </el-col>
                </el-row>
                <el-row style="height:5vh;width:100%;">
                    <el-col :span="9" class="bill-search">
                        下单时间： <el-date-picker size="small" style="display:inline;width:150px;" placeholder="选择起始时间"
                            v-model="searchTable.startTime"></el-date-picker>
                        -<el-date-picker size="small" style="display:inline;width:150px;" placeholder="选择直到时间"
                            v-model="searchTable.endTime"></el-date-picker>
                    </el-col>
                    <el-col :span="9" class="bill-search">
                        金额范围：<el-input size="small" style="display:inline;width:150px;" placeholder="最小金额"
                            v-model="searchTable.money1"></el-input>
                        -<el-input size="small" style="display:inline;width:150px;" placeholder="最大金额"  v-model="searchTable.money2"></el-input>
                    </el-col>
                    <el-col :span="6" class="bill-search">
                        <el-button type="primary" size="small" @click="clear()">清空</el-button>
                        <el-button type="primary" size="small" @click="getTbaleData()">查询</el-button>
                    </el-col>
                </el-row>
            </el-row>
            <el-row style="height:90vh">
                <el-table :data="tableData" height="100%" style="width: 100">
                    <el-table-column prop="userId" label="用户ID" width="150px" />
                    <el-table-column prop="userAccount" label="用户账号" width="150px" />
                    <el-table-column prop="userName" label="用户名" width="150px" />
                    <el-table-column prop="email" label="用户邮箱" width="150px" />
                    <el-table-column prop="orderId" label="订单编号" width="150px" />
                    <el-table-column prop="orderTime" label="下单时间" width="150px" />
                    <el-table-column prop="orderAmount" label="订单金额" width="150px" />
                    <el-table-column label="用户操作" width="200px">
                        <template #default="scope">
                            <el-button @click="edit1(scope.row)">修改</el-button>
                            <el-button @click="delete1(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-row>
        </el-col>
    </el-row>
    <el-dialog v-model="dialogVisible" title="Tips" width="30%" :before-close="handleClose">
        <div>
                            用户ID：<el-input size="small" style="display:inline;width:150px;" placeholder="用户ID"
                            v-model="dtable.userId"></el-input>
                            <br/>
                            订单编号：<el-input size="small" style="display:inline;width:150px;" placeholder="订单编号"
                            v-model="dtable.orderId"></el-input>
                            <br/>
                            下单时间：<el-date-picker size="small" style="display:inline;width:150px;" placeholder="下单时间"
                            v-model="dtable.orderTime"></el-date-picker>
                            <br/>
                            订单金额：<el-input size="small" style="display:inline;width:150px;" placeholder="订单金额"
                            v-model="dtable.orderAmount"></el-input>
        </div>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="edit2()">
                    提交
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<style>
.bill-bg {
    height: 100vh;
    background-color: rgb(234, 224, 210);
}

.bill-search {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
</style>

<script>
import request from "../utils/request";
export default {
    name: 'BillView',
    components: {

    },
    data() {
        return {
            tableData: [],
            sum: 0,
            pages: 1,
            searchTable: {
                userId: '',
                startTime: '',
                endTime: '',
                money1: '',
                money2: '',
                size: 12,
                page: 1
            },
            dialogVisible:false,
            dtable:{
                id : '',
                userId : '',
                orderId : '',
                orderTime : '',
                orderAmount : ''
            }
        }
    },
    mounted() {
        this.checkLogin()
        this.getTbaleData()
    },
    methods: {
        getTbaleData() {
            request.get("/order/get", {
                params: {
                    userId: this.searchTable.userId,
                    startTime: this.searchTable.startTime,
                    endTime: this.searchTable.endTime,
                    money1: this.searchTable.money1,
                    money2: this.searchTable.money2,
                    size: this.searchTable.size,
                    page: this.searchTable.page - 1
                }
            }).then(res => {
                console.log(res);
                if (res.code == 200) {
                    this.tableData = res.data
                    this.sum = res.data.size
                    if(this.sum % this.searchtable.size == 0){
                        this.pages = Math.ceil(this.sum / this.searchtable.size)

                    }else{
                        this.pages = Math.ceil(this.sum / this.searchtable.size) + 1
                    }
                }
            }).catch(err => {
                console.log(err);
            })
        }, clear() {
            this.searchTable = {
                userId: '',
                startTime: '',
                endTime: '',
                money1: '',
                money2: '',
                size: 12,
                page: 1
            }
        },
        checkLogin() {
            request.get('/user/check', {
                headers: {
                    "satoken": this.getCookie("satoken")
                }
            }
            ).then(res => {
                console.log(res);
                if (res.code != 200) {
                    this.$message({
                        message: '请先登录',
                        type: 'warning'
                    });
                    this.$router.push({ path: '/login' })
                }
            }).catch(err => {
                console.log(err);
            })
        },
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
        }, edit1(row) {
            console.log(row);
            this.dialogVisible = true;
            this.dtable.id = row.id;
            this.dtable.userId = row.userId;
            this.dtable.orderId = row.orderId;
            this.dtable.orderTime = row.orderTime;
            this.dtable.orderAmount = row.orderAmount;
        },
        edit2(){
            request.post("/order/update",{
                "id": this.dtable.id,
                "userId": this.dtable.userId,
                "orderId": this.dtable.orderId,
                "orderTime": this.dtable.orderTime,
                "orderAmount": this.dtable.orderAmount
            }).then(res=>{
                if(res.code == 200){
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    this.dialogVisible = false;
                    this.getTbaleData()
                }else{
                    this.$message({
                        message: "修改失败",
                        type: 'warning'
                    });
                }
            }).catch(err => {
                console.log(err);
            })
        }, delete1(row) {
            //先询问是否要删除
            this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除
                request.get("/order/delete", {
                    params: {
                        id: row.id
                    }
                }).then(res => {
                    if (res.code == 200) {
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getTbaleData()
                    } else {
                        this.$message({
                            message: "删除失败",
                            type: 'warning'
                        });
                    }
                }).catch(err => {
                    console.log(err);
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>