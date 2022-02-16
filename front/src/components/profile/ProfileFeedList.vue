<template>
  <div id="profile-feed-list">
    <div v-if="feedList == null">피드 없음</div>
    <div v-else v-for="(feed, index) in feedList" :key="index" class="feed">
      <img
        :src="feed.mediaUrl"
        alt="피드사진"
        @click="viewDetailFeed(index, feed.feedId)"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileFeedList",
  props: ["feedList", "userName", "email"],
  methods: {
    viewDetailFeed(index, feedId) {
      this.$router.push({
        name: "ProfileFeedDetail",
        params: {
          feedId: feedId,
          userName: this.userName,
          email: this.email,
          index: index,
        },
      });
    },
  },
};
</script>

<style></style>
