<script setup>
</script>
<template>
  <nav class="top-navbar">
    <ul>
      <li><router-link to="/customer">首页商城</router-link></li>
    </ul>
  </nav>
</template>
<style scoped>
.top-navbar {
  background-color: #333;
  color: white;
  padding: 10px;
  text-align: center;
}

.top-navbar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.top-navbar li {
  display: inline;
  margin-right: 20px;
}

.top-navbar a {
  color: white;
  text-decoration: none;
}

.top-navbar a:hover {
  text-decoration: underline;
}
</style>