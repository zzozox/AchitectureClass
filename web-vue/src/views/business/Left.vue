<script setup>
import router from "@/router";
import {Fold, HomeFilled, User, View} from "@element-plus/icons-vue";
</script>

<template>
  <ul>
    <li @click="router.push('/business')">
      <el-icon><Fold /></el-icon>
      <p>门店管理</p>
    </li>
    <li @click="router.push('/showItems')">
      <el-icon><User /></el-icon>
      <p>商品管理</p>
    </li>
    <li @click="router.push('/newShop')">
      <el-icon><HomeFilled /></el-icon>
      <p>新建门店</p>
    </li>
    <li @click="router.push('/newItem')">
      <el-icon><View /></el-icon>
      <p>新建商品</p>
    </li>
  </ul>
</template>
<style>
/* 左侧导航栏的基础样式 */
ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
  background-color: #333; /* 深色背景 */
  color: white;
  font-family: Arial, sans-serif;
}

/* 列表项样式 */
li {
  display: flex;
  align-items: center;
  padding: 10px 20px;
  border-bottom: 1px solid #444; /* 列表项之间的分隔线 */
  cursor: pointer;
  transition: background-color 0.3s ease;
}

/* 图标样式 */
.el-icon {
  margin-right: 10px;
  color: #ddd; /* 图标颜色 */
}

/* 文本样式 */
p {
  margin: 0;
  font-size: 16px;
}

/* 悬浮效果 */
li:hover {
  background-color: #4A90E2; /* 悬浮时的背景色 */
}

/* 活动状态或点击效果 */
li.active, li:active {
  background-color: #5DADE2; /* 点击时的背景色 */
  color: white;
}

/* 响应式设计 */
@media (max-width: 600px) {
  li {
    padding: 10px;
    justify-content: center;
  }

  .el-icon {
    margin: 0;
  }

  p {
    display: none; /* 在小屏幕上隐藏文本 */
  }
}

</style>
