/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 19:48:57
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 16:52:27
 * @FilePath: \store-front-end\src\store\modules\goods.js
 */
const state = {
  id: 0,
  name: "",
  image: "",
  info: "",
  price: "",
};

const getters = {};

const mutations = {
  setGoods(state, payload) {
    state.id = payload.id;
    state.name = payload.name;
    state.image = payload.image;
    state.info = payload.info;
    state.price = payload.price;
  },
};

const actions = {
  updateGoods(content, payload) {
    content.commit("setGoods", payload);
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
