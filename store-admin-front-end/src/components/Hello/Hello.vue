<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-05-18 18:03:33
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 17:17:58
 * @FilePath: \store-admin-front-end\src\components\Hello\Hello.vue
-->
<template>
  <div class="container">
    <span class="title">欢迎使用次缘购后台管理系统</span>
    <img src="@/assets/logo.png"
         alt=""
         class="logo">

  </div>
</template>

<script>
export default {
  name: 'v-hello',
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 30px;
}
.logo {
  width: 50%;
  height: auto;
  aspect-ratio: 4/3;
  position: relative;
}
.title {
  color: black;
  font-size: larger;
  font-weight: bold;
  font-family: 'Courier New', Courier, monospace;
  text-align: center;
}
</style>
