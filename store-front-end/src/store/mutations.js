/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-23 22:01:29
 * @LastEditors: chuyang
 * @LastEditTime: 2022-06-06 20:02:47
 * @FilePath: \demo-front-end\src\store\mutations.js
 */

export default {
  setIsLogin(state, payload) {
    state.isLogin = payload.isLogin;
  },
  setShowLogin(state, payload) {
    state.showLogin = payload.showLogin;
  },
  setShowRegister(state, payload) {
    state.showRegister = payload.showRegister;
  },
};
