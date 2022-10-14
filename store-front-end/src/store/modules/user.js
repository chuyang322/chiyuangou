/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 19:48:37
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 00:48:44
 * @FilePath: \store-front-end\src\store\modules\user.js
 */
const state = {
  id: undefined,
  name: undefined,
  createTime: undefined,
  email: undefined,
  phone: undefined,
  avatar: undefined,
  sex: undefined,
};

const getters = {};

const mutations = {
  setUser(state, { user }) {
    state.id = user.id;
    state.name = user.name;
    state.createTime = user.createTime;
    state.email = user.email;
    state.phone = user.phone;
    state.sex = user.sex;
    state.avatar=user.avatar;
    localStorage.setItem("user", JSON.stringify(user));
  },
  setId(state, { id }) {
    state.id = id;
  },
  setName(state, { name }) {
    state.name = name;
  },
  setCreateTime(state, { createTime }) {
    state.createTime = createTime;
  },
  setEmail(state, { email }) {
    state.email = email;
  },
  setPhone(state, { phone }) {
    state.phone = phone;
  },
  setSex(state, { sex }) {
    state.sex = sex;
  },
  setAvatar(state, { avatar }) {
    state.avatar = avatar;
  },
};

const actions = {
  updateUser(content, payload) {
    content.commit("setUser", payload);
  },
  updateId(content, payload) {
    content.commit("setId", payload);
  },
  updateName(content, payload) {
    content.commit("setName", payload);
  },
  updateCreateTime(content, payload) {
    content.commit("setCreateTime", payload);
  },
  updateEmail(content, payload) {
    content.commit("setEmail", payload);
  },
  updatePhone(content, payload) {
    content.commit("setPhone", payload);
  },
  updateSex(content, payload) {
    content.commit("setSex", payload);
  },
  updateAvatar(content, payload) {
    content.commit("setAvatar", payload);
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
