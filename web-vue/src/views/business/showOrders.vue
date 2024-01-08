<script setup>
import axios from "axios";
import {ref,onMounted} from "vue";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
import Left from "@/views/business/Left.vue";
const route=useRoute();
const shopId=route.query.shopId;

const orderArr=ref([])
const getOrders=()=>{
  axios.post(`/shop/listOrders/${shopId}`,{shopId:shopId}).then(response=>{
    orderArr.value=response.data
  })
}
const toShopItems=()=>{
  router.push({path:'/shopItems',query:{shopId:shopId}})
}
const confirmOrder=(item)=>{
  axios.post(`/business/confirmOrder/${item.id}`,{orderId:item.id}).then(()=>{getOrders()})
}
const cancelOrder=(item)=>{
  axios.post(`/business/cancelOrder/${item.id}`,{orderId:item.id}).then(()=>{getOrders()})
}
onMounted(()=>{
  getOrders()
})
</script>

<template>
  <div class="app-container">
    <Left class="sidebar"></Left>
    <div class="content-area">
      <el-button @click="toShopItems">查看商品</el-button>
      <ul class="order-list">
        <li v-for="item in orderArr" :key="item">
          <div class="order-info">
            <!-- 订单信息 -->
            <span>订单{{ item.id }}</span>
            <span>总价{{ item.totalPrice }}</span>
          </div>
          <div>
            <!-- 根据订单状态显示的按钮 -->
            <el-button v-if="item.confirm === 1">已确认</el-button>
            <el-button v-if="item.confirm === -1">已退款</el-button>
            <el-button v-if="item.payState && item.confirm === 0" @click="confirmOrder(item)">确认</el-button>
            <el-button v-if="item.payState && item.confirm === 0" @click="cancelOrder(item)">退款</el-button>
            <el-button v-if="!item.payState">未支付</el-button>
          </div>
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

/* 右侧内容区域样式 */
.content-area {
  flex-grow: 1; /* 占据剩余空间 */
  display: flex;
  flex-direction: column; /* 垂直排列子元素 */
  background-color: #fdfdfd; /* 右侧区域背景色 */
  overflow-y: auto; /* 如果内容过长，允许滚动 */
  padding: 20px; /* 适当的内边距 */
  /* 更多样式... */
}

/* 查看商品按钮样式 */
.content-area > .el-button {
  align-self: flex-start; /* 按钮靠左对齐 */
  margin-bottom: 10px; /* 与订单列表间隔 */
}

/* 订单列表样式 */
ul {
  width: 100%; /* 确保列表宽度填满容器 */
  list-style-type: none; /* 移除列表项目符号 */
  padding: 0; /* 移除默认的内边距 */
  margin: 0; /* 移除默认的外边距 */
}

/* 订单列表样式 */
.order-list {
  width: 100%; /* 确保列表宽度填满容器 */
  list-style-type: none; /* 移除列表项目符号 */
  padding: 0; /* 移除默认的内边距 */
  margin: 0; /* 移除默认的外边距 */
  background-color: #fdfdfd; /* 门店列表背景色 */
}
/* 订单列表项样式 */
.order-list li {
  display: flex; /* 使用flex布局对齐内容和按钮 */
  justify-content: space-between; /* 内容两侧对齐 */
  align-items: center; /* 垂直居中对齐 */
  padding: 10px 20px; /* 列表项内边距 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  border-bottom: 1px solid #ccc; /* 列表项之间的分隔线 */
  background-color: #fdfdfd; /* 设置列表项背景色为白色或与右侧区域底色相同 */
}
/* 悬浮效果 */
.order-list li:hover {
  background-color: #e0e0e0; /* 悬浮时的背景色 */
}
/* 订单信息样式 */
.order-info {
  color: #000; /* 订单信息文字颜色 */
}

/* 按钮样式 */
li .el-button {
  margin-left: 10px; /* 按钮之间的间隔 */
}

/* 悬浮效果 */
li:hover {
  background-color: #e0e0e0; /* 悬浮时的背景色 */
}

/* 响应式设计样式 */
@media (max-width: 768px) {
  .app-container {
    flex-direction: column; /* 小屏幕时改为垂直布局 */
  }

  .sidebar {
    width: 100%; /* 侧边栏宽度调整为100% */
  }

  .content-area {
    padding: 10px; /* 小屏幕时减少内边距 */
  }
}

</style>