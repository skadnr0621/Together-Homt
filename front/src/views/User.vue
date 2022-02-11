<template>
  <div id="user">
    <!-- 로그인 : Login.vue -->
    <!-- 회원가입 : Singup.vue -->
    <!-- 비밀번호 찾기 -->
    <router-view />
  </div>
</template>

<script>
import "@/assets/css/user.css";

export default {
  name: "User",
};
</script>

<style></style>
