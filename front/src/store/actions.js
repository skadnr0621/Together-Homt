import axios from "axios";

export default {
  actions: {
    // 내 정보 조회해서 저장하기
    async setMyInfo({ commit }, payload) {
      return await axios
        .get(`/user`, {
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
        .get(`/user/${payload.email}/profile`, {
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
        .get(`/slide3/profilefeed/${payload.email}`, {
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
        .get(`/slide3/followlist/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          let resData = res.data;
          resData.followerCnt = res.data.followerlist.length;
          resData.followingCnt = res.data.followinglist.length;

          commit("SET_MY_FOLLOW", resData);
        });
    },

    // 나의 팔로워 조회해서 저장하기
    async setMyFollower({ commit }, payload) {
      return await axios
        .get(`/slide3/followerlist/${payload.email}`, {
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
        .get(`/slide3/followinglist/${payload.email}`, {
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
        .get(`/slide3/followlist/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          let resData = res.data;
          resData.followerCnt = res.data.followerlist.length;
          resData.followingCnt = res.data.followinglist.length;

          commit("SET_USER_FOLLOW", resData);
        });
    },

    // 다른 유저의 팔로워 조회해서 저장하기
    async setUserFollower({ commit }, payload) {
      return await axios
        .get(`/slide3/followerlist/${payload.email}`, {
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
        .get(`/slide3/followinglist/${payload.email}`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_USER_FOLLOWING", res.data);
        });
    },
  },
};
