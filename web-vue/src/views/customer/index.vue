<script setup>
import axios from "axios";
import {ref ,onMounted} from "vue";
import router from "@/router/index.js";
import Header from "@/views/customer/Header.vue";

const businessId=1
const shopArr=ref([])
const getShops=()=>{
  axios.post(`/business/listShops/${businessId}`,{businessId:businessId}).then(response=>{
    shopArr.value=response.data;
  })
}
const toShop=(item)=>{
  router.push({path:'/chooseItem',query:{shopId:item.id}})
}
onMounted(()=>{
  getShops()
})
</script>
<template>
  <Header></Header>
  <ul>
    <li v-for="(item, index) in shopArr" :key="index" @click="toShop(item)">
      <img :src="item.shopImg" alt="">
      <span>{{ item.shopName }}</span>
    </li>
  </ul>
</template>


<style scoped>
/* 列表样式 */
ul {
  list-style-type: none; /* 移除列表符号 */
  padding: 0; /* 移除默认内边距 */
  margin: 0; /* 移除默认外边距 */
}

/* 列表项样式 */
li {
  display: flex; /* 使用flex布局 */
  align-items: center; /* 垂直居中对齐子元素 */
  padding: 10px; /* 内边距 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  background-color: #f9f9f9; /* 背景颜色 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* 阴影效果 */
  transition: transform 0.2s ease, box-shadow 0.2s ease; /* 过渡效果 */
  cursor: pointer; /* 鼠标样式 */
}

li:hover {
  transform: translateY(-5px); /* 悬浮时上移 */
  box-shadow: 0 4px 10px rgba(0,0,0,0.3); /* 悬浮时阴影效果 */
}

/* 图片样式 */
li img {
  width: 50px; /* 图片宽度 */
  height: 50px; /* 图片高度 */
  border-radius: 50%; /* 圆形图片 */
  object-fit: cover; /* 图片填充方式 */
  margin-right: 15px; /* 与文本的间距 */
}

/* 文本样式 */
li span {
  font-size: 16px; /* 字体大小 */
  color: #333; /* 字体颜色 */
}
</style>
