<template>
  <div>
    <Header />
    <router-view />
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

<style></style>
