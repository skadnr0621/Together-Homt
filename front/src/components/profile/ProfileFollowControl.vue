<template>
  <div id="profile-follow-control">
    <div class="follow-btn" v-show="isFollow == -1" @click="onFollow">
      팔로우
    </div>
    <div class="follow-btn" v-show="isFollow != -1" @click="onUnFollow">
      팔로잉
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

export default {
  name: "ProfileFollowControl",
  props: ["email", "isFollow"],
  methods: {
    // 팔로우 하기
    async onFollow() {
      await axios
        .post(`/communication/follows/${this.email}`)
        .then((res) => {
          console.log(res);
          alert("팔로우 성공!");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
          alert("팔로우 실패!");
        });
    },

    // 언팔로우 하기
    async onUnFollow() {
      await axios
        .delete(`/communication/follows/${this.email}`)
        .then((res) => {
          console.log(res);
          alert("언팔로우 성공!");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
          alert("언팔로우 실패!");
        });
    },
  },
};
</script>

<style></style>
