export default {
  actions: {
    fetchMyInfo({ commit }, myNickname) {
      commit("FETCH_MY_INFO", myNickname);
      // return api.get(`/myinfo/:${myNickname}`).then((res) => {
      //     console.log(res);
      //     commit("FETCH_MY_INFO", res.data);
      //   });
    },
  },
};
