import axios from "axios";
const config = { Authorization : sessionStorage.getItem("jwt") }

export default {
  namespaced: true,

  state: {
    groupMembers: [],
  },

  mutations: {
    async GET_GROUP_MEMBERS(state, groupMembers) {
      state.groupMembers = groupMembers
    }
  },

  actions: {
    async getGroupMembers({commit}, groupId) {
      return await 
      axios({
        method: "get",
        url: `/group/groups/${groupId}/members`,
        headers: config
      })
      .then((res) => {
        console.log(res.data)
        commit("GET_GROUP_MEMBERS", res.data);
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
};