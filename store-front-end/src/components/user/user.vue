<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-05-26 17:07:06
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-08 10:17:27
 * @FilePath: \store-front-end\src\components\user\user.vue
-->
<template>
  <div class="user">
    <el-dropdown trigger="click"
                 @command="handleCommand">
      <div class="userInfo">
        <el-image class="avatar"
                  :src="user.avatar"></el-image>
        <span class="uname">{{user.name}}
          <el-icon>
            <arrow-down />
          </el-icon>
        </span>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="modifyInfo">修改资料</el-dropdown-item>
          <el-dropdown-item command="changeAvatar">更换头像</el-dropdown-item>
          <el-dropdown-item command="changePassword">修改密码</el-dropdown-item>
          <el-dropdown-item divided
                            command="logout">注销登录 </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import { mapState, mapActions } from 'vuex';
import router from '@/router';
import axios from 'axios'

export default {
  name: 'v-user',
  computed: {
    ...mapState([
      'user',
    ]),
  },
  methods: {
    ...mapActions([
      'user/updateUser',
      'updateIsLogin',
    ]),
    logout: function () {
      this['user/updateUser']({ user: {} })
      this.updateIsLogin({ islogin: false })
      axios.defaults.headers["token"] = '';
      ElMessage.success("注销成功")
      localStorage.setItem("token", "")
    },
    handleCommand: function (command) {
      if (command === "logout") {
        this.logout()
      } else if (command === "modifyInfo") {
        router.push('/modifyInfo')
      } else if (command === "changeAvatar") {
        router.push('/changeAvatar')
      } else if (command === "changePassword") {
        router.push('/changePassword')
      }
    },
  },
}
</script>
<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
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
</style>