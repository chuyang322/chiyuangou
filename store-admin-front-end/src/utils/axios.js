/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-23 21:45:41
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 22:37:33
 * @FilePath: \store-admin-front-end\src\utils\axios.js
 */
import axios from "axios";

axios.defaults.baseURL =
  process.env.NODE_ENV == "development"
    ? "/api"
    : "http://localhost:8080/store-back-end";
axios.defaults.withCredentials = true;
axios.defaults.headers["X-Requested-With"] = "XMLHttpRequest";
axios.defaults.headers["token"] = localStorage.getItem("token") || "";
axios.defaults.headers.post["Content-Type"] = "application/json";

axios.interceptors.response.use((res) => {
  console.log("res------");
  console.log(res);
  if (
    res.data != null &&
    res.data.data != null &&
    res.data.data.token != null
  ) {
    axios.defaults.headers["token"] = res.data.data.token;
    localStorage.setItem("token", res.data.data.token);
  }
  return res.data;
});

export default axios;
