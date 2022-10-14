<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 22:27:09
 * @FilePath: \store-front-end\src\views\CartInfo.vue
-->
<template>
  <div class="container">
    <div class="form-container">
      <table border="1">
        <tr>
          <th>
            选择
          </th>
          <th>订单号</th>
          <th>图片</th>
          <th>名字</th>
          <th>详情</th>
          <th>价格</th>
          <th>数量</th>
          <th>总价</th>
        </tr>
        <tr v-for="goods in cart.list"
            :key="goods.id">
          <td class="check">
            <input type="checkbox"
                   v-model="checkedData"
                   :value="{id:goods.id,money:goods.number*goods.price}" />
          </td>
          <td>{{goods.id}}</td>
          <td><img :src="goods.image"
                 alt=""></td>
          <td>{{goods.name}}</td>
          <td>{{goods.info}}</td>
          <td>￥{{goods.price}}</td>
          <td>{{goods.number}}</td>
          <td>￥{{goods.number*goods.price}}</td>
        </tr>
      </table>
      <div class="page">
        <el-pagination background
                       layout="prev, pager, next"
                       :total="cart.total"
                       :page-size="cart.pageSize"
                       :current-page="current"
                       @current-change="handleCurrentChange" />
      </div>
      <div class="price">￥{{totalPrices}}</div>
      <div class="form-button">
        <el-button type=""
                   @click="deleteCart">删除</el-button>
        <el-button type="danger">结算</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router'
import { deleteByList, getList } from '@/service/cart'
import { mapState, mapActions } from 'vuex'
import { apiResultParse } from '@/utils/apiResult'
export default {
  name: "CartInfo",
  data () {
    return {
      checkedData: []
    }
  },
  computed: {
    ...mapState([
      'user',
      'cart'
    ]),
    totalPrices: function () {
      var sum = 0
      for (var data in this.checkedData) {
        sum += this.checkedData[data].money
      }
      return sum
    },
    idList: function () {
      var list = []
      for (var data in this.checkedData) {
        list.push(this.checkedData[data].id)
      }
      return list
    }
  },
  methods: {
    ...mapActions([
      'cart/updateList',
      'cart/updateCurrent'
    ]),
    backHome: function () {
      router.push("/")
    },
    handleCurrentChange: function (current) {
      this.initList(this.user.id, current, this.cart.pageSize)
    },
    initList: async function (id, current, size) {
      var data = await getList(id, current, size)
      if (apiResultParse(data)) {
        this['cart/updateList']({ list: data.data })
        this['cart/updateCurrent']({ current: current })
      }
      return
    },
    deleteCart: async function () {
      var data = await deleteByList(this.idList)
      if (apiResultParse(data)) {
        this.initList(this.user.id, this.cart.current, this.cart.pageSize)
      }
    }
  },
  mounted () {
    this.initList(this.user.id, this.cart.current, this.cart.pageSize)
  },
}
</script>

<style scoped>
.container {
  width: 100%;
}
.form-container {
  margin-top: 10px;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
}
a {
  text-decoration: none;
  color: #409eff;
  font-size: small;
}
.form-button {
  text-align: center;
}
.page {
  text-align: center;
  margin-top: 10px;
}
table {
  border-collapse: collapse;
  border-color: #2e0561;
  width: 100%;
}
th {
  padding: 10px;
  color: #000;
  font-weight: 300;
  text-align: center;
}
td {
  padding: 10px;
  max-width: 100px;
  overflow-y: hidden;
  overflow-x: inherit;
  text-align: center;
}

td::-webkit-scrollbar {
  display: none;
}
img {
  height: 80px;
  width: 60px;
}
.check {
  width: 60px;
}
.price {
  color: red;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>