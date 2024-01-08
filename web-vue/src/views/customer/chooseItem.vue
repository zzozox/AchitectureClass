<script setup>
import axios from "axios";
import router from "@/router/index.js";
import {ref,onMounted} from "vue";
import {useRoute} from "vue-router";
import Header from "@/views/customer/Header.vue";
const route=useRoute();
const shopId=route.query.shopId
const customerId=sessionStorage.getItem("customerId")
const shopItemArr=ref([])
const getShopItems = () => {
  axios.post(`/shop/listShopItems/${shopId}`, {shopId: shopId}).then(response => {
    shopItemArr.value = response.data;
    // quantity.value = new Array(shopItemArr.value.length).fill(0); // 初始化 quantity 数组
  })
}
const quantity=ref()
const placeOrder=(item)=>{
  const params=new URLSearchParams()
  params.append('shopItemId',item.id)
  params.append('quantity',quantity.value)
  params.append('customerId',customerId)
  axios.post(`/customer/placeOrder`,params).then(response=>{
    router.push({path:'/payOrder',query:{orderId:response.data.id}})
  })
}
onMounted(()=>{
  getShopItems()
})
</script>
<template>
  <Header></Header>
  <ul>
    <li v-for="(item, index) in shopItemArr" :key="index" v-show="item.onsale">
      <span>商品{{item.id}}</span>
      <div class="item-actions">
        <el-input v-model="quantity" placeholder="购买数量"></el-input>
        <el-button @click="placeOrder(item)">购买</el-button>
      </div>
    </li>
  </ul>
</template>


<style scoped>
/* 列表样式 */
ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

/* 列表项样式 */
li {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 确保子元素分布在两端 */
  padding: 10px;
  margin-bottom: 10px;
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

/* 商品ID样式 */
li span {
  font-size: 16px;
  margin-right: 20px;
}

/* 操作区域容器样式 */
.item-actions {
  display: flex;
  align-items: center;
  margin-left: auto; /* 将操作区域推到右侧 */
}

/* 输入框样式 */
.el-input {
  width: 150px;
  margin-right: 10px;
}

/* 按钮样式 */
.el-button {
  background-color: #409eff;
  color: white;
  border: none;
  padding: 8px 15px;
  border-radius: 4px;
}

.el-button:hover {
  background-color: #66b1ff;
}
</style>

