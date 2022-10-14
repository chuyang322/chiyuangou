<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-06-05 22:02:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 22:54:31
 * @FilePath: \store-front-end\src\components\GoodsList\GoodsList.vue
-->
<template>
  <div class="goods-list">
    <div class="goods-container">
      <div v-for="goods in goodsList.list"
           :key="goods.id"
           class="goods-card"
           @click="goodsInfo(goods)">
        <div>
          <el-image class="goods-image"
                    :src="goods.image">

          </el-image>
          <div class="goods-info">
            <div class="goods-name">{{goods.name}}</div>
            <div class="goods-price">￥{{goods.price}}</div>
          </div>
        </div>

      </div>
    </div>
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
import router from '@/router'

export default {
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
      'goodsList/updateCurrent',
      'goods/updateGoods'
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
    },
    goodsInfo: function (goods) {
      this['goods/updateGoods'](goods)
      router.push("/goodsInfo")
    }
  },
  mounted () {
    this.initgoodsList()
  }
}
</script>

<style scoped>
.goods-container {
  display: flex;
  flex-direction: row;
  flex-flow: wrap;
  margin: 10px 10px 10px 10px;
  height: 100%;
}
.goods-card {
  border-radius: 6px;
  margin: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-shadow: rgb(202, 202, 202) 3px 3px;
}
.goods-image {
  height: 240px;
  width: auto;
  border-radius: 6px;
  aspect-ratio: 3/4;
}
.goods-name {
  overflow: hidden;
}
.goods-price {
  color: red;
}
.article-list-item-title {
  border-bottom: 1px solid black;
}

.article-list-item-body {
  text-indent: 2em;
}

.page {
  text-align: center;
}
</style>