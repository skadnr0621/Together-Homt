<template>
  <div id="change-password-page">
    <div class="header">
      <div class="cancel" v-on:click="goBack()">취소</div>
      <div class="context">비밀번호 변경</div>
      <div class="done" v-on:click="changePassword()">완료</div>
    </div>

    <div class="info">
      <div class="current-pw">
        <label>현재 비밀번호</label>
        <div>
          <input type="text" v-model="currentPassword" />
          <span class="material-icons"> {{ icon }} </span>
        </div>
      </div>
      <div class="new-pw">
        <label>새 비밀번호</label>
        <input type="text" v-model="newPassword" />
        <span class="material-icons"> {{ icon }} </span>
      </div>
      <div class="confirm-pw">
        <label>새 비밀번호 확인</label>
        <input type="text" v-model="confirmPassword" />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

import axios from "axios";

export default {
  name: "ChangePasswordPage",
  data() {
    return {
      currentPassword: null,
      newPassword: null,
      confirmPassword: null,
      icon: "visibility_off",
    };
  },
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),

    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
  },
  updated() {},
  methods: {
    goBack() {
      this.$router.back();
    },
    async changePassword() {
      console.log(this.newPassword);
      console.log(this.confirmPassword);
      // 현재 비밀번호 맞는지 확인하는 로직 안넣음

      // 새 비밀번호 = 새 비밀번호 확인
      if (this.newPassword == this.confirmPassword) {
        console.log("비밀번호 바꾸자~");
        // 비밀번호 변경하기
        await axios
          .put(`/user/${this.loginUser}/passwordUpdate`, {
            headers: {
              Authorization: sessionStorage.getItem("jwt"),
            },
          })
          .then((res) => {
            console.log(res);
            alert("비밀번호가 변경되었습니다.");
            this.goBack();
          })
          .catch((err) => {
            console.log(err);
            alert("비밀번호 변경을 실패하였습니다.");
          });
      } else {
        alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
      }
    },
  },
};
</script>

<style>
#change-password-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}

#change-password-page > .header {
  display: flex;
  height: 40px;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  padding: 0 10px;
}

#change-password-page > .header > .cancel,
.done {
  font-size: 15px;
}

#change-password-page > .header > .done {
  color: rgba(41, 98, 255, 0.86);
}

#change-password-page > .header > .context {
  font-weight: bold;
}

#change-password-page > .info {
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 10px 15px;
}

#change-password-page > .info > div {
  padding: 5px 0;
  width: 100%;
}

#change-password-page > .info > div > * {
  margin: 3px 0;
}

/* #change-password-page > .info > div > label {
  display: inline-block;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
} */

#change-password-page > .info > div > input {
  font-size: 14px;
  width: 100%;
  height: 30px;
  display: inline-block;
  border: 1px solid rgba(0, 0, 0, 0.1);
  padding-left: 10px;
  color: rgba(0, 0, 0, 0.87);
  border-radius: 2px;
}

#change-password-page > .info > div > .material-icons {
  font-size: 14px;
}
</style>
