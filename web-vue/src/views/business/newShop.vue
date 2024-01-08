<script setup>
import Left from "@/views/business/Left.vue";
import axios from "axios";
import router from "@/router/index.js";
import {ref,onMounted} from "vue";
const businessId=sessionStorage.getItem("businessId")
const newShop=ref({
  shopName:'',
  shopImg:'',
})
const createShop=()=>{
  const params=new URLSearchParams()
  params.append('shopName',newShop.value.shopName)
  params.append('shopImg',newShop.value.shopImg)
  params.append('businessId',businessId)
  axios.post(`/business/createShop`,params).then(()=>{
    router.push('/business')
  })
}
</script>

<template>
  <div class="app-container">
    <Left class="sidebar"></Left>
    <div class="right-content">
      <el-input v-model="newShop.shopName" placeholder="门店名"></el-input>
      <el-input v-model="newShop.shopImg" placeholder="门店图片"></el-input>
      <el-button @click="createShop">新建</el-button>
    </div>
  </div>
</template>


<style scoped>
/* 整体布局样式 */
.app-container {
  display: flex; /* 使用flex布局 */
  min-height: 100vh; /* 整个视窗的最小高度 */
}

/* 侧边栏样式 */
.sidebar {
  width: 250px; /* 侧边栏宽度 */
  background-color: #2c3e50; /* 侧边栏背景色 */
  color: #ffffff;
  /* 更多样式... */
}

/* 左侧侧边栏的列表项悬浮效果 */
.sidebar li:hover {
  background-color: #101c2b; /* 你想要的悬浮颜色 */
}

/* 右侧内容区域样式 */
.right-content {
  flex-grow: 1; /* 占据剩余空间 */
  background-color: #fdfdfd; /* 背景色 */
  padding: 20px; /* 内边距 */
  display: flex;
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 子元素居中对齐 */
}

/* 输入框样式 */
.el-input {
  width: 300px; /* 设置宽度 */
  margin-bottom: 15px; /* 底部间距 */
  /* 其他样式... */
}

.el-input:focus {
  border-color: #409eff; /* 聚焦时的边框颜色 */
  box-shadow: 0 0 8px rgba(64,158,255,0.2); /* 聚焦时的阴影 */
}

/* 按钮样式 */
.el-button {
  width: 150px; /* 按钮宽度 */
  padding: 10px 20px; /* 按钮内边距 */
  border-radius: 4px; /* 圆角 */
  background-color: #409eff; /* 按钮背景颜色 */
  color: white; /* 文本颜色 */
  border: none; /* 移除边框 */
  cursor: pointer; /* 鼠标样式 */
  transition: background-color 0.3s; /* 背景色过渡效果 */
}

.el-button:hover {
  background-color: #66b1ff; /* 鼠标悬浮时的背景色 */
}
</style>