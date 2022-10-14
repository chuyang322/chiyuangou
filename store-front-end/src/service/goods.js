/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 16:28:35
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 23:12:44
 * @FilePath: \store-front-end\src\service\goods.js
 */
import axios from "../utils/axios";

export function addGoods(params) {
  return axios.post("/goods/goods", params);
}

export function updateGoods(params) {
  return axios.put("/goods/goods", params);
}

export function deleteGoods(params) {
  return axios.delete(`/goods/goods/${params}`);
}

export function getList(current, size) {
  return axios.get(`/goods/list/${current}/${size}`);
}

export function getGoodsByKeyword(current, size, keyword) {
  return axios.get(`/goods/klist/${current}/${size}/${keyword}`);
}

export function uploadImage(params) {
  return axios.post(`/images/uploadImage`, params);
}
export function changePassword(params) {
  return axios.post(`/user/changePassword`, params);
}
