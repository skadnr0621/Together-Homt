<template>
  <div id="feed">
    email : {{ myInfo.email }} <br />
    username : {{ myInfo.username }} <br />
    introduce : {{ myInfo.introduce }} <br />
    profileUrl : {{ myInfo.profileUrl }} <br />
    followers : {{ myInfo.followers }} <br />
    cntFollower : {{ myInfo.cntFollower }} <br />
    followings : {{ myInfo.followings }} <br />
    cntFollowing : {{ myInfo.cntFollowing }}
  </div>
</template>

<script>
import "@/assets/css/feed.css";

import { mapState, mapActions } from "vuex";

export default {
  name: "Feed",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
    };
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 내 정보
    ...mapState(["myInfo"]),
  },
  methods: {
    // 내 정보 조회
    ...mapActions(["setMyInfo"]),
  },
  mounted() {
    // 내 정보 조회

    this.setMyInfo({ email: this.loginUser, token: this.token });
    console.log("Feed에서 내 정보 get요청함!");
  },
};
</script>

<style></style>
