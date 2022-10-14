<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-05-26 17:07:06
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 17:01:03
 * @FilePath: \store-front-end\src\components\topMenu\topMenu.vue
-->
<template>
  <el-menu :default-active="'goods'"
           class="el-menu-demo"
           mode="horizontal"
           background-color="#00000000"
           text-color="#000"
           active-text-color="#ffd04b"
           @select="handleSelect">
    <el-menu-item index="goods">主页</el-menu-item>
    <el-menu-item v-if="user.id" index="cart">购物车</el-menu-item>
  </el-menu>
</template>

<script>
import router from '@/router'
import { mapState } from 'vuex'

export default {
  name: 'v-top-menu',
  computed: {
    ...mapState([
      'user'
    ])
  },
  methods: {
    handleSelect: function (key, keyPath) {
      console.log(key, keyPath)
      if (key === 'goods') {
        router.push('goods')
      } else if (key === 'cart') {
        router.push('cart')
      }
    }
  }
}
</script>
<style scoped>
</style>