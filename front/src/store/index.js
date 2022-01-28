import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import State from "@/store/states.js";
import Mutations from "@/store/mutations.js";
import Actions from "@/store/actions.js";

import userStore from '@/store/userStore'

export default new Vuex.Store({
  ...State,
  ...Mutations,
  ...Actions,

  modules: {
    userStore: userStore,
  },
  plugins: [
    createPersistedState({
      paths: ['userStore' ],
    })
  ]
});
