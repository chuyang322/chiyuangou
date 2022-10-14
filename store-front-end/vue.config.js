/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-18 18:03:33
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 17:23:48
 * @FilePath: \store-front-end\vue.config.js
 */
// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     port: 8083,
//     proxy: {
//       "/api": {
//         // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
//         target: "http://localhost:8080", //目标地址，一般是指后台服务器地址
//         changeOrigin: true, //是否跨域
//         pathRewrite: {
//           // pathRewrite 的作用是把实际Request Url中的'/api'用""代替
//           "^/api": "/store-back-end",
//         },
//       },
//     },
//   },
// });
module.exports = {
  publicPath: "./",
};
