/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-23 22:01:07
 * @LastEditors: chuyang
 * @LastEditTime: 2022-06-06 20:02:59
 * @FilePath: \demo-front-end\src\store\actions.js
 */

export default {
  updateIsLogin(content, payload) {
    content.commit("setIsLogin", payload);
  },
  updateShowLogin(content, payload) {
    content.commit("setShowLogin", payload);
  },
  updateShowRegister(content, payload) {
    content.commit("setShowRegister", payload);
  },
};
