import axios from "axios";

export default {
  actions: {
    // 내 정보 조회해서 저장하기
    async setMyInfo({ commit }, payload) {
      return await axios
        .get(`/user/users/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);
          commit("SET_MY_INFO", res.data);
        });
    },

    // 다른 유저 정보 조회해서 저장하기
    async setUserInfo({ commit }, payload) {
      return await axios
        .get(`/user/users/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);
          commit("SET_USER_INFO", res.data);
        });
    },

    // 나 or 유지 피드 조회해서 저장하기
    async setUserFeeds({ commit }, payload) {
      return await axios
        .get(`/feed/feeds/${payload.email}/temp`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          let resData = {
            feedCnt: res.data.length,
            feeds: res.data,
          };

          commit("SET_USER_FEEDS", resData);
        });
    },

    // 나의 팔로워 + 팔로잉 조회해서 저장하기
    async setMyFollow({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_MY_FOLLOW", res);
        });
    },

    // 나의 팔로워 조회해서 저장하기
    async setMyFollower({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}/followers`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_MY_FOLLOWER", res.data);
        });
    },

    // 나의 팔로잉 조회해서 저장하기
    async setMyFollowing({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}/followings`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_MY_FOLLOWING", res.data);
        });
    },

    // 다른 유저의 팔로워 + 팔로잉 조회해서 저장하기
    async setUserFollow({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_USER_FOLLOW", res);
        });
    },

    // 다른 유저의 팔로워 조회해서 저장하기
    async setUserFollower({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}/followers`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_USER_FOLLOWER", res.data);
        });
    },

    // 다른 유저의 팔로잉 조회해서 저장하기
    async setUserFollowing({ commit }, payload) {
      return await axios
        .get(`/communication/follows/${payload.email}/followings`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_USER_FOLLOWING", res.data);
        });
    },

    // 피드 댓글 조회해서 저장하기
    async setFeedComments({ commit }, payload) {
      return await axios
        .get(`/feed/${payload.feedId}/comment`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);
          commit("SET_FEED_COMMENTS", res.data);
        });
    },

    getAllUsers({ commit }) {
      axios({
        method: "get",
        url: "http://i6b206.p.ssafy.io:8092/slide1/userlist",
      }).then((res) => {
        console.log(res);
        res.data.sort((a, b) => {
          return b.follower_cnt - a.follower_cnt;
        });
        commit("GET_ALL_USERS", res.data);
      });
    },
  },
};
