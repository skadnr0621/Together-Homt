<template>
  <div id="search" Search>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: '죠르디', email: 'collcr@kakao.com' },
        }"
      >
        이동
      </router-link>
    </div>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: '앙몬드', email: 'collcr@naver.com' },
        }"
      >
        이동
      </router-link>
    </div>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: 'aaa', email: 'aaa@aaa.com' },
        }"
      >
        이동
      </router-link>
    </div>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: 'bbb', email: 'bbb@bbb.com' },
        }"
      >
        이동
      </router-link>
    </div>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: 'ccc', email: 'ccc@ccc.com' },
        }"
      >
        이동
      </router-link>
    </div>
    <div>
      <router-link
        :to="{
          name: 'Profile',
          params: { userName: 'ddd', email: 'ddd@ddd.com' },
        }"
      >
        이동
      </router-link>
    </div>
  </div>
</template>

<script>
import "@/assets/css/search.css";

export default {
  name: "Search",
};
</script>

<style></style>
