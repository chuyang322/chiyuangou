<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-05 21:12:40
 * @FilePath: \store-front-end\src\views\ModifyInfo.vue
-->
<template>
  <div class="container">
    <div>
      <a href="#"
         @click="backHome">返回主页</a>
    </div>
    <div class="form-container">
      <el-form :model="userForm"
               ref="userForm"
               :rules="rules"
               label-position="right"
               label-width="auto">
        <el-form-item prop="name"
                      label="用户名">
          <el-input v-model="user.name"
                    disabled
                    name="name" />
        </el-form-item>
        <el-form-item prop="email"
                      label="邮箱">
          <el-input v-model="userForm.email"
                    name="email" />
        </el-form-item>
        <el-form-item prop="phone"
                      label="手机">
          <el-input v-model="userForm.phone"
                    name="phone"
                    type="tel" />
        </el-form-item>
        <el-form-item prop="sex"
                      label="性别">
          <el-radio-group v-model="userForm.sex">
            <el-radio :label="true">男</el-radio>
            <el-radio :label="false">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <div class="form-button">
          <el-button type="primary"
                     style=""
                     @click="submitForm()">提交</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import router from '@/router'
import { mapState, mapActions } from 'vuex'
import { modifyUserInfo } from '@/service/user'
import { apiResultParse } from '@/utils/apiResult'
export default {
  name: "ModifyInfo",
  data () {
    return {
      userForm: {

      }
    }
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
  mounted () {
    this.userForm = this.user
  },
  methods: {
    ...mapActions([
      'user/updateUser',
      'updateIsLogin',
    ]),
    backHome: function () {
      router.push("/")
    },
    submitForm: async function () {
      this.userForm.id = this.user.id
      var data = await modifyUserInfo(this.userForm)
      if (apiResultParse(data)) {
        var user = {
          id: this.user.id,
          name: this.user.name,
          createTime: this.createTime,
          email: this.userForm.email,
          phone: this.userForm.phone,
          sex: this.userForm.sex
        }
        this['user/updateUser']({ user })
      }
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px;
  height: 500px;
  width: 100%;
}
.form-container {
  margin-top: 30px;
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
a {
  text-decoration: none;
  color: #409eff;
  font-size: small;
}
.form-button {
  text-align: center;
}
</style>