<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-02 18:00:10
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 17:22:37
 * @FilePath: \store-admin-front-end\src\components\GoodsManage\GoodsManage.vue
-->

<template>
  <div class="goods-manage">
    <table border="1">
      <tr>
        <th>id</th>
        <th>图片</th>
        <th>名字</th>
        <th>详情</th>
        <th>价格</th>
        <th>操作</th>
      </tr>
      <tr v-for="goods in goodsList.list"
          :key="goods.id">
        <td>{{goods.id}}</td>
        <td><img :src="goods.image"
               alt=""></td>
        <td>{{goods.name}}</td>
        <td>{{goods.info}}</td>
        <td>￥{{goods.price}}</td>
        <td>
          <el-button type="danger"
                     @click="handleDeletegoods(goods.id)">删除</el-button>
        </td>
      </tr>
    </table>
    <div class="page">
      <el-pagination background
                     layout="prev, pager, next"
                     :total="goodsList.total"
                     :page-size="goodsList.pageSize"
                     :current-page="current"
                     @current-change="handleCurrentChange" />
    </div>
  </div>
</template>

<script>
import { deleteGoods, getList } from '@/service/goods'
import { apiResultParse } from '@/utils/apiResult'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'v-goods-manage',
  data () {
    return {
      current: 1
    }
  },
  computed: {
    ...mapState([
      'goodsList'
    ]),
  },
  methods: {
    ...mapActions([
      'goodsList/updateList',
      'goodsList/updatePages',
      'goodsList/updateTotal',
      'goodsList/updateCurrent'
    ]),
    handleCurrentChange: async function (current) {
      this.current = current
      var data = await getList(current, this.goodsList.pageSize)
      if (apiResultParse(data)) {
        this['goodsList/updateList']({ list: data.data.records })
        this['goodsList/updatePages']({ pages: data.data.pages })
        this['goodsList/updateTotal']({ total: data.data.total })
        this['goodsList/updateCurrent']({ current: data.data.current })
      }
    },
    handleDeletegoods: async function (id) {
      var data = await deleteGoods(id)
      apiResultParse(data)
      this.initgoodsList()
    },
    initgoodsList: async function () {
      var data = await getList(1, this.goodsList.pageSize)
      if (apiResultParse(data)) {
        this['goodsList/updateList']({ list: data.data.records })
        this['goodsList/updatePages']({ pages: data.data.pages })
        this['goodsList/updateTotal']({ total: data.data.total })
        this['goodsList/updateCurrent']({ current: data.data.current })
      }
    }
  },
  mounted () {
    this.initgoodsList()
  }
}
</script>
<style scoped>
.goods-manage {
  width: 100%;
}
.page {
  text-align: center;
}
table {
  border-collapse: collapse;
  border-color: #2e0561;
  width: 100%;
}
th {
  padding: 10px;
  background-color: #545c64;
  color: #fff;
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
</style>