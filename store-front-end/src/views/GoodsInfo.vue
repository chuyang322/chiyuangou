<!--
 * @Description: 
 * @Author: chuyang
 * @Date: 2022-07-05 18:46:52
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-08 09:25:31
 * @FilePath: \store-front-end\src\views\GoodsInfo.vue
-->
<template>
  <div class="container">
    <div>
      <a href="#"
         @click="backHome"
         class="back-home">返回主页</a>
    </div>
    <div class="center_con clearfix">
      <div class="main_menu fl"><img :src="goods.image"
             class="goods-image"></div>
      <div class="goods_detail_list fr">
        <h3>{{goods.name}}</h3>
        <p>{{goods.info}}</p>
        <div class="prize_bar">
          <div class="show_prize fl">￥<em>{{goods.price}}</em></div>
        </div>
        <div class="goods_num clearfix goods-number">
          <div class="">数量：</div>
          <el-input-number v-model="number"
                           :min="1"
                           :max="10"
                           @change="handleChange" />
        </div>
        <div class="total">总价：<em>¥{{goods.price*number}}</em></div>
        <div class="operate_btn">
          <a href=""
             class="buy_btn">立即购买</a>
          <a class="add_cart"
             @click="addIntoCart">加入购物车</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import router from '@/router'
import { apiResultParse } from '@/utils/apiResult'
import { addCart } from '@/service/cart'
import { ElMessage } from 'element-plus'
export default {
  name: "GoodsInfo",
  data () {
    return {
      number: 1
    }
  },
  computed: {
    ...mapState([
      'goods',
      'user'
    ])
  },
  methods: {
    backHome: function () {
      router.push("/")
    },
    addIntoCart: async function () {
      if (this.user.id) {
        var cart = {
          user: this.user.id,
          goods: this.goods.id,
          number: this.number
        }
        var data = await addCart(cart)
        apiResultParse(data)
      } else {
        ElMessage.error("请先登录")
      }
    }
  },
}
</script>

<style scoped>
.container {
  padding: 20px;
  height: 500px;
  width: 100%;
}
body {
  font-family: 'Microsoft YaHei';
  color: #666;
  font-size: 12px;
}
/*顶部条状态*/
.header_con {
  height: 29px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ddd;
}
.header {
  width: 1200px;
  height: 29px;
  margin: 0 auto;
}

.welcome {
  font: 12px/29px 'Microsoft YaHei UI';
  float: left;
}
.user_login {
  height: 29px;
  line-height: 29px;
  float: right;
}
.user_login a {
  color: #666;
}
.user_login a:hover {
  color: #f80;
}
.search_bar {
  width: 1200px;
  height: 115px;
  margin: 0 auto;
}
.logo {
  margin: 20px 0 0 17px;
}
.search_con {
  width: 616px;
  height: 38px;
  border: 1px solid #ff9999;
  margin: 34px 0 0 123px;
  /*雪碧图的放大镜作背景图*/
  background: url() 10px -335px no-repeat;
}
.search_con .input_text {
  width: 470px;
  height: 37px;
  border: 0px;
  margin-left: 37px;
  outline: none;
}
.search_con .input_btn {
  width: 100px;
  height: 38px;
  background-color: pink;
  border: 0;
  font: 14px/38px 'Microsoft YaHei UI';
  color: #fff;
  /*鼠标变成手*/
  cursor: pointer;
}
.guest_card {
  width: 200px;
  height: 40px;
  margin-top: 34px;
}
.card_name {
  width: 158px;
  height: 38px;
  border: 1px solid #ddd;
  font: 14px/38px 'Microsoft YaHei UI';
  color: #ff9999;
  text-indent: 56px;
  background: url() 10px -300px no-repeat #f7f7f7;
}
.goods_count {
  width: 40px;
  height: 40px;
  background-color: #f80;
  font: bold 18px/40px 'Microsoft YaHei UI';
  text-align: center;
  color: #fff;
}
.navbar_con {
  height: 40px;
  border-bottom: 2px solid #ff9999;
  /*background: red;*/
}
.navbar {
  width: 1200px;
  height: 40px;
  margin: 0 auto;
  /*background: red;*/
}
.subnav_con h1 {
  width: 200px;
  height: 40px;
  background-color: pink;
  font: 14px/40px 'Microsoft YaHei UI';
  text-align: center;
  color: #fff;
}
.subnav_con i {
  width: 11px;
  height: 7px;
  overflow: hidden;
  display: inline-block;
}
.navlist {
  overflow: hidden;
}
.navlist li {
  float: left;
  height: 14px;
  padding: 0 25px;
  border-left: 1px solid #666;
  margin-left: -2px;
  margin-top: 13px;
}
.submena {
  width: 1200px;
  height: 30px;
  margin: 0 auto;
  /*background:yellow;*/
}
.submena a {
  color: #37ab40;
  line-height: 30px;
}

