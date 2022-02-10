<template>
  <div class="user" id="login">
    <div class="logo">투게더 홈트</div>

    <div>
      <input
        class="login-input-data form-control"
        v-model="user.email"
        placeholder="이메일을 입력해주세요."
        type="text"
      />
    </div>

    <div>
      <input
        class="login-input-data form-control"
        v-model="user.password"
        placeholder="비밀번호를 입력해주세요."
        type="password"
      />
    </div>

    <button class="user-btn" @click="Login()">로그인</button>

    <div class="find-password-box">
      <router-link :to="{ name: 'FindPW' }" class="link-find-password"
        >비밀번호 찾기</router-link
      >
    </div>

    <div class="social-login">
      <FacebookLogin />
      <kakaoLogin />
      <GoogleLogin />
    </div>

    <div class="end-text-signup">
      <span>아직 <strong>투게더 홈트</strong> 아이디가 없으신가요?</span>
      <router-link :to="{ name: 'Signup' }" class="link-go-signup"
        ><strong>회원가입</strong></router-link
      >
    </div>
  </div>
</template>

<script>
import "@/components/css/user.css";
import FacebookLogin from "@/components/user/snsLogin/Facebook.vue";
import KakaoLogin from "@/components/user/snsLogin/Kakao.vue";
import GoogleLogin from "@/components/user/snsLogin/Google.vue";
import axios from "axios";

export default {
  components: {
    FacebookLogin,
    KakaoLogin,
    GoogleLogin,
  },

  data: function () {
    return {
      user: {
        email: 'abc@abc',
        password: '123456789',
      },
      errForm: {
        email: null,
        password: null,
      },
    };
  },

  methods: {
    Login: function () {
      axios({
        method: "post",
        url: `/user/login`,
        data: this.user,
      })
        .then((res) => {
          const token = res.headers.authorization;

          sessionStorage.setItem("jwt", token);
          this.$store.dispatch("userStore/getLoginUser", this.user.email); //로그인 응답으로 username 받음

          // this.$store.dispatch("setMyInfo");
          this.$router.push({ name: "Feed" });
        })
        .catch((err) => {
          // 어떤 에러냐에 따라 문구 변경
          alert(err);
          this.user = this.errForm;
        });
    },
  },
};
</script>

<style></style>
