<template>
  <div id="user-follow-page">
    <follow-header v-on:goBack="onGoBack" />
    <follow-navbar />
    <follow-list />
  </div>
</template>

<script>
import FollowHeader from "@/components/follow/FollowHeader";
import FollowNavbar from "@/components/follow/FollowNavbar";
import FollowList from "@/components/follow/FollowList";

export default {
  name: "FollowPage",
  components: {
    FollowHeader,
    FollowNavbar,
    FollowList,
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
  },
};
</script>

<style>
#user-follow-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
