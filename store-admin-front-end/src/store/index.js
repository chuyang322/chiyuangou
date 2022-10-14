/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-19 18:28:51
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 12:09:34
 * @FilePath: \store-admin-front-end\src\store\index.js
 */
import { createStore } from "vuex";
import actions from "./actions";
import mutations from "./mutations";
import state from "./state";
import getters from "./getters";
import user from "./modules/user";
import userList from "./modules/userList";
import goods from "./modules/goods";
import goodsList from "./modules/goodsList";

export default createStore({
  state,
  mutations,
  actions,
  getters,
  modules: {
    user,
    userList,
    goods,
    goodsList,
  },
});
