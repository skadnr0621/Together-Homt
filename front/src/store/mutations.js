export default {
  mutations: {
    SET_MY_INFO(state, myInfo) {
      state.myInfo.email = myInfo.email;
      state.myInfo.group = myInfo.group;
      state.myInfo.introduce = myInfo.introduce;
      state.myInfo.password = myInfo.password;
      state.myInfo.profile_url = myInfo.profile_url;
      state.myInfo.role = myInfo.role;
      state.myInfo.user_id = myInfo.user_id;
      state.myInfo.username = myInfo.username;
    },
    SET_MY_FEEDS(state, feeds) {
      state.myFeeds = feeds;
    },
    SET_MY_COMMENTS(state, comments) {
      state.myComments = comments;
    },
    SET_MY_FOLLOWS(state, follower, following) {
      state.myFeeds.follower = follower;
      state.myFeeds.following = following;
    },
    SET_MY_RECORDS(state, records) {
      state.myRecords = records;
    },
    SET_MY_LIKES(state, likes) {
      state.myLikes = likes;
    },
  },
};
