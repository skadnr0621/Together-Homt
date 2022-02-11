<template>
  <div id="follow-header">
    <div class="back-btn">
      <span class="material-icons-outlined" v-on:click="goBack">
        arrow_back_ios
      </span>
    </div>
    <div class="username">{{ $route.params.userName }}</div>
  </div>
</template>

<script>
export default {
  name: "FollowHeader",
  methods: {
    goBack() {
      this.$emit("goBack");
    },
  },
};
</script>

<style></style>
