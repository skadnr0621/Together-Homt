import Vue from "vue";
import VueRouter from "vue-router";

import FirstPage from "@/views/FirstPage";

import MainRouters from "@/router/main";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "FirstPage",
    component: FirstPage,
  },
  { ...MainRouters },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
