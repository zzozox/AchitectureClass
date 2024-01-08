<script setup>
import Left from "@/views/business/Left.vue";
import axios from "axios";
import {ref ,onMounted} from "vue";
import router from "@/router/index.js";

const businessId=sessionStorage.getItem("businessId")
const shopArr=ref([])
const getShops=()=>{
  axios.post(`/business/listShops/${businessId}`,{businessId:businessId}).then(response=>{
    shopArr.value=response.data;
    console.log(shopArr.value)
  })
}
const toShop=(item)=>{
  router.push({path:'/shopItems',query:{shopId:item.id}})
}
onMounted(()=>{
  getShops()
})
</script>

<template>
  <div class="app-container">
    <Left class="sidebar"></Left>
    <ul class="shop-list">
      <li v-for="item in shopArr" :key="item.id" @click="toShop(item)">
        <img :src="item.shopImg" alt="">
        <span>{{ item.shopName }}</span>
      </li>
    </ul>
  </div>
</template>

<style>
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

/* 门店列表样式 */
.shop-list {
  flex-grow: 1; /* 占据剩余空间 */
  background-color: #fdfdfd; /* 门店列表背景色 */
  padding: 20px; /* 内边距 */
  /* 更多样式... */
}

/* 列表项样式 */
.shop-list li {
  display: flex; /* 使用flex布局对齐图像和文本 */
  align-items: center; /* 垂直居中 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  /* 更多样式... */
}

/* 悬浮效果 */
.shop-list li:hover {
  background-color: #e0e0e0; /* 将悬浮时的背景色改为浅灰色 */
}

/* 图片样式 */
.shop-list img {
  width: 50px; /* 图像宽度 */
  height: 50px; /* 图像高度 */
  border-radius: 50%; /* 圆形图片 */
  margin-right: 10px; /* 图像和文本之间的间距 */
}

/* 文本样式 */
.shop-list span {
  font-size: 16px;
  color: #000; /* 字体颜色改为黑色 */
  /* 更多样式... */
}


/* 响应式设计样式 */
@media (max-width: 768px) {
  .app-container {
    flex-direction: column; /* 小屏幕时改为垂直布局 */
  }

  .sidebar {
    width: 100%; /* 侧边栏宽度调整为100% */
  }

  .shop-list {
    padding: 10px;
  }
}
</style>
