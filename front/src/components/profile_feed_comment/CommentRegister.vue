<template>
  <div id="comment-register">
    <div><img :src="myInfo.profileUrl" alt="사용자프로필" /></div>
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
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

import { mapState, mapActions } from "vuex";

export default {
  name: "CommentRegister",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      comment: "",
    };
  },
  computed: {
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    ...mapState(["myInfo"]),
  },
  methods: {
    ...mapActions(["setMyInfo"]),

    async registerComment() {
      await axios
        .post(`/feed/${this.$route.params.feedId}/comment`, {
          content: this.comment,
        })
        .then((res) => {
          alert("댓글 등록 성공!");
          console.log(res);
        })
        .catch((err) => {
          alert("댓글 등록 실패!");
          console.log(err);
        });
      this.$router.go();
    },
  },
  async mounted() {
    await this.setMyInfo({
      token: this.token,
      email: this.loginUser,
    });
  },
};
</script>

<style></style>
