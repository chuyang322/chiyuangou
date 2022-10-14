/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-06-05 16:28:35
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-06 22:25:48
 * @FilePath: \store-front-end\src\service\cart.js
 */
import axios from "../utils/axios";

export function addCart(params) {
  return axios.post("/cart/carts", params);
}

export function updatecart(params) {
  return axios.put("/cart/carts", params);
}

export function deleteCart(params) {
  return axios.delete(`/cart/carts/${params}`);
}

export function getcartByKeyword(p, size, keyword) {
  return axios.get(`/cart/list/${p}/${size}/${keyword}`);
}

export function getList(id, current, size) {
  return axios.get(`/cart/carts/${id}/${current}/${size}`);
}

export function uploadImage(params) {
  return axios.post(`/images/uploadImage`, params);
}

export function deleteByList(params) {
  return axios.get(`/cart/deleteByIds?list=` + params);
}
