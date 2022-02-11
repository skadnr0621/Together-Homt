<template>
  <div id="profile-feed-list">
    <div v-if="feedList.feedCnt == 0">피드 없음</div>
    <div
      v-else
      v-for="(feed, index) in feedList.feeds"
      :key="index"
      class="feed"
    >
      <img
        :src="feed.media_url"
        alt="피드사진"
        @click="viewDetailFeed(index, feed.feed_id)"
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
