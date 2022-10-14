<template>
  <el-dialog :model-value="showRegister"
             @close="registerClose"
             title=""
             width="40%">
    <img src="@/assets/logo.png"
         alt=""
         class="form-logo">
    <el-form :model="registerForm"
             ref="registerForm"
             :rules="rules"
             label-position="right"
             label-width="auto"
             class="form">

      <el-form-item prop="name"
                    label="用户名"
                    class="form-item">
        <el-input v-model="registerForm.name"
                  placeholder="请输入用户名"
                  name="name" />
      </el-form-item>
      <el-form-item prop="password"
                    label="密码"
                    class="form-item">
        <el-input v-model="registerForm.password"
                  type="password"
                  placeholder="请输入密码"
                  name="password"
                  show-password />
      </el-form-item>
      <el-form-item prop="repassword"
                    label="密码"
                    class="form-item">
        <el-input v-model="registerForm.repassword"
                  type="password"
                  placeholder="请再次输入密码"
                  name="password"
                  show-password />
      </el-form-item>
      <el-form-item prop="signCode"
                    label="验证码"
                    class="form-item">
        <el-input v-model="registerForm.signCode"
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
                   :underline="false"
                   class="code-button"
                   @click="changeSignCode">看不清，换一张</el-link>
        </div>
      </el-form-item>

      <div class="form-button">
        <el-button type="primary"
                   @click="submitForm"
                   plain>注册</el-button>
      </div>

    </el-form>
  </el-dialog>
</template>

<script>
import { register, verifySignCode } from '@/service/user'
import { mapActions, mapState } from 'vuex';
import { ElMessage } from 'element-plus';
import axios from 'axios';

export default {
  name: 'v-register',
  data () {
    return {
      registerForm: {
        name: "",
        password: "",
        repassword: '',
        signCode: '',
      },
      signCodeUrl: "",
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '密码由8-16位字母、数字或特殊符号组成', pattern: /^[\w\s\S]{8,16}$/ }
        ],
        repassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '密码由8-16位字母、数字或特殊符号组成', pattern: /^[\w\s\S]{8,16}$/ }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '用户名由2-16位字母、汉字、数字或特殊符号组成', pattern: /^[\w\s\S]{2,16}$/ }
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
      'showRegister',
    ]),
  },
  methods: {
    ...mapActions([
      'updateShowRegister',
    ]),
    registerClose: function () {
      this.updateShowRegister({ showRegister: false })
    },
    submitForm: async function () {
      if (!this.$refs.registerForm) return
      if (this.registerForm.password != this.registerForm.repassword) {
        ElMessage.error("两次密码输入不一致")
        return
      }
      await this.$refs.registerForm.validate(async (valid) => {
        if (valid) {
          var codeRes = await verifySignCode(this.registerForm.signCode)
          if (codeRes.code === 200) {
            var data = await register({
              name: this.registerForm.name,
              password: this.registerForm.password
            })
            if (data.code === 200) {
              this.updateShowRegister({ showRegister: false })
              ElMessage.success(data.message)
            } else {
              ElMessage.error(data.message)
            }
          } else {
            ElMessage.error(codeRes.message)
          }
        } else {
          ElMessage.error("请按照提示完成输入")
        }
      })

    },
    changeSignCode: function () {
      this.signCodeUrl = axios.defaults.baseURL + '/signCode?n=' + Math.random()
      console.log(this.signCodeUrl)
    },

  },
  mounted: function () {
    var _this = this
    _this.signCodeUrl = axios.defaults.baseURL + '/signCode?'
    //
  },

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