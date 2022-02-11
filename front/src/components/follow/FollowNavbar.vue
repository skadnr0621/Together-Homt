<template>
  <div id="follow-navbar">
    <ul>
      <li class="following" @click="goFollowing">
        {{ info.cntFollowing }} 팔로잉
      </li>
      <li class="follower" @click="goFollower">
        {{ info.cntFollower }} 팔로워
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "FollowNavbar",
  props: ["info"],
  methods: {
    goFollowing() {
      this.$emit("goFollowing");
    },
    goFollower() {
      this.$emit("goFollower");
    },
  },
  async mounted() {
    // 스타일 적용
    if (this.$route.params.follow == "following") {
      document.querySelector(
        `#follow-navbar > ul > .following`
      ).style.borderBottom = "1px solid rgba(0, 0, 0, 0.87)";
      document.querySelector(`#follow-navbar > ul > .following`).style.color =
        "rgba(0, 0, 0, 0.87)";
      document.querySelector(
        `#follow-navbar > ul > .following`
      ).style.fontWeight = "bold";

      document.querySelector(
        `#follow-navbar > ul > .follower`
      ).style.borderBottom = "1px solid rgba(0, 0, 0, 0.1)";
      document.querySelector(`#follow-navbar > ul > .follower`).style.color =
        "rgba(0, 0, 0, 0.38)";
      document.querySelector(
        `#follow-navbar > ul > .follower`
      ).style.fontWeight = "normal";
    } else {
      document.querySelector(
        `#follow-navbar > ul > .follower`
      ).style.borderBottom = "1px solid rgba(0, 0, 0, 0.87)";
      document.querySelector(`#follow-navbar > ul > .follower`).style.color =
        "rgba(0, 0, 0, 0.87)";
      document.querySelector(
        `#follow-navbar > ul > .follower`
      ).style.fontWeight = "bold";

      document.querySelector(
        `#follow-navbar > ul > .following`
      ).style.borderBottom = "1px solid rgba(0, 0, 0, 0.1)";
      document.querySelector(`#follow-navbar > ul > .following`).style.color =
        "rgba(0, 0, 0, 0.38)";
      document.querySelector(
        `#follow-navbar > ul > .following`
      ).style.fontWeight = "normal";
    }
  },
};
</script>

<style></style>
