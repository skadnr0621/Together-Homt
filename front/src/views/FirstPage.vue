<template>
  <div>
    <h1>FirstPage</h1>
    <!-- 로그인, 회원가입 컴포넌트 -->
    <router-link :to="{ name: 'Feed' }">메인페이지이동</router-link>
  </div>
</template>

<script>
export default { name: "FirstPage" };
</script>

<style></style>
