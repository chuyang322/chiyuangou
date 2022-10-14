/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-23 21:54:57
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-02 20:35:28
 * @FilePath: \demo-admin-front-end\src\service\user.js
 */

import axios from "../utils/axios";

export function getUserInfo() {
  return axios.get("/user/info");
}

export function EditUserInfo(params) {
  return axios.put("/user/info", params);
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

export function getUsers(current, size) {
  return axios.get(`/user/list/${current}/${size}`);
}

export function deleteUser(id){
  return axios.delete(`/user/users/${id}`);
}
