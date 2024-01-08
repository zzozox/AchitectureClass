<script setup>
import { useDark, useToggle } from '@vueuse/core'

useDark({
  selector: 'html',
  attribute: 'class',
  valueDark: 'dark',
  valueLight: 'light'
})

useDark({
  onChanged(dark) { useToggle(dark) }
})

</script>

<template>
  <header>
    <div class="wrapper">
      <router-view/>
    </div>
  </header>
</template>

<style>
header {
  line-height: 1.5;
}
html,body,div,span,h1,h2,h3,h4,h5,h6,ul,ol,li,p {
  margin: 0;
  padding: 0;
}
html,body,#app {
  width: 100%;
  height: 100%;
  font-family: "微软雅黑";
}
ul,ol {
  list-style: none;
}
a {
  text-decoration: none;
}
</style>
