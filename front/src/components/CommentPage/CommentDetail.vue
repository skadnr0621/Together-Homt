<template>
  <div id="comment-detail">
    <!-- 댓글 적기 -->
    <div class="comment-register">
      <div><img :src="myInfo.profile_url" alt="사용자프로필" /></div>
      <div>
        <input
          type="text"
          placeholder="댓글 달기..."
          v-model="comment"
          @keyup.enter="registerComment()"
        />
        <span class="material-icons" @click="registerComment()">
          play_circle_filled
        </span>
      </div>
    </div>
    <!-- 피드 게시글 -->
    <div class="feed-content">
      <div>
        <div><img :src="myInfo.profile_url" alt="사용자프로필" /></div>
        <div>{{ myInfo.username }}</div>
      </div>

      <div>{{ myInfo.feeds[feedId].content }}</div>
      <div>
        {{ myInfo.feeds[feedId].createdAt[0] }}년
        {{ myInfo.feeds[feedId].createdAt[1] }}월
        {{ myInfo.feeds[feedId].createdAt[2] }}일
      </div>
    </div>

    <!-- 피드 댓글들 -->
    <div class="feed-comment">
      <div v-if="myInfo.feeds[feedId].comments.length != 0">
        {{ myInfo.feeds[feedId].comments }}
      </div>
    </div>
    <!-- {{ myInfo.feeds[feedId].comments }} -->
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "CommentDetail",
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  props: ["feedId"],
  data() {
    return {
      comment: "",
    };
  },
  methods: {
    registerComment() {
      this.$emit("registerComment", this.comment);
      this.comment = "";
    },
  },
};
</script>

<style>
#comment-detail {
  display: flex;
  flex-direction: column;
}

/* 댓글 적기 */
#comment-detail > .comment-register {
  align-items: center;
  display: flex;
  height: 50px;
  background-color: rgba(0, 0, 0, 0.025);
  padding: 0 10px;
}

#comment-detail > .comment-register > div:first-child {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#comment-detail > .comment-register > div > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#comment-detail > .comment-register > div:nth-child(2) {
  display: flex;
  align-items: center;
  margin-left: 10px;
  flex: 1 1 auto;
}

#comment-detail > .comment-register > div > input {
  /* width: 90%; */
  height: 30px;
  border: 1px solid rgba(0, 0, 0, 0.38);
  border-radius: 30px;
  padding: 0 10px;
  color: rgba(0, 0, 0, 0.87);
  font-size: 14px;
  flex: 1 1 auto;
  margin-right: 5px;
}

#comment-detail > .comment-register > div > span {
  display: inline-block;
  font-size: 24px;
  text-align: center;
  color: rgba(0, 0, 0, 0.38);
  /* color: rgba(0, 0, 0, 0.87); */
}

#comment-detail > .feed-content {
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.025);
}

#comment-detail > .feed-content > div {
  display: flex;
  align-items: center;
}

#comment-detail > .feed-content > div:nth-child(1) > div:nth-child(1) {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin-right: 10px;
}

#comment-detail > .feed-content > div:nth-child(1) > div:nth-child(2) {
  font-size: 15px;
  font-weight: bold;
}

#comment-detail > .feed-content > div > div > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#comment-detail > .feed-content > div:nth-child(2) {
  margin: 5px 0 10px 50px;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.87);
}

#comment-detail > .feed-content > div:nth-child(3) {
  margin-left: 50px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.38);
}

#comment-detail > .feed-comment {
  display: flex;
  flex-direction: column;
  padding: 0 10px;
}

#comment-detail > .feed-comment > div {
  margin: 10px 0;
  color: rgba(0, 0, 0, 0.87);
  font-size: 15px;
}
</style>
