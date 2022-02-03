export default {
  state: {
    // 나의 정보
    myInfo: {
      email: null,
      group: null,
      introduce: null,
      password: null,
      profile_url: null,
      role: null,
      user_id: null,
      username: null,
    },

    // 나의 피드 정보
    myFeeds: [],

    // 나의 댓글 정보
    myComments: [],

    // 나의 팔로우/팔로워 정보
    myFollows: {
      follower: [],
      following: [],
    },

    // 나의 운동 기록 정보
    myRecords: [],

    // 나의 좋아요 정보
    myLikes: [],
  },
};
