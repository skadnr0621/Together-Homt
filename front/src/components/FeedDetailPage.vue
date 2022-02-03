<template>
  <div id="feed-detail-page">
    <feed-detail-header v-on:goBack="onGoBack" />
    <feed-detail
      :feedId="this.$route.params.feedId"
      v-on:goLikeList="onGoLikeList"
      v-on:goComment="onGoComment"
      v-on:deleteFeed="onDeleteFeed"
      v-on:likeFeed="onLikeFeed"
    />
  </div>
</template>

<script>
import FeedDetailHeader from "@/components/FeedDetailPage/FeedDetailHeader";
import FeedDetail from "@/components/FeedDetailPage/FeedDetail";
import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "FeedDetailPage",
  components: {
    FeedDetailHeader,
    FeedDetail,
  },
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
    onGoLikeList() {
      // this.$router.push({ name: "LikeListPage", params: {feedId : feedDetail.feed_id}});
    },
    onGoComment() {
      this.$router.push({
        name: "CommentPage",
        params: {
          feedId: this.$route.params.feedId,
          userName: this.myInfo.username,
        },
      });
    },
    onDeleteFeed() {
      const feedId = this.myInfo.feeds[this.$route.params.feedId].feed_id;

      // 피드 삭제하기
      axios
        .delete(
          `/feed/${feedId}`,
          { feed_id: feedId },
          {
            headers: {
              Authorization: sessionStorage.getItem("jwt"),
            },
          }
        )
        .then((res) => {
          alert("게시글 삭제 완료!");
          console.log(res);
        })
        .catch((err) => {
          alert("게시글 삭제 실패!");
          console.log(err);
        });

      this.$router.push({
        name: "UserPage",
        params: {
          userName: this.myInfo.username,
        },
      });
    },
    onLikeFeed() {
      const feedId = this.myInfo.feeds[this.$route.params.feedId].feed_id;

      // 피드 좋아요 누르기
      axios
        .post(
          `/like/${feedId}`,
          { feed_id: feedId },
          {
            headers: {
              Authorization: sessionStorage.getItem("jwt"),
            },
          }
        )
        .then((res) => {
          alert("좋아요 누르기 완료!");
          console.log(res);
        })
        .catch((err) => {
          alert("좋아요 누르기 실패!");
          console.log(err);
        });

      this.$router.go(this.$router.currentRoute);
    },
  },
};
</script>

<style>
#feed-detail-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.025);
}
</style>
