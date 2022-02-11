<template>
  <div id="search" Search>
    <router-link
      :to="{
        name: 'Profile',
        params: { userName: '앙몬드', email: 'collcr@naver.com' },
      }"
    >
      이동
    </router-link>
  </div>
</template>

<script>
import "@/assets/css/search.css";

export default {
  name: "Search",
};
</script>

<style></style>
