<template>
  <div id="profile-feed-comment">
    <comment-header v-on:goBack="onGoBack" />
    <comment-register />
    <comment-main />
  </div>
</template>

<script>
import CommentHeader from "@/components/profile_feed_comment/CommentHeader";
import CommentRegister from "@/components/profile_feed_comment/CommentRegister";
import CommentMain from "@/components/profile_feed_comment/CommentMain";

import "@/assets/css/profileFeedComment.css";

export default {
  name: "ProfileFeedComment",
  components: {
    CommentHeader,
    CommentRegister,
    CommentMain,
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
  },
};
</script>

<style></style>
