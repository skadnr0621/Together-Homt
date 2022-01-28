<template>
  <div id="user-edit-footer">
    <a @click="deleteUser()">회원 탈퇴</a>
    <router-link
      :to="{ name: 'ChangePasswordPage', params: { userName: nickname } }"
      >비밀번호 변경</router-link
    >
    <a @click="logout()">로그아웃</a>
  </div>
</template>

<script>
import { mapState } from "vuex";

import axios from "axios";

export default {
  name: "UserEditFooter",
  props: ["nickname"],
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
  },
  methods: {
    logout() {
      if (confirm("로그아웃 하시겠습니까?")) {
        sessionStorage.clear();
        this.$store.dispatch("userStore/getLoginUser", null);
        this.$router.push({ name: "Login" });
      }
    },
    async deleteUser() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        // 탈퇴하기
        await axios
          .delete(`/user/${this.loginUser}`, {
            headers: {
              Authorization: sessionStorage.getItem("jwt"),
            },
          })
          .then((res) => {
            alert("그동안 서비스를 이용해주셔서 감사합니다.");
            console.log(res);

            sessionStorage.clear(); // 토큰 삭제
            this.$store.dispatch("userStore/getLoginUser", null); // LocalStorage 초기화

            this.$router.push({ name: "Login" });
          })
          .catch((err) => {
            console.log(err);
            alert("회원탈퇴를 실패하였습니다.");
          });
      }
    },
  },
};
</script>

<style>
#user-edit-footer {
  position: fixed;
  bottom: 45px;
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin: 10px 0;
}

#user-edit-footer > * {
  margin-right: 10px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.38);
  /* font-weight: bold; */
}

#user-edit-footer > a:first-child {
  float: left;
}
</style>
