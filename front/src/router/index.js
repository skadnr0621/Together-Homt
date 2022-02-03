import Vue from "vue";
import VueRouter from "vue-router";

import FindPW from "@/views/user/FindPW";

import FirstPageRouters from "@/router/firstpage"
import MainRouters from "@/router/main";

import MotionDetect from "@/views/exercise/MotionDetect";


Vue.use(VueRouter);

const routes = [
  { ...FirstPageRouters},
  {
    name: "FindPW",
    path: "/findpw/",
    component: FindPW,
  },
  { ...MainRouters },
  {
    name: "MotionDetect",
    path: "/exercise/",
    component: MotionDetect,
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
