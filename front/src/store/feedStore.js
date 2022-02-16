import axios from "axios";

export default {
  namespaced: true,

  state: {
    // 피드 정보
    feedInfo: [],

    // 댓글 정보
    commentInfo: [],

    // 좋아요 정보
    likeInfo: [],
  },

  mutations: {
    SET_FEED_INFO(state, feedInfo) {
      state.feedInfo = feedInfo;
    },
    SET_COMMENT_INFO(state, commentInfo) {
      state.commentInfo = commentInfo;
    },
    SET_LIKE_INFO(state, likeInfo) {
      state.likeInfo = likeInfo;
    },
  },

  actions: {
    // 피드 조회해서 저장하기
    async setFeedInfo({ commit }, payload) {
      return await axios
        .get(`/feed/feeds/${payload.email}/temp`, null, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);
          commit("SET_FEED_INFO", res.data);
        });
    },

    // 댓글 조회해서 저장하기
    async setCommentInfo({ commit }, payload) {
      return await axios
        .get(`/feed/${payload.feedId}/comments`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_COMMENT_INFO", res.data);
        });
    },

    // 좋아요 조회해서 저장하기
    async setLikeInfo({ commit }, payload) {
      return await axios
        .get(`/feed/${payload.feedId}/likes`, {
          headers: {
            Authorization: payload.token,
          },
        })
        .then((res) => {
          console.log(res);

          commit("SET_LIKE_INFO", res.data);
        });
    },
  },
};
