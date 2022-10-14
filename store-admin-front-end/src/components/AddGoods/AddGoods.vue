<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 16:16:29
 * @FilePath: \store-admin-front-end\src\components\AddGoods\AddGoods.vue
-->
<template>
  <div class="container">
    <div class="form-container">
      <el-form label-position="right"
               label-width="auto">
        <div class="image-container">
          <el-upload class="image-upload"
                     :show-file-list="false"
                     :on-change="handleImageChange"
                     :before-upload="beforeImageUpload">
            <img v-if="image"
                 :src="image"
                 class="image" />
            <el-icon v-else
                     class="image-upload-icon">
              <plus class="plus" />
            </el-icon>
          </el-upload>
        </div>
        <el-form-item prop="image"
                      label="展示图片">
          <el-input v-model="image"
                    name="image"
                    disabled />
        </el-form-item>
        <el-form-item prop="name"
                      label="商品名称">
          <el-input v-model="name"
                    name="name" />
        </el-form-item>
        <el-form-item prop="info"
                      label="商品详情">
          <el-input v-model="info"
                    name="info" />
        </el-form-item>

        <el-form-item prop="price"
                      label="价格">
          <el-input v-model="price"
                    name="price"
                    type="number"
                    @mousewheel.prevent></el-input>
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
import { mapState, mapActions } from 'vuex'
import { addGoods, uploadImage } from '@/service/goods'
import { apiResultParse } from '@/utils/apiResult'
import { ElMessage } from 'element-plus'
export default {
  name: "ModifyInfo",
  data () {
    return {

      image: "",
      name: "",
      price: 0,
      info: "",
      file: undefined,
    }
  },
  computed: {
    ...mapState([
      'goods'
    ])
  },
  mounted () {
    this.goodsForm = this.goods
  },
  methods: {
    ...mapActions([
      'goodsForm/updategoodsForm',
      'updateIsLogin',
    ]),
    submitForm: function () {
      return new Promise((resolve) => {
        resolve(this.handleUploadImage())
      }).then(() => {
        this.handleSubmitData()
      }).then(() => {
        ElMessage.success("添加成功")
      })
    },
    handleSubmitData: async function () {
      var goods = {
        name: this.name,
        info: this.info,
        image: this.image,
        price: this.price
      }
      var data = addGoods(goods)
      apiResultParse(data)
    },
    beforeImageUpload: function (rawFile) {
      if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
        ElMessage.error('图片必须为jpg或png')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('图片大小不超过2MB')
        return false
      }
      return true
    },
    handleUploadImage: async function () {
      var fd = new FormData()
      fd.append("file", this.file.raw)
      var data = await uploadImage(fd)
      if (apiResultParse(data)) {
        this.image = data.data
        return true
      } else {
        return false
      }
    },
    handleImageChange: function (file) {
      this.file = file
      this.image = URL.createObjectURL(file.raw)
    },
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
.image-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: baseline;
  margin-bottom: 20px;
}
.image-upload {
  height: 160px;
  width: 120px;
}

.image {
  height: 200px;
  width: 120px;
}
.image-upload {
  border: 1px dashed black;
  border-radius: 6px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.image-upload-icon {
  color: black;
  font-size: 15px;
}
input::-webkit-outer-spin-button input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}
</style>