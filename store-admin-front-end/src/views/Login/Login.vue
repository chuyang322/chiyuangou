<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-02 19:02:53
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 10:16:20
 * @FilePath: \store-admin-front-end\src\views\Login\Login.vue
-->
<template>
  <div class="container">
    <el-form :model="loginForm"
             ref="loginForm"
             :rules="rules"
             label-position="right"
             label-width="auto">
      <el-form-item prop="name"
                    label="用户名">
        <el-input v-model="loginForm.name"
                  placeholder="Please input username"
                  name="name" />
      </el-form-item>
      <el-form-item prop="password"
                    label="密码">
        <el-input v-model="loginForm.password"
                  type="password"
                  placeholder="Please input password"
                  name="password"
                  show-password />
      </el-form-item>
      <el-form-item prop="signCode"
                    label="验证码">
        <el-input v-model="loginForm.signCode"
                  placeholder="Please input code"
                  name="signCode" />

      </el-form-item>
      <el-form-item>
        <div class="sign-code">
          <el-image style="width: 60px; height: 30px"
                    :src="signCodeUrl"></el-image>
          <el-button style="color:blue"
                     type=""
                     size="small"
                     @click="changeSignCode"
                     plain>看不清，换一张</el-button>
        </div>
      </el-form-item>

      <div class="form-button">
        <el-button type="primary"
                   style=""
                   @click="submitForm()">登录</el-button>
      </div>

    </el-form>
  </div>
</template>

<script>
export default {
  name: 'v-Login',
}
</script>

<style scoped>
.container {
  padding: 20px;
  height: 500px;
  width: 100%;
}
</style>
