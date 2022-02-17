<template>
  <div id="likelist-page">
    <!-- <likelist-header v-on:goBack="onGoBack" /> -->
    <like-list :likes="likes"></like-list>
  </div>
</template>

<script>
// import LikeListHeader from "@/components/LikeListPage/LikeListHeader";
import LikeList from "@/components/LikeListPage/LikeList";
import axios from "axios";

export default {
  name: "LikeListPage",
  data() {
    return {
      likes: [],
      token: sessionStorage.getItem("jwt"),
    };
  },
  components: {
    // LikeListHeader,
    LikeList,
  },
  created() {
    const feedId = this.$route.query.feedId;
    console.log(feedId);
    axios
      .get(`/feed/${feedId}/likes`, {
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        this.likes = res.data;
        console.log(this.likes);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
  },
};
</script>

<style></style>
