<script setup>
import {User, Lock} from '@element-plus/icons-vue'
import router from "@/router";
import {reactive, ref} from "vue";
import axios from "axios";
const formRef = ref()
const form = reactive({
  username: '',
  password: '',
  remember: false
})
const rules = {
  username: [
    { required: true, message: '请输入用户名' }
  ],
  password: [
    { required: true, message: '请输入密码'}
  ]
}
function userLogin() {
  const parambusiness=new URLSearchParams();
  parambusiness.append('businessName',form.username)
  parambusiness.append('password',form.password)
  axios.post(`/business/login`,parambusiness).then(response=>{
    if(response.data.type===1){
      sessionStorage.setItem("businessId",response.data.id)
      router.push('/business')
    }else {
      const paramCustomer =new URLSearchParams()
      paramCustomer.append('customerName',form.username)
      paramCustomer.append('password',form.password)
      axios.post(`/customer/login`,paramCustomer).then(response=>{
        sessionStorage.setItem("customerId",response.data.id)
        router.push('/customer')
      })
    }
  })
}


</script>
<template>
  <div class="login-container">
    <header class="header">
      <p>用户登录</p>
    </header>
    <div class="form-container">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" maxlength="10" type="text" placeholder="用户名">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" maxlength="20" placeholder="密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="login-button">
      <el-button @click="userLogin()" type="success" plain>立即登录</el-button>
    </div>
  </div>
  <Footer></Footer>
</template>
<style scoped>
/* 全局字体和颜色 */
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
  background-color: #f4f4f4;
}

/* 登录容器 */
.login-container {
  text-align: center;
  margin: 0 auto;
  max-width: 400px; /* 限制最大宽度 */
  padding: 60px 20px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* 添加阴影 */
  background: white;
  border-radius: 8px; /* 圆角 */
}

.header {
  width: 100%;
  background-color: #4A90E2; /* 更柔和的蓝色 */
  color: #fff;
  font-size: 20px; /* 优化字体大小 */
  padding: 15px 0; /* 优化填充 */
  margin-bottom: 30px;
  border-radius: 8px 8px 0 0; /* 圆角顶部 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.05); /* 更轻微的阴影 */
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; /* 更现代的字体 */
}

/* 可以考虑添加一个徽标或图标 */
.header img.logo {
  height: 30px; /* 徽标大小 */
  margin-right: 10px;
  vertical-align: middle;
}

/* 标题文字样式 */
.header p {
  display: inline-block;
  vertical-align: middle;
  margin: 0;
}


/* 表单样式 */
.el-form {
  margin: 20px 0;
}

/* 输入框样式 */
.el-input {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 20px;
}

/* 按钮样式 */
.el-button {
  background-color: #0097FF;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.el-button:hover {
  background-color: #007ACC;
}

/* 底部样式 */
footer {
  margin-top: 40px;
  color: #aaa;
  text-align: center;
}

/* 响应式设计 */
@media (max-width: 600px) {
  .header {
    font-size: 20px;
  }

  .login-container {
    margin: 20px;
    padding: 40px 10px;
  }
}

</style>
