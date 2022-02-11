<template>
  <div class="user" id="signup">
    <!-- 회원가입 로고 -->
    <div class="logo">회원 가입</div>

    <!-- 닉네임 -->
    <div>
      <div class="input-with-label" id="short">
        <label for="nickname">닉네임</label>
        <input
          class="signup-input-data"
          v-model="user.username"
          id="nickname"
          placeholder="닉네임을 입력하세요."
          type="text"
        />
      </div>

      <!-- 이메일 -->
      <div class="input-with-label" id="short">
        <label for="email">이메일</label>

        <input
          v-model="user.email"
          class="signup-input-data"
          id="email"
          placeholder="이메일을 입력해주세요."
          type="text"
        />
      </div>

      <!-- 인증번호 -->
      <div class="input-with-label">
        <label for="input-confirmnum">인증번호 입력</label>
        <input
          class="signup-input-data"
          v-model="inputConfirmNum"
          id="input-confirmnum"
          placeholder="인증번호를 입력하세요."
          type="text"
        />
        <div v-if="isSendNum">
          <!-- 확인 -->
          <button
            class="send-confirmNum"
            @click="emailConfirm()"
            id="input-confirmnum-btn"
          >
            <p class="font-scale-8">확인</p>
          </button>
          <!-- 재전송 -->
        </div>
        <div v-else>
          <button class="send-confirmNum" @click="sendConfirm()">
            <p class="font-scale-8">인증번호 받기</p>
          </button>
        </div>
      </div>

      <!-- 비밀번호 -->
      <div class="input-with-label" id="short">
        <input
          class="signup-input-data"
          v-model="user.password"
          id="password"
          placeholder="비밀번호를 입력하세요.(영문 + 숫자 6자리)"
          type="text"
        />
        <label for="password">비밀번호</label>
      </div>

      <!-- 비밀번호 확인 -->
      <div class="input-with-label">
        <input
          class="signup-input-data"
          v-model="passwordConfirm"
          type="text"
          id="passwordConfirm"
          placeholder="비밀번호를 다시한번 입력하세요."
        />
        <label for="passwordConfirm">비밀번호 확인</label>
      </div>
    </div>

    <!-- 이용약관 -->
    <div class="checkbox">
      <input type="checkbox" id="checkbox" v-model="checked" />
      <label for="checkbox">
        <strong>이용약관</strong> 및 <strong>개인정보 처리방침</strong>에
        동의합니다.
      </label>
    </div>

    <!-- 회원가입 -->
    <div v-if="isSubmit">
      <button class="user-btn" @click="Signup()">가입 하기</button>
    </div>
    <div v-else>
      <button class="user-btn-reject">가입 하기</button>
    </div>

    <!-- 로그인 -->
    <div class="end-text-signup">
      <span>이미 계정이 있으신가요?</span>
      <router-link :to="{ name: 'Login' }" class="link-go-signup">
        <strong>로그인</strong>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Signup",
  data: function () {
    return {
      user: {
        username: "",
        email: "",
        password: "",
      },
      inputConfirmNum: "", // 인증번호 입력
      // emailConfirmNum: "",
      isSendNum: false,
      isConfirm: false,

      passwordConfirm: null,
      // ispasswordConfirm: false,

      isSubmit: false, // true: 버튼 활성화
      // passwordType: "password",
      checked: false,
    };
  },

  methods: {
    // 회원가입
    Signup: function () {
      console.log(this.user);
      axios({
        method: "post",
        url: `/user/signup`,
        data: this.user,
      })
        .then(() => {
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          alert(err);
        });
    },

    // 이메일 인증 발송
    sendConfirm: function () {
      this.isSendNum = true;
      axios({
        method: "post",
        url: `/user/signup/confirm`,
        data: this.user.email,
        headers: { "Content-Type": "text/plain" },
      })
        .then((res) => {
          this.isSendNum = true;

          console.log(res.data);
          this.emailConfirmNum = res.data;
        })
        .catch((err) => {
          alert(err);
        });
    },

    // 인증번호 확인
    emailConfirm: function () {
      if (this.inputConfirmNum == this.emailConfirmNum) {
        console.log("true");
        const confirmNumInputBox = document.querySelector("#input-confirmnum");
        const confirmNumInputBtn = document.querySelector(
          "#input-confirmnum-btn"
        );
        confirmNumInputBox.setAttribute("disabled", true);
        confirmNumInputBtn.setAttribute("disabled", true);
        this.isConfirm = true;
      } else {
        console.log(this.inputConfirmNum);
        console.log(this.emailConfirmNum);
      }
    },

    // 비밀번호와 비밀번호 확인 일치 확인
    checkForm: function () {
      if (this.passwordConfirm === this.user.password) {
        this.ispasswordConfirm = true;
        if (this.user.username && this.isConfirm) {
          this.isSubmit = true;
        }
      } else {
        this.isSubmit = false;
      }
    },
  },

  watch: {
    passwordConfirm: function () {
      this.checkForm();
    },
  },
};
</script>

<style></style>
