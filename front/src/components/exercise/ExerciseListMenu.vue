<template>
  <div id="exercise-list-menu">
    <div @click="changeExerciseType('전체')">전체</div>
    <div @click="changeExerciseType('상체')">상체</div>
    <div @click="changeExerciseType('하체')">하체</div>
    <div @click="changeExerciseType('스트레칭')">스트레칭</div>
    <div @click="changeExerciseType('근력운동')">근력운동</div>
  </div>
</template>

<script>
export default {
  name: "ExerciseListMenu",
  methods: {
    changeExerciseType(value) {
      this.$emit("changeExerciseType", value);
    },
  },
};
</script>

<style lang="scss" scoped></style>
