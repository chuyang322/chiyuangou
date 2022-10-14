<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 16:15:50
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-05 18:32:27
 * @FilePath: \store-front-end\src\components\AvatarUPload\AvatarUPload.vue
-->
<template>
  <div class="avatar-container">
    <el-upload class="avatar-upload"
               :show-file-list="false"
               :on-change="handleAvatarChange"
               :before-upload="beforeAvatarUpload">
      <img v-if="registerForm.avatar"
           :src="registerForm.avatar"
           class="avatar" />
      <el-icon v-else
               class="avatar-upload-icon">
        <plus class="plus" />
      </el-icon>
    </el-upload>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
export default {
  name: "AvatarUpload",
  data () {
    return {
      imageUrl: ""
    }
  },
  methods: {
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
    handleAvatarChange: function (uploadFile) {
      this.registerForm.avatar = URL.createObjectURL(uploadFile.raw)
    },
  }
}


</script>

<style scoped>
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
</style>
