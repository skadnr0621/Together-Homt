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

    // 검색해서 보여줄 피드들
    searchFeedList: [],
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

    SET_SEARCH_FEEDS(state, feedList) {
      state.searchFeedList = feedList;
    },

    UPDATE_SEARCH_FEEDS(state, payload) {
      state.searchFeedList[payload.index] = payload.feedInfo
    },

    SEARCH_FEEDS_DELETE(state, index) {
      state.searchFeedList.splice(index, 1)
    }
  },

  actions: {
    // 피드 조회해서 저장하기
    async setFeedInfo({ commit }, payload) {
      return await axios
        .get(`/feed/feeds/${payload.email}/temp`, {
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

    // 검색해서 보여줄 좋아요 피드
    async setSearchFeeds({commit}, feedList) {
      commit("SET_SEARCH_FEEDS", feedList)
    },

    // 검색 피드 갱신
    updateSearchFeeds({commit}, payload) {
      axios({
        method: "get",
        url: `/feed/feeds/${payload.feedId}`,
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        commit("UPDATE_SEARCH_FEEDS", {feedInfo : res.data, index : payload.index});
      });
    },

    // 검색 피드 삭제
    searchFeedsDelete({commit}, payload) {
      console.log(payload)
      axios({
        method: "delete",
        url: `/feed/feeds/${payload.feedId}`,
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then(() => {
        alert("삭제 성공!")
        commit("SEARCH_FEEDS_DELETE", payload.index)
      })
      .catch((err) => {
        console.log(err)
        alert("삭제 실패!");
      });
    },
  },
};
