/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-18 18:30:58
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-07 01:44:40
 * @FilePath: \store-admin-front-end\src\router\index.js
 */
import { createRouter, createWebHashHistory } from "vue-router";
import { defineAsyncComponent } from "vue";

const router = createRouter({
  history: createWebHashHistory(), // hash 模式
  // history: createWebHistory(), // history 模式
  routes: [
    {
      path: "/",
      name: "home",
      component: defineAsyncComponent(() => import(`../views/Home/Home.vue`)),
      meta: {
        title: "首页",
      },
      children: [
        {
          path: "/hello",
          name: "欢迎",
          component: defineAsyncComponent(() =>
            import(`../components/Hello/Hello.vue`)
          ),
        },
        {
          path: "/userManage",
          name: "用户管理",
          component: defineAsyncComponent(() =>
            import(`../components/UserManage/UserManage.vue`)
          ),
        },
        {
          path: "/goodsManage",
          name: "商品管理",
          component: defineAsyncComponent(() =>
            import(`../components/GoodsManage/GoodsManage.vue`)
          ),
        },
        {
          path: "/addGoods",
          name: "新增商品",
          component: defineAsyncComponent(() =>
            import(`../components/AddGoods/AddGoods.vue`)
          ),
        },
      ],
    },
    {
      path: "/login",
      name: "登录",
      component: defineAsyncComponent(() => import(`../views/Login/Login.vue`)),
    },
    {
      path: "/*",
      redirect: "/",
    },
  ],
});

// 全局路由守卫
router.beforeEach((to, from, next) => {
  console.log(to, from);
  if (to.meta.title) {
    document.title = `${to.meta.title}`;
  }
  next();
});

router.afterEach((to, from) => {
  console.log(to, from);
  console.log("afterEach");
});

export default router;
