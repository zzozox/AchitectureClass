<script setup>
import Left from "@/views/business/Left.vue";
import axios from "axios";
import {ref,onMounted} from "vue";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
const route=useRoute();
const shopId=route.query.shopId;
const shopItemArr=ref([])
const getShopItems=()=>{
  axios.post(`/shop/listShopItems/${shopId}`,{shopId:shopId}).then(response=>{
    shopItemArr.value=response.data;
  })
}
const onsaleShopItem=(item)=>{
  axios.post(`/business/onsaleShopItem/${item.id}`,{shopItemId:item.id})
}
const newShopItem=()=>{
  router.push({path:'/newShopItem',query:{shopId:shopId}})
}
const toOrders=()=>{
  router.push({path:'/showOrders',query:{shopId:shopId}})
}
onMounted(()=>{
  getShopItems()
})
</script>
<template>
  <div class="app-container">
    <Left class="sidebar"></Left>
    <div class="content-area">
      <div class="top-buttons">
        <el-button @click="newShopItem">新增商品</el-button>
        <el-button @click="toOrders">查看订单</el-button>
      </div>
      <ul class="shop-list">
        <li v-for="(item, index) in shopItemArr" :key="index">
          <div class="item-content">
<!--            <img :src="item.shopImg" alt="">-->
<!--            <span>{{ item.shopName }}</span>-->
            <span>商品{{item.id}}</span>
          </div>
          <el-button v-if="item.onsale">已上架</el-button>
          <el-button v-if="!item.onsale" @click="onsaleShopItem(item)">上架</el-button>
        </li>
      </ul>
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

/* 右侧内容区域顶部按钮样式 */
.top-buttons {
  display: flex;
  justify-content: space-between; /* 按钮左右排列 */
  padding: 10px 20px; /* 顶部按钮间距 */
}

/* 门店列表样式 */
.shop-list {
  flex-grow: 1; /* 占据剩余空间 */
  background-color: #fdfdfd; /* 门店列表背景色 */
  padding-top: 0; /* 移除顶部内边距 */
  /* 更多样式... */
}

/* 列表项样式 */
.shop-list li {
  display: flex; /* 使用flex布局对齐图像和文本 */
  justify-content: space-between; /* 内容两侧对齐 */
  align-items: center; /* 垂直居中 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  padding: 10px 20px; /* 列表项内边距 */
  /* 更多样式... */
}

/* 图片和文本容器 */
.shop-list li .item-content {
  display: flex;
  align-items: center;
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

/* 悬浮效果 */
.shop-list li:hover {
  background-color: #e0e0e0; /* 将悬浮时的背景色改为浅灰色 */
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
/* 右侧内容区域样式 */
.content-area {
  flex-grow: 1; /* 占据剩余空间 */
  display: flex;
  flex-direction: column; /* 垂直排列子元素 */
  background-color: #fdfdfd; /* 右侧区域背景色 */
  /* 更多样式... */
}
/* 右侧区域顶部按钮样式 */
.top-buttons {
  display: flex;
  justify-content: space-between; /* 按钮左右排列 */
  padding: 10px 20px; /* 顶部按钮间距 */
}

/* 门店列表样式 */
.shop-list {
  width: 100%; /* 确保列表宽度填满容器 */
  overflow-y: auto; /* 如果列表过长，允许滚动 */
  /* 更多样式... */
}

/* 列表项样式 */
.shop-list li {
  display: flex; /* 使用flex布局对齐图像和文本 */
  justify-content: space-between; /* 内容两侧对齐 */
  align-items: center;
  padding: 10px 20px; /* 列表项内边距 */
  /* 更多样式... */
}

</style>