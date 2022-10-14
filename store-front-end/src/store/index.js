/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-19 18:28:51
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 18:39:24
 * @FilePath: \store-front-end\src\store\index.js
 */
import { createStore } from "vuex";
import actions from "./actions";
import mutations from "./mutations";
import state from "./state";
import getters from "./getters";
import goods from "./modules/goods";
import user from "./modules/user";
import goodsList from "./modules/goodsList";
import cart from "./modules/cart";

export default createStore({
  state,
  mutations,
  actions,
  getters,
  modules: {
    goods,
    user,
    goodsList,
    cart,
  },
});
