<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-05-18 18:40:46
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-05 19:15:36
 * @FilePath: \store-front-end\src\views\home.vue
-->
<template>
  <div id="home">
    <v-header></v-header>
    <router-view style="height:75%"></router-view>
  </div>
</template>

<script>
import header from '@/components/header/header'
import { mapActions, mapState } from 'vuex';
import axios from 'axios';
import router from '@/router';

export default {
  name: 'v-home',
  computed: {
    ...mapState([
      'articleList'
    ])
  },
  components: {
    'v-header': header,

  },
  methods: {
    ...mapActions([
      'user/updateUser',
      'updateIsLogin',
      'updateToken',
      'articleList/updateArticles',
      'articleList/updatePages',
      'articleList/updateTotal',
    ])
  },
  async mounted () {
    try {
      axios.defaults.headers["token"] = localStorage.getItem("token");
      const user = JSON.parse(localStorage.getItem("user"))
      this['user/updateUser']({ user })
      if (user.name != null) {
        this.updateIsLogin({ isLogin: true })
      }
    } catch (error) {
      console.log(error)
    }
    router.push('goods')
  }
}
</script>

<style scoped>
#home {
  height: 100%;
  width: 100%;
}
</style>
