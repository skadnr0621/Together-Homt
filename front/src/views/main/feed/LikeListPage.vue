<template>
  <div id="like-list-page">
    LikeListPage
    <!-- <like-list-header /> -->
    <like-list />
  </div>
</template>

<script>
// import LikeListHeader from "@/components/LikeListPage/LikeListHeader";
import LikeList from "@/components/LikeListPage/LikeList";

export default {
  name: "LikeListPage",
  // created: function () {
  //   //여기서 데이터를 가져와서 상태 확인하고 리스트로 아래로 넘겨주면 likelist에서 받아서 뿌려주세요.
  //   axios.get("/feed/${feed-id}/likes", {}).then().catch();
  // },
  components: {
    // LikeListHeader,
    LikeList,
  },
};
</script>

<style></style>
