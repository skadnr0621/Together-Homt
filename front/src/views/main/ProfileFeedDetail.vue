<template>
  <div id="profile-feed-detail">
    <profile-feed-detail-header v-on:goBack="onGoBack" />
    <profile-feed-detail-main />
  </div>
</template>

<script>
import ProfileFeedDetailHeader from "@/components/profile_feed_detail/ProfileFeedDetailHeader";
import ProfileFeedDetailMain from "@/components/profile_feed_detail/ProfileFeedDetailMain";

import "@/assets/css/profileFeedDetail.css";

export default {
  name: "ProfileFeedDetail",
  components: {
    ProfileFeedDetailHeader,
    ProfileFeedDetailMain,
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
  },
};
</script>

<style></style>
