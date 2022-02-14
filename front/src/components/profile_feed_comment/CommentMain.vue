<template>
  <div id="comment-main">
    <!-- 피드 게시글 -->
    <div class="feed-content">
      <div>
        <div><img :src="info.imagePath" alt="피드작성자프로필" /></div>
        <div>{{ info.username }}</div>
      </div>
      <div>{{ feed.content }}</div>
      <div>
        {{ feed.createdAt[0] }}년 {{ feed.createdAt[1] }}월
        {{ feed.createdAt[2] }}일
      </div>
    </div>

    <!-- 피드 댓글들 -->
    <div class="feed-comment">
      <div v-for="(comment, index) in commentInfo" :key="index">
        <div>
          <div>
            <img :src="comment.profileUrl" alt="댓글 작성자 프로필" />
          </div>
          <div>{{ comment.username }}</div>
          <div>{{ comment.content }}</div>
        </div>
        <div v-if="comment.email == loginUser">
          <span
            class="material-icons"
            @click="deleteComment(feed.feedId, comment.commentId)"
          >
            delete
          </span>
        </div>
        <div v-else></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

export default {
  name: "CommentMain",
  props: ["info", "commentInfo", "feed", "loginUser"],
  methods: {
    // 댓글 삭제
    async deleteComment(feedId, commentId) {
      if (confirm("정말 삭제하시겠습니까?")) {
        await axios
          .delete(`/feed/${feedId}/comments/${commentId}`)
          .then((res) => {
            alert("삭제 성공!");
            console.log(res);
          })
          .catch((err) => {
            alert("삭제 실패!");
            console.log(err);
          });

        this.$router.go();
      }
    },
  },
};
</script>

<style></style>
