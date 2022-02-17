<template>
  <div id="feed-detail-header">
    <div class="cancel" v-on:click="goBack()">
      <span class="material-icons"> arrow_back_ios_new </span>
    </div>
    <div class="context">게시글</div>
    <div></div>
  </div>
</template>

<script>
export default {
  name: "FeedDetailHeader",
  methods: {
    goBack() {
      this.$emit("goBack");
    },
  },
};
</script>

<style>
#feed-detail-header {
  background-color: #ffffff;
  display: flex;
  height: 40px;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  padding: 0 10px;
}

#feed-detail-header > .cancel {
  height: 24px;
}

#feed-detail-header > .cancel > span {
  font-size: 24px;
  color: rgba(0, 0, 0, 0.87);
}

#feed-detail-header > .context {
  font-weight: bold;
}
</style>
