<template>
  <div id="register-feed-page">
    <!-- <img
      src="https://cdn.pixabay.com/photo/2014/03/24/13/40/dumbbells-293955_960_720.png"
      alt="기본 이미지"
    /> -->
    <register-feed-header
      v-on:goBack="onGoBack"
      v-on:registerFeed="onRegisterFeed"
    />
    <register-feed :isRegister="isRegister" :nickname="nickname" />
  </div>
</template>

<script>
import RegisterFeedHeader from "@/components/RegisterFeedPage/RegisterFeedHeader";
import RegisterFeed from "@/components/RegisterFeedPage/RegisterFeed";

export default {
  name: "RegisterFeedPage",
  components: {
    RegisterFeedHeader,
    RegisterFeed,
  },
  data() {
    return {
      isRegister: false,
      nickname: this.$route.params.userName,
    };
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
    onRegisterFeed(value) {
      this.isRegister = value;
    },
  },
};
</script>

<style>
#register-feed-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
