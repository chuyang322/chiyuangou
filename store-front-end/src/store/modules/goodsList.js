/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 19:48:57
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 14:45:53
 * @FilePath: \store-admin-front-end\src\store\modules\goodsList.js
 */

const state = {
  list: [],
  current: 1,
  pages: 0,
  total: 0,
  keyword: "",
  pageSize: 10,
};

const getters = {};

const mutations = {
  setList(state, { list }) {
    state.list = list;
  },
  setPages(state, { pages }) {
    state.pages = pages;
  },
  setTotal(state, { total }) {
    state.total = total;
  },
  setCurrent(state, { current }) {
    state.current = current;
  },
};

const actions = {
  updateList(content, { list }) {
    content.commit("setList", { list });
  },
  updatePages(content, { pages }) {
    content.commit("setPages", { pages });
  },
  updateTotal(content, { total }) {
    content.commit("setTotal", { total });
  },
  updateCurrent(content, { current }) {
    content.commit("setCurrent", { current });
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
