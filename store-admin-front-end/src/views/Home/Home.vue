<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-02 18:00:10
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 11:16:00
 * @FilePath: \store-admin-front-end\src\views\Home\Home.vue
-->

<template>
  <div class="home">
    <v-left-menu></v-left-menu>
    <router-view />
  </div>
</template>

<script>
import router from '@/router'
import LeftMenu from '@/components/LeftMenu/LeftMenu.vue'

export default {
  name: 'v-home',
  components: {
    'v-left-menu': LeftMenu
  },
  mounted () {
    router.push("hello")
  }
}
</script>
<style scoped>
.home {
  display: flex;
  flex-direction: row;
}
</style>