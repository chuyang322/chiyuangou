/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 19:48:57
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-04 18:38:43
 * @FilePath: \store-front-end\src\store\modules\goods.js
 */
const state = {
  id: 0,
};

const getters = {};

const mutations = {
  setArticle(state, payload) {
    state.Article = payload.Article;
  },
};

const actions = {
  updateArticle(content, payload) {
    content.commit("setArticle", payload);
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
