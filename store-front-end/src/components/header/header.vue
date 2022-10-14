<!--
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-16 13:28:50
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 23:27:12
 * @FilePath: \store-front-end\src\components\header\header.vue
-->
<template>
  <div class="header">
    <v-login></v-login>
    <v-register></v-register>
    <div class="top">

      <div v-if="isLogin">
        <el-affix :offset="120">
          <v-user></v-user>
        </el-affix>
      </div>
      <div v-else>
        <el-affix :offset="120">
          <div class="signButton">
            <el-button @click="loginShow"
                       size="small"
                       text
                       bg>登录</el-button>
            <el-button @click="registerShow"
                       size="small"
                       text
                       bg>注册</el-button>
          </div>
        </el-affix>
      </div>
    </div>
    <div class="middle">
      <img src="@/assets/logo.png"
           alt=""
           class="logo">
      <div class="search">
        <el-input v-model="keyword"
                  placeholder="Search"
                  @keyup.enter="search">
          <template #prefix>
            <el-icon class="el-input__icon">
              <search />
            </el-icon>
          </template>
        </el-input>
      </div>
    </div>
    <div class="bottom">
      <v-top-menu></v-top-menu>
    </div>
  </div>
</template>

<script>
import loginVue from '@/components/login/login.vue'
import registerVue from '@/components/register/register.vue'
import { mapState, mapActions } from 'vuex';
import userVue from '@/components/user/user.vue';
import topMenu from '../topMenu/topMenu.vue';
import { apiResultParse } from '@/utils/apiResult';
import { getGoodsByKeyword } from '@/service/goods';

export default {
  name: 'v-header',
  data () {
    return {
      keyword: ''
    }
  },
  computed: {
    ...mapState([
      'isLogin',
      'goodsList'
    ]),
  },
  methods: {
    ...mapActions([
      'updateShowLogin',
      'updateShowRegister',
      'goodsList/updateList',
      'goodsList/updatePages',
      'goodsList/updateTotal'
    ]),
    loginShow: function () {
      this.updateShowLogin({ showLogin: true })
    },
    registerShow: function () {
      this.updateShowRegister({ showRegister: true })
    },
    search: async function () {
      var data = await getGoodsByKeyword(1, this.goodsList.pageSize, this.keyword)
      this['goodsList/updateKeyword']({ keyword: this.keyword })
      console.log(data)
      if (apiResultParse(data)) {
        this['goodsList/updateList']({ list: data.data.records })
        this['goodsList/updatePages']({ pages: data.data.pages })
        this['goodsList/updateTotal']({ total: data.data.total })
      }
    }
  },
  components: {
    "v-login": loginVue,
    "v-register": registerVue,
    "v-user": userVue,
    "v-top-menu": topMenu
  },
  mounted () {

  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
  border-radius: 4px;
  min-height: 100px;
  max-height: 200px;
  /* background: url('@/assets/header-bg.jpg'); */
  background-color: #c8c8c8;
}
.top {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
  height: 20%;
}
.top div {
  margin: 10px 10px 0 0;
}
.middle {
  height: 60%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.bottom {
  height: 20%;
}

.userInfo {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.avatar {
  height: 32px;
  width: 32px;
  border-radius: 50%;
  margin: 10px;
}
.signButton {
  display: flex;
  flex-direction: row;
  z-index: 3000;
}
.search {
  width: 40%;
  margin: 10px;
}
.logo {
  margin: 10px;
  left: 20px;
  width: 20%;
  height: auto;
  aspect-ratio: 4/3;
  position: absolute;
  top: 0px;
}
::v-deep .el-input__inner {
  background-color: #ffffff00;
  border-color: rgb(129, 107, 107);
}
</style>
