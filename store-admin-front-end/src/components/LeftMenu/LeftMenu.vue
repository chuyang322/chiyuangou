<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-02 18:00:10
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 12:36:49
 * @FilePath: \store-admin-front-end\src\components\LeftMenu\LeftMenu.vue
-->

<template>
  <el-menu active-text-color="#ffd04b"
           background-color="#545c64"
           class="el-menu-vertical-demo"
           default-active="hello"
           text-color="#fff"
           @select="handleSelect">
    <el-menu-item index="hello">
      <span>主页</span>
    </el-menu-item>
    <el-menu-item index="userManage">
      <span>用户管理</span>
    </el-menu-item>
    <el-menu-item index="goodsManage">
      <span>商品管理</span>
    </el-menu-item>
    <el-menu-item index="addGoods">
      <span>新增商品</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
import router from '@/router'

export default {
  name: 'v-top-menu',
  methods: {
    handleSelect: function (key, keyPath) {
      console.log(key, keyPath)
      if (key === 'hello') {
        router.push('hello')
      } else if (key === 'userManage') {
        router.push('userManage')
      } else if (key === 'goodsManage') {
        router.push("goodsManage")
      }else if (key === 'addGoods') {
        router.push("addGoods")
      }
    }
  }
}
</script>
<style scoped>
</style>