<template>
  <div class="comment-detail">
    <!-- <comment-header v-on:goBack="onGoBack" /> -->
    <div class="inputComment">
      <!-- 왜 profile url 만 넣으면 지랄나는거야?? -->
      <div class="print" v-for="item in comments" v-bind:key="item.commendId">
        <div class="pimage" v-if="item.profileUrl == null">
          <img class="headerimg" src="@/assets/스카피.jpg" alt="프로필 사진" />
        </div>
        <div v-else>
          <img class="pimgae2" :src="item.profileUrl" />
        </div>
        <div class="username">{{ item.username }}</div>
        <div class="comment_content" v-if="item.content.length == 0">
          <!-- 댓글이 없을 경우 -->
          아직 댓글이 없습니다. 댓글을 써보세요.
        </div>
        <div class="comment_content2" v-else>
          {{ item.content }}
        </div>
        <div class="UnD">
          <!-- <div class="update">
            <i class="fa-solid fa-pen fa-2x"></i>
          </div> -->
          <div class="delete">
            <i
              class="fa-solid fa-trash fa-2x"
              @click="deleteComment(item.commentdId, feedId)"
            ></i>
            {{ item.commentdId }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import CommentHeader from "@/components/CommentPage/CommentHeader";

export default {
  name: "FrontCommentdetail",
  props: {
    comments: Array,
    feedId: String,
  },
  components: {
    // CommentHeader,
  },
  methods: {
    //댓글삭제
    deleteComment(commentid, feedId) {
      const commentId = commentid;
      console.log("삭제 요청");
      console.log(commentId);
      console.log(feedId);
      axios
        .delete(`/feed/${feedId}/comments/${commentId}`)
        .then((res) => {
          alert("댓글 삭제");
          console.log(res);
        })
        .catch((err) => {
          alert("댓글 안삭제");
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.comment-detail {
  border: 1px solid black;
  margin: 10px;
  padding: 1px;
}
.comment-detail > .inputComment {
  border: 1px solid black;
  margin: 10px;
  padding: 1px;
}
.comment-detail > .inputComment > .print > .pimage {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin: 2px;
  display: flex;
}
.comment-detail > .inputComment > .print > .pimage > .headerimg {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
}
.comment-detail > .inputComment > .print > .pimage2 {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin: 2px;
  display: flex;
}
</style>
