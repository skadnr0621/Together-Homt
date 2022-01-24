<template>
  <div id="follow-header">
    <div class="back-btn">
      <span class="material-icons-outlined" v-on:click="goBack">
        arrow_back_ios
      </span>
    </div>
    <div class="username">i.m-cherry</div>
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

<style>
#follow-header {
  height: 40px;

  display: flex;
  align-items: center;
}

#follow-header > div {
  width: 24px;
  height: 24px;
}

#follow-header > .back-btn > span {
  margin-left: 5px;
  font-size: 24px;
}

#follow-header > .username {
  width: 100%;
  text-align: center;
  font-weight: bold;
}
</style>
