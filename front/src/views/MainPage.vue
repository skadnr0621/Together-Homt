<template>
  <div id="main-page">
    <!-- 헤더 컴포넌트 -->
    <Header />

    <!-- 메인 라우터 : 피드 페이지, 유저 페이지 -->
    <router-view />

    <!-- 네비게이션바 컴포넌트 -->
    <Navbar />
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Navbar from "@/components/common/Navbar";

export default {
  name: "MainPage",
  components: {
    Header,
    Navbar,
  },
};
</script>

<style>
#main-page {
  /* padding-bottom: 19px; */
  width: 735px;
}
</style>
