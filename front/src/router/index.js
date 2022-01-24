import Vue from "vue";
import VueRouter from "vue-router";

import FindPW from "@/views/user/FindPW";

import FirstPageRouters from "@/router/firstpage"
import MyPageRouters from "@/router/mypage";

Vue.use(VueRouter);

const routes = [
  { ...FirstPageRouters},
  {
    name: "FindPW",
    path: "/findpw/",
    component: FindPW,
  },
  { ...MyPageRouters },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
