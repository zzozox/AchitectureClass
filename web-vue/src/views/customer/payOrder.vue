<script setup>
import axios from "axios";
import router from "@/router/index.js";
import {ref,onMounted} from "vue";
import {useRoute} from "vue-router";
import Header from "@/views/customer/Header.vue";
const route=useRoute();
const orderId=route.query.orderId
const Price=ref(0)
const customerId=sessionStorage.getItem("customerId")
const lineItemArr=ref([])
const getLineItems=()=>{
  axios.post(`/order/listLineItems/${orderId}`,{orderId:orderId}).then(responde=>{
    lineItemArr.value=responde.data;
    for (const item of lineItemArr.value){
      Price.value=item.order.totalPrice
    }
    console.log(lineItemArr.value)
  })
}
const payOrder=()=>{
  axios.post(`/customer/payOrder/${orderId}`,{orderId:orderId}).then(()=>{
    router.push('/customer')
    ElMessage.success('支付成功')
  })
}
const cancelOrder=()=>{
  axios.post(`/customer/cancelOrder/${orderId}`,{orderId:orderId}).then(()=>{
    router.push('/customer')
    ElMessage.success('取消成功')
  })
}
onMounted(()=>{
  getLineItems()
})
</script>
<template>
  <Header></Header>
  <ul>
    <li v-for="item in lineItemArr" :key="item.id">
      <img :src="item.shopItem.item.itemImg" alt="">
      <span>  {{item.shopItem.item.itemName}}</span>
      <span>  数量:{{ item.quantity }}</span>
    </li>
  </ul>
  <div class="buttons-container">
    <el-button @click="payOrder">支付 {{Price}} 元</el-button>
    <el-button @click="cancelOrder">取消</el-button>
  </div>
</template>
<style scoped>
/* 列表样式 */
ul {
  list-style-type: none; /* 移除列表符号 */
  padding: 0; /* 移除默认的内边距 */
  margin: 0; /* 移除默认的外边距 */
}

/* 列表项样式 */
li {
  display: flex; /* 使用flex布局 */
  align-items: center; /* 垂直居中对齐子元素 */
  justify-content: left;
  padding: 10px; /* 内边距 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  background-color: #f9f9f9; /* 背景颜色 */
  border-radius: 8px; /* 圆角 */
  box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* 阴影效果 */
}

/* 商品ID和数量的样式 */
li span {
  font-size: 16px; /* 字体大小 */
  margin-right: 10px; /* 与其他元素的间隔 */
}
/* 第一个span样式 */
li span:first-child {
  margin-right: 20px; /* 第一个span与第二个span之间的间隔 */
}

/* 第二个span样式 */
li span:nth-child(2) {
  /* 可以添加特定样式，如果需要的话 */
}
/* 图片样式 */
img {
  width: 50px; /* 图像宽度 */
  height: 50px; /* 图像高度 */
  border-radius: 50%; /* 圆形图片 */
  margin-right: 10px; /* 图像和文本之间的间距 */
}

/* 按钮容器样式 */
.buttons-container {
  display: flex;
  flex-direction: column; /* 上下排列按钮 */
  align-items: center; /* 按钮居中 */
  margin-top: 20px; /* 与列表间隔 */
}

/* 按钮样式 */
.el-button {
  width: 200px; /* 更宽的按钮 */
  margin-bottom: 10px; /* 按钮之间的间隔 */
  background-color: #409eff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
}

.el-button:last-child {
  margin-bottom: 0; /* 最后一个按钮没有间隔 */
}

.el-button:hover {
  background-color: #66b1ff;
}
</style>
