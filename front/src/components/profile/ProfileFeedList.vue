<template>
  <div id="profile-feed-list">
    <div v-if="feedList == null">피드 없음</div>
    <div v-else v-for="(feed, index) in feedList" :key="index" class="feed">
      <video
        v-if="isVideo(feed.mediaUrl)"
        :src="feed.mediaUrl"
        @click="viewDetailFeed(index, feed.feedId)"
        alt="피드동영상"
      />
      <img
        v-else
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
    // 확장자 체크하기
    isVideo(fileName) {
      let fileLength = fileName.length;
      let lastDot = fileName.lastIndexOf(".");

      console.log(fileName.substring(lastDot + 1, fileLength) == "mp4");
      if (fileName.substring(lastDot + 1, fileLength) == "mp4") return true;
      else return false;
    },

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
