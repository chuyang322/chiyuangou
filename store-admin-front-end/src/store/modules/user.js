/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 19:48:37
 * @LastEditors: chuyang
 * @LastEditTime: 2022-06-05 20:35:12
 * @FilePath: \demo-front-end\src\store\modules\user.js
 */
const state = {
  id: 0,
  name: "",
  createTime: "",
};

const getters = {};

const mutations = {
  setUser(state, {user}) {
    localStorage.setItem("user", JSON.stringify(user));
    state.id = user.id;
    state.name = user.name;
    state.createTime=user.createTime;
  },
};

const actions = {
  updateUser(content, {user}) {
    content.commit("setUser", {user});
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
