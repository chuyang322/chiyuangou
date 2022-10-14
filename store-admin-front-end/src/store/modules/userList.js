/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-07-02 19:27:25
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-02 19:29:47
 * @FilePath: \demo-admin-front-end\src\store\modules\userList.js
 */
const state = {
  users: [],
  current: 1,
  pages: 0,
  total: 0,
  pageSize: 10,
};

const getters = {};

const mutations = {
  setUsers(state, { users }) {
    state.users = users;
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
  updateUsers(content, { users }) {
    content.commit("setUsers", { users });
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
