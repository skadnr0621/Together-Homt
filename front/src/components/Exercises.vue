<template>
  <div class="execise_recommend">
    <div class="notice">
      이게 왜 위로 붙어?
      <input type="button" value="알림" style="float: right" />
      <input type="button" value="염탐" style="float: right" />
    </div>
    <div class="username">{{ username }}</div>
    <div class="exercises_list">
      운동리스트
      <div class="category">카테고리 버튼들</div>
      <!-- v-dragscroll.x -->
      <div class="exercises">
        <!-- <svg class="curation" height="200" width="300">
          리스트 안의 내용을 뿌리는 역할을 해야합니다. 그걸 대신 원안에 뿌려야함.
           <circle cx="100" cy="100" r="45" style="fill:limegreen" v-on:click="cy-=50"/>
          <circle cx="200" cy="100" r="45" style="fill:limegreen"/>
          <circle cx="300" cy="100" r="45" style="fill:limegreen"/>
          <circle cx="400" cy="100" r="45" style="fill:limegreen"/>
          <circle cx="500" cy="100" r="45" style="fill:limegreen"/> 
        </svg>  -->
        <div class="circle">원그리기</div>
        <div class="circle">하나더?</div>
      </div>
    </div>
    <div class="today_exercise">
      오늘의 운동
      <div class="information">
        운동 리스트에서 원하는 운동을 클릭해보세요.
        <svg height="200" width="300">
          <div>안에 리스트를 배열로 받아오고 싶습니다.</div>
        </svg>
      </div>
      <div class="gage-bar">오늘의 운동을 시작해보세요.</div>
    </div>
  </div>
</template>

<script>
import "@/components/css/exercise.css";
// import axios from "axios";
import VueDragscroll from "vue-dragscroll";
Vue.use(VueDragscroll);

export default {
  name: "Exercises",

  data() {},

  methods: {
    /*
      1. exercise 테이블에 접근한다.
      2. 테이블의 '부위', '이름'을 가져온다.
      3. 배열 형태로 가져와서 뿌려준다.
    */
  },
};
</script>

<style lang="scss" scoped>
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
