import api from "@/api";
import axios from "axios";

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

    setMyInfo({ commit }) {
      axios({
        method: 'get',
        url: '/user',
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
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



    getAllUsers({commit}) {
      axios({
        method: 'get',
        url: 'http://i6b206.p.ssafy.io:8092/slide1/userlist'
      })
      .then((res)=> {
        console.log(res)
        res.data.sort((a, b) => {
          return b.follower_cnt - a.follower_cnt
        })
        commit("GET_ALL_USERS", res.data)
      })
    }
  },
};
