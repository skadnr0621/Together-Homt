<template>
  <div class="admin" id="profile">
    <profile-info
        :info="{ userName: myInfo.username, profileUrl: myInfo.profileUrl }"
      />

    <button @click="makeGroup()">그룹 나누기</button>
    <div>관리자계정 입니다</div>

    <profile-feed-navbar />
    <profile-feed-list
        :feedList="userFeeds"
        :userName="myInfo.username"
        :email="myInfo.email"
      />
  </div>
</template>

<script>
// import "@/components/css/profile.css";
import ProfileInfo from "@/components/profile/ProfileInfo";
import ProfileFeedNavbar from "@/components/profile/ProfileFeedNavbar";
import ProfileFeedList from "@/components/profile/ProfileFeedList";

import { mapState, mapActions } from "vuex";

export default {
  name: 'AdminPage',
  components: {
    ProfileInfo,
    ProfileFeedNavbar,
    ProfileFeedList,
  },
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
    }
  },
  computed: {
    // 로그인한 유저
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
    // 내 정보
    ...mapState(["myInfo", "userFeeds"]),
  },

  methods: {
    // 내 정보 조회, 내 피드 조회
    ...mapActions([
      "setMyInfo",
      "setUserFeeds",
    ]),

    makeGroup() {
      this.$router.push({ name: 'Group' })
    }
  },

  mounted() {
    this.setMyInfo({ email: 'admin@admin', token: this.token });
    this.setUserFeeds({ email: 'admin@admin', token: this.token })
  }
}
</script>

<style>

</style>