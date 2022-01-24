<template>
  <div id="user-page">
    <user-profile />
    <user-data-cnt />
    <user-profile-control />
    <user-introduction />
    <user-feed-navbar />
    <user-feed-list />
  </div>
</template>

<script>
import UserProfile from "@/components/user/UserProfile";
import UserIntroduction from "@/components/user/UserIntroduction";
import UserDataCnt from "@/components/user/UserDataCnt";
import UserProfileControl from "@/components/user/UserProfileControl";
import UserFeedNavbar from "@/components/user/UserFeedNavbar";
import UserFeedList from "@/components/user/UserFeedList";

export default {
  name: "UserPage",
  components: {
    UserProfile,
    UserIntroduction,
    UserDataCnt,
    UserProfileControl,
    UserFeedNavbar,
    UserFeedList,
  },
};
</script>

<style>
#user-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
