<template>
  <div id="exercise-list-header">
    <div class="cancel" v-on:click="goBack()">
      <span class="material-icons"> arrow_back_ios_new </span>
    </div>
    <div class="context">운동 리스트</div>
  </div>
</template>

<script>
export default {
  name: "ExerciseListHeader",
  methods: {
    goBack() {
      this.$emit("goBack");
    },
  },
};
</script>

<style lang="scss" scoped></style>
