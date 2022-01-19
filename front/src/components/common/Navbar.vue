<template>
  <div>
    <h1>Navbar</h1>
    <router-link :to="{ name: 'Feed' }">피드 이동</router-link>&nbsp;
    <router-link :to="{ name: 'MyPage' }">마이페이지 이동</router-link>
  </div>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style></style>
