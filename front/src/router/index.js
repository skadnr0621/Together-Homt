import Vue from "vue";
import VueRouter from "vue-router";

import FirstPage from "@/views/FirstPage";

import MyPageRouters from "@/router/mypage";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "FirstPage",
    component: FirstPage,
  },
  { ...MyPageRouters },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
