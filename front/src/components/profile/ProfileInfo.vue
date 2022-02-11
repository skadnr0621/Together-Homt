<template>
  <div id="profile-info">
    <div class="profile">
      <img v-if="info.profileUrl" :src="pictureUrl()" alt="유저 프로필 사진" />
      <img
        v-else
        src="https://cdn.pixabay.com/photo/2022/01/29/08/40/basic-6976744_960_720.png"
        alt="프로필 사진"
      />
    </div>
    <div class="name">@{{ info.userName }}</div>
  </div>
</template>

<script>
export default {
  name: "ProfileInfo",
  props: ["info"],
  methods: {
    pictureUrl() {
      console.log(this.info.profileUrl);
      return "http://localhost:8092" + this.info.profileUrl;
    },
  },
};
</script>

<style></style>
