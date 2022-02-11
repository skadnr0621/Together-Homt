<template>
  <div id="follow">
    <!-- {{ $route.params.userName }}
    {{ $route.params.email }} -->
    <div>
      <follow-header v-on:goBack="onGoBack" />

      <follow-navbar
        v-if="loginUser == this.email"
        :info="{
          cntFollowing: myInfo.cntFollowing,
          cntFollower: myInfo.cntFollower,
        }"
        v-on:goFollowing="onGoFollowing"
        v-on:goFollower="onGoFollower"
      />

      <follow-navbar
        v-else
        :info="{
          cntFollowing: userInfo.cntFollowing,
          cntFollower: userInfo.cntFollower,
        }"
        v-on:goFollowing="onGoFollowing"
        v-on:goFollower="onGoFollower"
      />

      <follow-list />
    </div>
  </div>
</template>

<script>
import FollowHeader from "@/components/follow/FollowHeader";
import FollowNavbar from "@/components/follow/FollowNavbar";
import FollowList from "@/components/follow/FollowList";

import { mapState, mapActions } from "vuex";
import "@/assets/css/follow.css";

export default {
  name: "Follow",
  components: {
    FollowHeader,
    FollowNavbar,
    FollowList,
  },
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      name: this.$route.params.userName,
    };
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 나의 정보, 다른 유저의 정보
    ...mapState(["myInfo", "userInfo"]),
  },
  methods: {
    // 나의 정보, 다른 유저의 정보 조회
    ...mapActions(["setMyInfo", "setUserInfo"]),

    // 뒤로 가기
    onGoBack() {
      this.$router.back();
    },

    // 팔로잉 페이지
    onGoFollowing() {
      this.$router.replace({
        name: "Follow",
        params: {
          userName: this.name,
          email: this.email,
          follow: "following",
        },
      });
    },

    // 팔로우 페이지
    onGoFollower() {
      this.$router.replace({
        name: "Follow",
        params: {
          userName: this.name,
          email: this.email,
          follow: "follower",
        },
      });
    },
  },
  async mounted() {
    // 나의 정보 조회하기
    if (this.loginUser == this.email) {
      await this.setMyInfo({
        email: this.email,
        token: this.token,
      });
      console.log("FollowNavbar에서 나의 정보 get요청함!");
    }
    // 유저 정보 조회하기
    else {
      await this.setUserInfo({
        email: this.email,
        token: this.token,
      });
      console.log("FollowNavbar에서 유저 정보 get요청함!");
    }
  },
  watch: {
    $route() {
      this.$router.go();
    },
  },
};
</script>

<style></style>
