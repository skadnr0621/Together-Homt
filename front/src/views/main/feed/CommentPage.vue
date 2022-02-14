<template>
  <div id="comment-page">
    <comment-header v-on:goBack="onGoBack" />
    <div class="show-comment">
      <comment-detail :comments="comments" />
    </div>

    <div class="comment-input">
      <!-- <div class="pimage" v-if="item.profileUrl == null">
        <img class="headerimg" src="@/assets/스카피.jpg" alt="프로필 사진" />
      </div>
      <div v-else>
        <img class="pimgae2" :src="item.profileUrl" />
      </div> -->

      <div class="text">
        <!-- <div class="text-pimage" v-if="item.profileUrl == null">
          <img class="headerimg" src="@/assets/스카피.jpg" alt="프로필 사진" />
        </div>
        <div v-else>
          <img class="text-pimgae2" :src="item.profileUrl" />
        </div> -->
        <div>이미지들어가야하는곳</div>
        <input
          type="text"
          placeholder="댓글을 입력해주세요."
          v-model="comment"
          v-on:keyup.enter="createComment()"
        />
        <div class="submit">
          <button type="button" v-on:click="createComment()">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommentHeader from "@/components/CommentPage/CommentHeader";
import CommentDetail from "@/components/CommentPage/CommentDetail";
import axios from "axios";

export default {
  name: "CommentPage",
  data() {
    return {
      comments: [],
      comment: "",
      feedId: this.$route.query.feedId,
    };
  },
  components: {
    CommentHeader,
    CommentDetail,
  },
  created() {
    const feedId = this.$route.query.feedId;
    console.log(feedId);
    axios
      .get(`/feed/${feedId}/comments`, {
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        this.comments = res.data;
        console.log(this.comments);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    //   // 로그인한 유저 정보
    //   ...mapState(["myInfo"]),
  },
  methods: {
    //뒤로가기
    onGoBack() {
      this.$router.back();
    },

    //댓글 등록
    createComment() {
      const feedId = this.$route.query.feedId;
      console.log(feedId);
      axios
        .post(`/feed/${feedId}/comments`, {
          content: this.comment,
        })
        .then((res) => {
          alert("댓글 등록");
          console.log(res);
        })
        .catch((err) => {
          alert("댓글 안등록");
          console.log(err);
        });
    },
    //새로고침
    //댓글등록하고나서 새로고침하면 토큰이 없어져서 로그아웃되나?
  },
};
</script>

<style>
#comment-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
  /* background-color: rgba(0, 0, 0, 0.025); */
}
#comment-page > .show-comment {
  border: 1px solid black;
  margin: 10px;
  padding: 1px;
}
#comment-page > .comment-input {
  border: 1px solid black;
  margin: 10px;
  padding: 1px;
  text-align: center;
}
#comment-page > .comment-input > .profileImg {
  border: 1px solid black;
  margin: 10px;
  padding: 5px;
  text-align: center;
  display: flex;
}
#comment-page > .comment-input > .submit {
  border: 1px solid black;
  margin: 10px;
  padding: 5px;
  text-align: center;
  display: flex;
}
#comment-page > .comment-input > .text {
  border: 1px solid black;
  margin: 10px;
  padding: 5px;
  text-align: center;
  display: flex;
}
</style>
