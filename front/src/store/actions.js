import api from "@/api";

export default {
  actions: {
    async setAllMyInfo({ commit }, email) {
      return await api.get(`user/profile/${email}`).then((res) => {
        console.log(res);
        commit("SET_MY_INFO", res.data);
        commit("SET_MY_FEEDS", res.data.feeds);
        commit("SET_MY_COMMENTS", res.data.comments);
        commit("SET_MY_FOLLOWS", res.data.follower, res.data.following);
        commit("SET_MY_RECORDS", res.data.records);
        commit("SET_MY_LIKES", res.data.likes);
      });
    },

    async setMyInfo({ commit }, email) {
      return await api.get(`user/profile/${email}`).then((res) => {
        console.log(res);
        commit("SET_MY_INFO", res.data);
      });
    },

    async setMyFeeds({ commit }, email) {
      return await api.get(`feed/personal_feed/${email}`).then((res) => {
        console.log(res);
        commit("SET_MY_FEEDS", res.data);
      });
    },

    async setMyComments({ commit }, feedId) {
      return await api.get(`feed/${feedId}/comment`).then((res) => {
        console.log(res);
        commit("SET_MY_COMMENTS", res.data);
      });
    },
  },
};
