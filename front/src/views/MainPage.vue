<template>
  <div id="main-page">
    <!-- jwt 토큰을 가지고 있을 경우 -->
    <div v-if="isAuthorized">
      <!-- 헤더 컴포넌트 -->
      <Header />

      <!-- 메인 라우터 : 피드 페이지, 유저 페이지 -->
      <router-view />

      <!-- 네비게이션바 컴포넌트 -->
      <Navbar />
    </div>

    <!-- jwt 토큰을 가지고 있지 않을 경우 -->
    <!-- <div v-else>{{ logout() }}</div> -->
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Navbar from "@/components/common/Navbar";
import { mapState, mapActions } from "vuex";

export default {
  name: "MainPage",
  components: {
    Header,
    Navbar,
  },
  data() {
    return {
      isAuthorized: sessionStorage.getItem("jwt"),
    };
  },
  methods: {
    // 유저 정보 조회
    ...mapActions(["setAllMyInfo"]),
    logout() {
      sessionStorage.clear();
      this.$store.dispatch("userStore/getLoginUser", null);
      this.$router.push({ name: "Login" });
    },
  },
  mounted() {
    //this.setAllMyInfo(this.loginUser);
  },
  updated() {
    // jwt 토큰 여부 확인
    this.isAuthorized = sessionStorage.getItem("jwt");
    // this.setMyInfo(this.loginUser); //받아오면서 에러
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 로그인한 유저 정보
    // ...mapState(["myInfo"]),
  },
};
</script>

<style>
#main-page {
  /* padding-bottom: 19px; */
  width: 735px;
}
</style>
