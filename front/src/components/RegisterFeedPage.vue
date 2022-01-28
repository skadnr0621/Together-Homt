<template>
  <div id="register-feed-page">
    피드 등록 페이지
    <register-feed-header />
    <register-feed />
    <register-feed-tag />
    <register-feed-setting />
  </div>
</template>

<script>
import RegisterFeedHeader from "@/components/RegisterFeedPage/RegisterFeedHeader";
import RegisterFeed from "@/components/RegisterFeedPage/RegisterFeed";
import RegisterFeedTag from "@/components/RegisterFeedPage/RegisterFeedTag";
import RegisterFeedSetting from "@/components/RegisterFeedPage/RegisterFeedSetting";

export default {
  name: "RegisterFeedPage",
  components: {
    RegisterFeedHeader,
    RegisterFeed,
    RegisterFeedTag,
    RegisterFeedSetting,
  },
  data() {
    return {};
  },
  methods: {},
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
