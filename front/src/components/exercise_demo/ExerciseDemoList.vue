<template>
  <div id="exercise-demo-list">
    <div v-for="(exercise, index) in viewExercise" :key="index">
      <div><img :src="require(`@/assets/푸쉬업.png`)" alt="푸쉬업" /></div>
      <div>{{ exercise }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExerciseDemoList",
  props: ["exerciseType"],
  data() {
    return {
      viewExercise: [
        "목 스트레칭",
        "허리 스트레칭",
        "팔 스트레칭",
        "크로스 사이드 크런치",
        "스쿼트",
        "런지",
        "푸쉬업",
        "레터럴 레이즈",
      ],
      exercises: {
        전체: [
          "목 스트레칭",
          "허리 스트레칭",
          "팔 스트레칭",
          "크로스 사이드 크런치",
          "스쿼트",
          "런지",
          "푸쉬업",
          "레터럴 레이즈",
        ],
        상체: [
          "목 스트레칭",
          "허리 스트레칭",
          "팔 스트레칭",
          "크로스 사이드 크런치",
          "푸쉬업",
          "레터럴 레이즈",
        ],
        하체: ["스쿼트", "런지"],
        스트레칭: ["목 스트레칭", "허리 스트레칭", "팔 스트레칭"],
        근력운동: [
          "크로스 사이드 크런치",
          "스쿼트",
          "런지",
          "푸쉬업",
          "레터럴 레이즈",
        ],
      },
    };
  },
  watch: {
    async exerciseType(value) {
      this.viewExercise = this.exercises[value];
    },
  },
};
</script>

<style></style>
