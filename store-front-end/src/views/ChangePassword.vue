<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-08 10:24:08
 * @FilePath: \store-front-end\src\views\ChangePassword.vue
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
        <el-form-item prop="password"
                      label="密码">
          <el-input v-model="userForm.password"
                    name="email"
                    type="password"
                    show-password />
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
import { apiResultParse } from '@/utils/apiResult'
import { ElMessage } from 'element-plus'
import { changePassword } from '@/service/user'
export default {
  name: "ModifyInfo",
  data () {
    return {
      userForm: {
        password: ""
      },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: ['blur', 'change'] },
          { type: "string", message: '密码由8-16位字母、数字或特殊符号组成', pattern: /^[\w\s\S]{8,16}$/ }
        ],
      }
    }
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
  mounted () {
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
      if (!this.$refs.userForm) return
      await this.$refs.userForm.validate(async (valid) => {
        if (valid) {
          var data = await changePassword({ id: this.user.id, password: this.userForm.password })
          apiResultParse(data)
        } else {
          ElMessage.error("请按照提示完成输入")
        }
      })
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