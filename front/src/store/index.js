import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import State from "@/store/states.js";
import Mutations from "@/store/mutations.js";
import Actions from "@/store/actions.js";
import userStore from "@/store/userStore";

Vue.use(Vuex);

export default new Vuex.Store({
  ...State,
  ...Mutations,
  ...Actions,

  modules: {
    userStore: userStore,
  },
  plugins: [
    createPersistedState({
      paths: ["userStore"],
    }),
  ],
});
