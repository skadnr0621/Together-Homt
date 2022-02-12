<template>
  <div id="profile">
    <!-- 내 프로필 -->
    <div v-if="loginUser == email">
      <profile-info
        :info="{ userName: myInfo.username, profileUrl: myInfo.imagePath }"
      />

      <profile-data-cnt
        :info="{
          feedCnt: userFeeds.feedCnt,
          followerCnt: myInfo.cntFollower,
          followingCnt: myInfo.cntFollowing,
          userName: myInfo.username,
          email: myInfo.email,
        }"
      />

      <profile-edit-control />

      <profile-introduction :introduction="myInfo.introduce" />

      <profile-feed-navbar />

      <profile-feed-list
        :feedList="userFeeds"
        :userName="myInfo.username"
        :email="loginUser"
      />
    </div>

    <!-- 다른 유저 프로필 -->
    <div v-else>
      <profile-info
        :info="{ userName: userInfo.username, profileUrl: userInfo.profileUrl }"
      />

      <profile-data-cnt
        :info="{
          feedCnt: userFeeds.feedCnt,
          followerCnt: userInfo.cntFollower,
          followingCnt: userInfo.cntFollowing,
          userName: userInfo.username,
          email: userInfo.email,
        }"
      />

      <profile-follow-control :email="userInfo.email" :isFollow="isFollow" />

      <profile-introduction :introduction="userInfo.introduce" />

      <profile-feed-navbar />

      <profile-feed-list
        :feedList="userFeeds"
        :userName="userInfo.username"
        :email="userInfo.email"
      />
    </div>
  </div>
</template>

<script>
import ProfileInfo from "@/components/profile/ProfileInfo";
import ProfileDataCnt from "@/components/profile/ProfileDataCnt";
import ProfileEditControl from "@/components/profile/ProfileEditControl";
import ProfileFollowControl from "@/components/profile/ProfileFollowControl";
import ProfileIntroduction from "@/components/profile/ProfileIntroduction";
import ProfileFeedNavbar from "@/components/profile/ProfileFeedNavbar";
import ProfileFeedList from "@/components/profile/ProfileFeedList";

import { mapState, mapActions } from "vuex";

import "@/assets/css/profile.css";

export default {
  name: "Profile",
  components: {
    ProfileInfo,
    ProfileDataCnt,
    ProfileEditControl,
    ProfileIntroduction,
    ProfileFeedNavbar,
    ProfileFeedList,
    ProfileFollowControl,
  },
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      isFollow: null,
    };
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 내 정보
    ...mapState(["myInfo", "userInfo", "userFeeds", "userFollower"]),
  },
  methods: {
    // 내 정보 조회, 내 피드 조회
    ...mapActions([
      "setMyInfo",
      "setUserInfo",
      "setUserFeeds",
      "setUserFollower",
    ]),

    // 팔로우 했는지 안했는지 체크
    checkFollow(value) {
      if (value.email == this.loginUser) {
        return true;
      }
      return false;
    },
  },
  watch: {
    $route() {
      this.$router.go();
    },
  },
  async mounted() {
    // 다른 사용자 프로필 조회
    if (this.loginUser != this.email) {
      // 유저 정보 조회
      await this.setUserInfo({
        email: this.email,
        token: this.token,
      });
      console.log("Profile에서 유저 정보 get요청함!");

      // 유저 팔로워 리스트 조회
      await this.setUserFollower({
        email: this.email,
      });
      console.log("Profile에서 유저 팔로워 get요청함!");

      this.isFollow = this.userFollower.findIndex(this.checkFollow);
    }

    // 유저 피드 조회
    await this.setUserFeeds({
      email: this.email,
      token: this.token,
    });
    console.log("Profile에서 유저 피드 get요청함!");

    // 나의 정보 조회
    await this.setMyInfo({ email: this.loginUser, token: this.token });
    console.log("Profile에서 내 정보 get요청함!");
  },
};
</script>

<style></style>
