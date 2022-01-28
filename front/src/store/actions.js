import api from "@/api";

export default {
  actions: {
    setMyInfo({ commit }, email) {
      return api.get(`user/profile/${email}`).then((res) => {
        console.log(res);
        commit("SET_MY_INFO", res.data);
      });
      // return api.get(`/myinfo/:${myNickname}`).then((res) => {
      //     console.log(res);
      //     commit("SET_MY_INFO", res.data);
      //   });
    },
  },
};
