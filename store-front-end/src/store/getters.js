/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-25 12:45:16
 * @LastEditors: chuyang
 * @LastEditTime: 2022-05-25 13:10:08
 * @FilePath: \demo-front-end\src\store\getters.js
 */
export default {
  getUser: (state) => {
    return state.user;
  },
  getIsLogin: (state) => {
    return state.isLogin;
  },
  getShowLogin: (state) => {
    return state.showLogin;
  },
};
