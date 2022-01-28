<template>
  <div id="user-page">
    <user-profile :nickname="myInfo.username" />

    <!-- 필요한 데이터 : 프로필사진, 닉네임-->
    <user-data-cnt />

    <!-- 필요한 데이터 : 프로필사진, 닉네임-->
    <user-profile-control />

    <!-- 필요한 데이터 : 자기소개글 -->
    <user-introduction />

    <!-- 필요한 데이터 : 프로필사진, 닉네임-->
    <user-feed-navbar />

    <!-- 필요한 데이터 : 피드 -->
    <user-feed-list :feeds="myInfo.feeds" />
  </div>
</template>

<script>
import UserProfile from "@/components/userPage/UserProfile";
import UserIntroduction from "@/components/userPage/UserIntroduction";
import UserDataCnt from "@/components/userPage/UserDataCnt";
import UserProfileControl from "@/components/userPage/UserProfileControl";
import UserFeedNavbar from "@/components/userPage/UserFeedNavbar";
import UserFeedList from "@/components/userPage/UserFeedList";

import { mapState } from "vuex";

export default {
  name: "UserPage",
  components: {
    UserProfile,
    UserIntroduction,
    UserDataCnt,
    UserProfileControl,
    UserFeedNavbar,
    UserFeedList,
  },
  data() {
    return {
      nickname: this.$route.params.userName,
    };
  },
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  updated() {},
};
</script>

<style>
#user-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
