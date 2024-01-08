<script setup>
import Left from "@/views/business/Left.vue";
import axios from "axios";
import {ref,onMounted} from "vue";
import {useRoute} from "vue-router";
const route=useRoute();

const shopId=route.query.shopId;
const businessId=sessionStorage.getItem("businessId")
const itemArr=ref([])
const getItems=()=>{
  axios.post(`/business/listItems/${businessId}`,{businessId:businessId}).then(response=>{
    itemArr.value=response.data
  })
}
const price=ref()
const onsaleItem=(item)=>{
  const params=new URLSearchParams()
  params.append('itemId',item.id)
  params.append('shopId',shopId)
  params.append('price',price.value)
  axios.post(`/shop/onsaleItem`,params)
}
onMounted(()=>{
  getItems()
})
</script>

<!--<template>-->
<!--  <Left></Left>-->
<!--  <ul>-->
<!--    <li v-for="(item,index) in itemArr" :key="index">-->
<!--      {{item.itemName}}-->
<!--      <el-input v-model="price" placeholder="价格"></el-input>-->
<!--      <el-button @click="onsaleItem(item)">新增</el-button>-->
<!--    </li>-->
<!--  </ul>-->
<!--</template>-->
<template>
  <div class="app-container">
    <Left class="sidebar"></Left>
    <div class="content-area">
      <ul class="item-list">
        <li v-for="(item, index) in itemArr" :key="index">
          <span>{{ item.itemName }}</span>
          <div class="item-actions">
            <el-input v-model="price" placeholder="价格"></el-input>
            <el-button @click="onsaleItem(item)">新增</el-button>
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
  background-color: #fdfdfd; /* 右侧区域背景色 */
  padding: 20px; /* 内边距 */
}

.item-list{
  background-color: #fdfdfd; /* 门店列表背景色 */
}
/* 商品列表项样式 */
.item-list li {
  display: flex; /* 使用flex布局对齐文本、输入框和按钮 */
  justify-content: space-between; /* 内容两侧对齐 */
  align-items: center; /* 垂直居中 */
  padding: 10px 20px; /* 列表项内边距 */
  margin-bottom: 10px; /* 列表项之间的间距 */
  border-bottom: 1px solid #ccc; /* 列表项之间的分隔线 */
  background-color: #fdfdfd; /* 设置背景颜色与右侧内容区域相同 */
}

/* 商品名称样式 */
.item-list li span {
  color: #000; /* 字体颜色改为黑色 */
  margin-right: 10px; /* 与操作按钮的间距 */
}

/* 操作按钮容器 */
.item-list .item-actions {
  display: flex; /* 使用flex布局对齐输入框和按钮 */
  align-items: center;
}

/* 输入框样式 */
.item-list .el-input {
  width: 150px; /* 输入框宽度设置为150px */
  margin-right: 10px; /* 与按钮的间距 */
}

/* 悬浮效果 */
.item-list li:hover {
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