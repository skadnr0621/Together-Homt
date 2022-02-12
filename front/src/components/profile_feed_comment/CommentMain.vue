<template>
  <div id="comment-main">
    <!-- 피드 게시글 -->
    <div class="feed-content">
      <div>
        <div><img src="" alt="피드작성자프로필" /></div>
        <div>피드작성자이름</div>
      </div>

      <div>피드 내용</div>
      <div>???? 년 ?? 월 ?? 일</div>
    </div>

    <!-- 피드 댓글들 -->
    <div class="feed-comment">
      <div v-for="(comment, index) in feedComments" :key="index">
        <div><img src="" alt="댓글 작성자 프로필" /></div>
        <div>{{ comment.username }}</div>
        <div>{{ comment.content }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "CommentMain",
  data() {
    return {
      feedId: this.$route.params.feedId,
      token: sessionStorage.getItem("jwt"),
    };
  },
  computed: {
    ...mapState(["feedComments"]),
  },
  methods: {
    ...mapActions(["setFeedComments"]),
  },
  async mounted() {
    await this.setFeedComments({
      feedId: this.feedId,
      token: this.token,
    });
  },
};
</script>

<style></style>
