<template>
  <div id="register-feed-header">
    <div class="cancel" v-on:click="goBack()">취소</div>
    <div class="context">새 게시물</div>
    <div class="done" v-on:click="registerFeed()">공유</div>
  </div>
</template>

<script>
export default {
  name: "RegisterFeedHeader",
  methods: {
    goBack() {
      this.$emit("goBack");
    },
    registerFeed() {
      this.$emit("registerFeed", true);
    },
  },
};
</script>

<style>
#register-feed-header {
  display: flex;
  height: 40px;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  padding: 0 10px;
}

#register-feed-header > .cancel,
.done {
  font-size: 15px;
}

#register-feed-header > .done {
  color: rgba(41, 98, 255, 0.86);
}

#register-feed-header > .context {
  font-weight: bold;
}
</style>
