import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import State from "@/store/states.js";
import Mutations from "@/store/mutations.js";
import Actions from "@/store/actions.js";

export default new Vuex.Store({
  ...State,
  ...Mutations,
  ...Actions,
});
