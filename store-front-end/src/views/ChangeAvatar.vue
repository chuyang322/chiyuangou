<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-05 23:27:05
 * @FilePath: \store-front-end\src\views\ChangeAvatar.vue
-->
<template>
  <div class="container">
    <div>
      <a href="#"
         @click="backHome">返回主页</a>
    </div>
    <div class="form-container">
      <div class="avatar-container">
        <el-upload class="avatar-upload"
                   :show-file-list="false"
                   action=""
                   :on-change="handleAvatarChange"
                   :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl"
               :src="imageUrl"
               class="avatar" />
          <el-icon v-else
                   class="avatar-upload-icon">
            <plus class="plus" />
          </el-icon>
        </el-upload>
      </div>
      <div class="form-button">
        <el-button type="primary"
                   style=""
                   @click="submitForm()">提交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { mapActions, mapState } from 'vuex'
import router from '@/router'
import { changeAvatar } from '@/service/user'
import { apiResultParse } from '@/utils/apiResult'
export default {
  name: "ChangeAvatar",
  data () {
    return {
      imageUrl: "",
      httpUrl: "",
      file: undefined
    }
  },
  computed: {
    ...mapState([
      'user'
    ])
  },
  methods: {
    ...mapActions([
      'user/updateAvatar'
    ]),
    beforeAvatarUpload: function (rawFile) {
      if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
        ElMessage.error('图片必须为JPG')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('图片大小不超过2MB')
        return false
      }
      return true
    },
    handleAvatarChange: function (file) {
      this.file = file
      this.imageUrl = URL.createObjectURL(this.file.raw)
    },
    submitForm: async function () {
      var fd = new FormData()
      fd.append('file', this.file.raw)
      fd.append('id', this.user.id)
      var data = await changeAvatar(fd)
      if (apiResultParse(data)) {
        this['user/updateAvatar']({ avatar: data.data})
      }
    },
    backHome: function () {
      router.push("/")
    },
  },
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
.avatar-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: baseline;
  margin-bottom: 20px;
}
.avatar-upload {
  height: 64px;
  width: 64px;
}

.avatar {
  height: 64px;
  width: 64px;
}
.avatar-upload {
  border: 1px dashed black;
  border-radius: 6px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.avatar-upload-icon {
  color: black;
  font-size: 15px;
}
.form-button {
  text-align: center;
}
</style>