<!--
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-16 17:00:17
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 16:40:44
 * @FilePath: \store-front-end\src\components\login\login.vue
-->
<template>
  <el-dialog :model-value="showLogin"
             @close="loginClose"
             class="login"
             title=""
             width="40%">
    <img src="@/assets/logo.png"
         alt=""
         class="form-logo">
    <el-form :model="
         loginForm"
             ref="loginForm"
             :rules="rules"
             label-position="right"
             label-width="auto"
             class="form">
      <el-form-item prop="name"
                    label="用户名"
                    class="form-item">
        <el-input v-model="loginForm.name"
                  placeholder="请输入用户名"
                  name="name" />
      </el-form-item>
      <el-form-item prop="password"
                    label="密码"
                    class="form-item">
        <el-input v-model="loginForm.password"
                  type="password"
                  placeholder="请输入密码"
                  name="password"
                  show-password />
      </el-form-item>
      <el-form-item prop="signCode"
                    label="验证码"
                    class="form-item">
        <el-input v-model="loginForm.signCode"
                  placeholder="请输入验证码"
                  name="signCode" />

      </el-form-item>
      <el-form-item class="form-item">
        <div class="sign-code">
          <el-image style="width: 60px; height: 30px"
                    :src="signCodeUrl"></el-image>
          <el-link href="#"
                   type="primary"
                   size="small"
                   @click="changeSignCode"
                   :underline="false"
                   class="code-button">看不清，换一张</el-link>
        </div>
      </el-form-item>

      <div class="form-button">
        <el-button type="primary"
                   style=""
                   @click="submitForm()">登录</el-button>
      </div>

    </el-form>
  </el-dialog>
</template>

<script>
import { login, verifySignCode } from '@/service/user'
import { mapActions, mapState } from 'vuex';
import { ElMessage } from 'element-plus';
import { apiResultParse } from '@/utils/apiResult';
import axios from '@/utils/axios';
//import { ref } from 'vue';

export default {
  name: 'v-login',
  data () {
    return {
      //loginFormRef :ref(''),
      loginForm: {
        name: "",
        password: "",
        signCode: '',
      },
      signCodeUrl: "",
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '密码由8-16位字母、数字或特殊符号组成', pattern: /^[\w\s\S]{8,16}$/ }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '用户名由2-16位字母、数字或特殊符号组成', pattern: /^[\w\s\S]{2,16}$/ }
        ],
        signCode: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { len: 4, message: '长度为 4 个字符', trigger: ['blur', 'change'] },
          { type: "string", pattern: /^[\w]{4}$/ }
        ],
      }
    }
  },
  computed: {
    ...mapState([
      'showLogin',
      'user',
      'isLogin'
    ]),
  },
  methods: {
    ...mapActions([
      'updateShowLogin',
      'user/updateUser',
      'updateIsLogin'
    ]),
    loginClose: function () {
      this.updateShowLogin({ showLogin: false })
    },
    submitForm: async function () {
      if (!this.$refs.loginForm) return
      await this.$refs.loginForm.validate(async (valid) => {
        if (valid) {
          var codeRes = await verifySignCode(this.loginForm.signCode)
          if (apiResultParse(codeRes)) {
            var data = await login({ name: this.loginForm.name, password: this.loginForm.password })
            if (apiResultParse(data)) {
              this['user/updateUser'](data.data)
              this.updateIsLogin({ isLogin: true })
              this.updateShowLogin({ showLogin: false })
            }
          }
        } else {
          ElMessage.error("请按照提示完成输入")
        }
      })

    },
    changeSignCode: function () {
      this.signCodeUrl = axios.defaults.baseURL + '/signCode?n=' + Math.random()
    },

  },
  mounted: function () {
    var _this = this
    _this.signCodeUrl = axios.defaults.baseURL + '/signCode?n=' + Math.random()
    //
  },
  setup () {
    //const loginFormRef = ref(null)// eslint-disable-line no-unused-vars
  }
}
</script>

<style scoped>
.sign-code {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.form-button {
  display: flex;
  justify-content: space-around;
}

.form-logo {
  width: auto;
  height: 30%;
  aspect-ratio: 4/3;
  position: absolute;
  top: 0px;
}

.form {
  margin-top: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.form-item {
  height: 20%;
  width: 80%;
}
.code-button {
  font-size: small;
  height: 30px;
  overflow: hidden;
  display: inline;
  margin-left: 5px;
}
</style>
