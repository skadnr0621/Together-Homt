<template>
  <div class="excerciselist">
    <div class="notice">
      이게 왜 위로 붙어?
      <input type="button" value="알림" style="float: right" />
      <input type="button" value="염탐" style="float: right" />
    </div>
    <div class="username">{{ username }}</div>
    <div class="exercises_list">
      운동리스트
      <div class="category">
        카테고리 버튼들 <br />
        <!-- 리스트로 운동을 받아오면 리스트 요소로 운동이름을 받아오는데 그 운동이름을 exercise의 매개변수로 넘겨야함. -->
        <button @click="exercise('hi')">hi운동</button>
        <button @click="exercise('neck')">목 스트레칭</button>
        <button @click="exercise('waist')">허리 스트레칭</button>
        <button @click="exercise('arm')">팔 스트레칭</button>
        <button @click="exercise('squat')">스쿼트</button>
      </div>
      <!-- v-dragscroll.x -->
      <div class="exercises">
        <div class="circle">원그리기</div>
        <div class="circle2">하나더?</div>
      </div>
    </div>
    <div class="today_exercise">
      오늘의 운동
      <div class="information">운동 리스트에서 원하는 운동을 클릭해보세요.</div>
      <div class="gage-bar">오늘의 운동을 시작해보세요.</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ExercisesList",
  created: () => {
    /*
      1. exercise 테이블에 접근한다.
      2. 테이블의 '부위', '이름'을 가져온다.
      3. 배열 형태로 가져와서 뿌려준다.
      일단 제대로 받아오는지 체크하고
      그 데이터를 리스트로 뿌려준다. - 피드와 동일 : 객체로 받아올텐데
    */
    axios
      .get("/exercise/all", {
        header: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    exercise: function (exercise) {
      this.$router.push({
        name: "MotionDetect",
        query: { exercise: exercise },
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
