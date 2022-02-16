<template>
  <div class="admin" id="profile">
    <profile-info
        :info="{ userName: myInfo.username, profileUrl: myInfo.imagePath }"
      />
    <div id="admin-edit">
      <button @click="$router.push({ name: 'Group' })">그룹 나누기</button>
      <button @click="$router.push({ name : 'AdminRegister'})">미퇴실 명단</button>
      <profile-edit-control />
    </div>
    <div>관리자계정 입니다</div>
    <div id="admin-feeds-line">미퇴실명단 피드</div>
    <profile-feed-list
        :feedList="userFeeds"
        :userName="myInfo.username"
        :email="myInfo.email"
      />
  </div>
</template>

<script>
import "@/assets/css/admin.css";
import ProfileInfo from "@/components/profile/ProfileInfo";
import ProfileFeedList from "@/components/profile/ProfileFeedList";
import ProfileEditControl from "@/components/profile/ProfileEditControl";
import axios from 'axios'
import { mapState, mapActions } from "vuex";

export default {
  name: 'AdminPage',
  components: {
    ProfileInfo,
    ProfileFeedList,
    ProfileEditControl
  },
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      userFeeds: null,
    }
  },
  computed: {
    // 로그인한 유저
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
    // 내 정보
    ...mapState({ myInfo: (state) => state.myStore.myInfo}),
  },

  methods: {
    // 내 정보 조회, 내 피드 조회
    ...mapActions([
      "setMyInfo",
    ]),

    adminFeeds() {
      axios({
        method: "get",
        url: `/feed/feeds/profiles/${this.myInfo.email}/temp`,
        headers: {
          Authorization : sessionStorage.getItem("jwt")
        }
      })
      .then((res)=> {
        console.log('admin 게시글'+res.data)
        this.userFeeds = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
  },

  mounted() {
    this.setMyInfo({ email: this.loginUser, token: this.token });
    this.adminFeeds()
  }
}
</script>
