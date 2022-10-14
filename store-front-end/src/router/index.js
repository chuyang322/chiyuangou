/*
 * @Description:
 * @Author: chuyang
 * @Date: 2022-05-18 18:30:58
 * @LastEditors: chuyang
 * @LastEditTime: 2022-07-08 10:17:47
 * @FilePath: \store-front-end\src\router\index.js
 */
import { createRouter, createWebHashHistory } from "vue-router";
import { defineAsyncComponent } from "vue";

const router = createRouter({
  history: createWebHashHistory(), // hash 模式
  // history: createWebHistory(), // history 模式
  base: "/store/",
  routes: [
    {
      path: "/",
      name: "home",
      component: defineAsyncComponent(() => import(`../views/Home.vue`)),
      meta: {
        title: "首页",
      },
      children: [
        {
          path: "/goods",
          name: "goods",
          component: defineAsyncComponent(() =>
            import(`../components/GoodsList/GoodsList.vue`)
          ),
        },
        {
          path: "/cart",
          name: "cartInfo",
          component: defineAsyncComponent(() =>
            import(`../views/CartInfo.vue`)
          ),
        },
      ],
    },
    {
      path: "/modifyInfo",
      name: "modifyInfo",
      component: defineAsyncComponent(() => import(`../views/ModifyInfo.vue`)),
    },
    {
      path: "/changeAvatar",
      name: "changeAvatar",
      component: defineAsyncComponent(() =>
        import(`../views/ChangeAvatar.vue`)
      ),
    },
    {
      path: "/goodsInfo",
      name: "goodsInfo",
      component: defineAsyncComponent(() => import(`../views/GoodsInfo.vue`)),
    },
    {
      path: "/changePassword",
      name: "修改密码",
      component: defineAsyncComponent(() => import(`../views/ChangePassword.vue`)),
    },
    {
      path: "/test",
      name: "test",
      component: defineAsyncComponent(() =>
        import(`../views/ChangePassword.vue`)
      ),
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
