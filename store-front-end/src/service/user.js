/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-23 21:54:57
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-08 10:02:21
 * @FilePath: \store-front-end\src\service\user.js
 */

import axios from "../utils/axios";

export function modifyUserInfo(params) {
  return axios.put("/user/users", params);
}

export function login(params) {
  return axios.post("/login", params);
}

export function logout() {
  return axios.post("/logout");
}

export function register(params) {
  return axios.post("/register", params);
}

export function verifySignCode(params) {
  return axios.get(`/verifySignCode/${params}`);
}

export function changeAvatar(params) {
  return axios.post(`/images/avatarUpload`,params)
}

export function changePassword(params){
  return axios.post(`/user/changePassword`,params)
}
