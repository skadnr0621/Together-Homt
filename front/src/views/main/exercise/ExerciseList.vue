<template>
  <div class="excerciselist">
    <div class="notice">
      <input id="notice" type="button" value="알림" style="float: right" />
      <input id="spy" type="button" value="염탐" style="float: right" />
    </div>
    <!-- <div class="username">{{ username }}</div> -->
    <div class="exercises_list">
      운동리스트
      <div class="category">
        <button @click="gotoexample()">시연 영상</button>
      </div>
      <!-- v-dragscroll.x -->
    </div>
    <div class="basket_exercise">
      운동 장바구니
      <ExerciseBasket></ExerciseBasket>
    </div>
    <div class="today_exercise">
      오늘의 운동
      <div class="information">운동 리스트에서 원하는 운동을 클릭해보세요.</div>
      <div class="TDexercises">
        <TodayExercise></TodayExercise>
        <button @click="exercise('hi')">hi운동</button>
        <button @click="exercise('neck')">목 스트레칭</button>
        <button @click="exercise('waist')">허리 스트레칭</button>
        <button @click="exercise('arm')">팔 뻗기</button>
        <button @click="exercise('squat')">스쿼트</button>
        <button @click="exercise('ateral_raise')">레터럴 레이즈</button>
        <button @click="exercise('cross_crunches')">
          크로스 사이드 크런치
        </button>
      </div>
      <div class="gage-bar">오늘의 운동을 시작해보세요.</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TodayExercise from "@/components/exercise/TodayExercise";
import ExerciseBasket from "@/components/exercise/ExerciseBasket";

export default {
  name: "ExercisesList",
  data: () => {
    return {
      total_excercises: [],
    };
  },
  components: {
    TodayExercise,
    ExerciseBasket,
  },
  created: () => {
    var vm = this;
    axios
      .get("/exercise/exercises", {
        header: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        vm.total_excercises = res.data;
        console.log(vm.total_excercises);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    //모션디텍트로 연결
    exercise: function (exercise) {
      this.$router.push({
        name: "MotionDetect",
        query: { exercise: exercise },
      });
    },
    //시연영상으로 연결
    gotoexample() {
      this.$router.push({
        // name: "MotionDetect",
        // query: { exercise: exercise },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.excerciselist {
  border: 1px solid black;
  margin: 30px;
  padding: 10px;
}
.execise_recommend > * {
  border: 1px solid black;
  margin: 2px 0;
}
.today_exercise > * {
  border: 1px solid black;
  margin: 2px 0;
}
.exercises_list > * {
  border: 1px solid black;
  margin: 2px 0;
}
.username {
  text-align: center;
}
.information {
  text-align: center;
}
.gage-bar {
  text-align: center;
}
.exercises_list > ex {
  margin: 2px 0;
}
</style>
