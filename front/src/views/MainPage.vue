<template>
  <div>
    <h1>MainPage</h1>
    <Header />
    <Navbar />
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import Navbar from "@/components/common/Navbar";

export default {
  name: "MainPage",
  components: {
    Header,
    Footer,
    Navbar,
  },
};
</script>

<style></style>
