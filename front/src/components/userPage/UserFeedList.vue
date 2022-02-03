<template>
  <div id="user-feed-list">
    <div v-if="myInfo.feeds.length == 0">피드 없음</div>
    <div v-else v-for="(feed, index) in myInfo.feeds" :key="index" class="feed">
      <img
        :src="feed.media_url"
        alt="피드사진"
        @click="viewDetailFeed(index)"
      />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserFeedList",
  // props: ["feeds"],
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  data() {
    return {
      // feed: ["feed1", "feed2", "feed3", "feed4", "feed5"],
    };
  },
  methods: {
    viewDetailFeed(index) {
      this.$router.push({
        name: "FeedDetailPage",
        params: {
          feedId: index,
          userName: this.myInfo.username,
        },
      });
    },
  },
};
</script>

<style>
#user-feed-list {
  margin: 2px 0;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  width: 100%;
  gap: 1px 1px;
}

#user-feed-list > .feed {
  border: 1px solid rgba(0, 0, 0, 0.1);
  aspect-ratio: 1;
}

#user-feed-list > .feed > img {
  width: 100%;
  height: 100%;
}
</style>