.center_con {
  width: 1200px;
  height: 350px;
  margin: 0 auto;
  /*background: yellow;*/
}
.center_con .main_menu {
  width: 350px;
  height: 350px;
  overflow: hidden;
}
.goods_detail_list {
  width: 730px;
  height: 350px;
}
.goods_detail_list h3 {
  font-size: 24px;
  line-height: 24px;
  color: #666;
  font-weight: normal;
}
.goods_detail_list p {
  color: #666;
  line-height: 40px;
}
.prize_bar {
  height: 72px;
  background-color: #fff5f5;
  line-height: 72px;
}
.prize_bar .show_prize {
  font-size: 20px;
  color: #ff3e3e;
  padding-left: 20px;
}
.prize_bar .show_pirze em {
  font-style: normal;
  font-size: 36px;
  padding-left: 10px;
}
.prize_bar .show_unit {
  padding-left: 150px;
}
.goods_num {
  height: 52px;
  margin-top: 19px;
  /*background: yellow;*/
}
.goods_num .num_name {
  width: 70px;
  height: 52px;
  line-height: 52px;
}
.goods_num .num_add {
  width: 75px;
  height: 50px;
  border: 1px solid #dddddd;
}
.goods_num .num_add .num_show {
  width: 49px;
  height: 50px;
  text-align: center;
  line-height: 50px;
  border: 0px;
  outline: none;
  font-size: 14px;
  color: #666;
}
.goods_num .num_add .add,
.goods_num .num_add .minus {
  width: 25px;
  line-height: 25px;
  text-align: center;
  border-left: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  color: #666;
  font-size: 14px;
}
.goods_num .num_add .minus {
  border-bottom: 0px;
}
.total {
  height: 35px;
  line-height: 35px;
  margin-top: 25px;
  /*background: yellow;*/
}
.total em {
  font-style: normal;
  color: #ff3e3e;
  font-size: 18px;
}
.operate_btn {
  height: 40px;
  margin-top: 35px;
  font-size: 0;
  position: relative;
}
.operate_btn .buy_btn,
.operate_btn .add_cart {
  display: inline-block;
  width: 178px;
  height: 38px;
  border: 1px solid #c40000;
  font-size: 14px;
  color: #c40000;
  line-height: 38px;
  text-align: center;
  background-color: #ffeded;
}
.operate_btn .add_cart {
  background-color: #c40000;
  color: #fff;
  margin-left: 10px;
  position: relative;
  z-index: 10;
}
body,
p,
h1,
h2,
h3,
h4,
h5,
h6,
ul,
dl,
dt,
form,
input {
  margin: 0;
  padding: 0;
}

ul {
  list-style: none;
}
a {
  text-decoration: none;
}

em {
  font-style: normal;
}

img {
  border: 0px;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-size: 100%;
}
.clearfix:before,
.clearfix:after {
  content: '';
  display: table;
}
.clearfix:after {
  clear: both;
}
.clearfix {
  zoom: 1;
}

.fl {
  float: left;
}
.fr {
  float: right;
}
.goods-image {
  height: 400px;
  width: 300px;
}
.back-home {
  text-decoration: none;
  color: #409eff;
  font-size: small;
}
.goods-number {
  display: flex;
  flex-direction: row;
  align-items: center;
}
</style>