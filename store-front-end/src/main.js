/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-18 18:03:33
 * @LastEditors: chuyang
 * @LastEditTime: 2022-06-04 16:36:03
 * @FilePath: \demo-front-end\src\main.js
 */
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { components } from "./utils/elementPlus.js";
import "element-plus/lib/theme-chalk/index.css";

const app = createApp(App);
components.forEach((component) => {
  app.component(component.name, component);
});

app.use(router);
app.use(store);
//app.use(background);
app.mount("#app");

