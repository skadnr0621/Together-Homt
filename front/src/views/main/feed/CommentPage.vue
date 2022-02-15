<template>
  <div id="comment-page">
    <comment-header v-on:goBack="onGoBack" />
    <comment-detail :comments="comments"></comment-detail>
    <comment-register v-bind:feedId="feedId"></comment-register>
  </div>
</template>

<script>
import CommentHeader from "@/components/CommentPage/CommentHeader";
import CommentDetail from "@/components/CommentPage/CommentDetail";
import CommentRegister from "@/components/CommentPage/CommentRegister";
import axios from "axios";

export default {
  name: "CommentPage",
  data() {
    return {
      comments: [],
      comment: "",
      feedId: this.$route.query.feedId,
      //사용자 정보를 넘겨줘야하는데..
    };
  },
  components: {
    CommentHeader,
    CommentDetail,
    CommentRegister,
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
