<template>
  <div id="like-list-page">
    LikeListPage
    <like-list-header />
    <like-list />
  </div>
</template>

<script>
import LikeListHeader from "@/components/LikeListPage/LikeListHeader";
import LikeList from "@/components/LikeListPage/LikeList";

export default {
  name: "LikeListPage",
  components: {
    LikeListHeader,
    LikeList,
  },
};
</script>

<style></style>
