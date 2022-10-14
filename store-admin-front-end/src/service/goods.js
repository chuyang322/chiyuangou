/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 16:28:35
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 14:51:13
 * @FilePath: \store-admin-front-end\src\service\goods.js
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

export function getGoodsByKeyword(p, size, keyword) {
  return axios.get(`/goods/list/${p}/${size}/${keyword}`);
}

export function getList(current, size) {
  return axios.get(`/goods/list/${current}/${size}`);
}

export function uploadImage(params) {
  return axios.post(`/images/uploadImage`, params);
}
