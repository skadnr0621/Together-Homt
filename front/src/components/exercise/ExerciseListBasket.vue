<template>
  <div id="exercise-list-basket">
    <div v-for="(exercise, index) in todayExercises" :key="index">
      <span class="basket" @click="goExercise(exercise)">{{ exercise }}</span>
      <i class="fa-solid fa-trash-can" @click="removeExercise(exercise)"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "Exerciselistbasket",
  data() {
    return {
      todayExercises: [],
    };
  },
  created() {
    if (localStorage.length > 0) {
      for (let i = 0; i < localStorage.length; i++) {
        if (
          localStorage.key(i) !== "loglevel:webpack-dev-server" &&
          localStorage.key(i) !== "vuex"
        ) {
          this.todayExercises.push(localStorage.getItem(localStorage.key(i)));
          // 운동을 판별하는 로직을 다시 짜야할듯 - 리스트랑 비교
        }
      }
    } else {
      console.log("아직 운동바구니가 비었습니다. ");
    }
  },
  methods: {
    goExercise(exercise) {
      this.exercise = exercise;
      // console.log(this.exercise);
      this.$router.push({
        name: "MotionDetect",
        query: { exercise: this.exercise },
      });
    },

    //시연영상으로 연결
    gotoexample() {
      this.$router.push({
        //시연영상으로 연결
      });
    },

    //삭제
    removeExercise(exercise) {
      this.exercise = exercise;
      localStorage.removeItem(this.exercise);
      alert("운동을 삭제했습니다.");
      this.$router.go();
    },
  },
};
</script>

<style lang="scss" scoped>
#exercise-list-basket > div > span {
}
</style>
