<template>
  <div id="exercise-list-basket-header">
    {{ timestamp }} 운동 장바구니
    <div>
      <button v-on:click="DeleteStorage()">운동 전체삭제</button>
      <button v-on:click="QuitExercise()">운동 종료</button>
      <!-- 휴지통 아이콘으로 대체 -->
    </div>
  </div>
</template>

<script>
import Feed from "@/views/main/feed/Feed";

export default {
  name: "Exerciselistbasketheader",
  data() {
    return {
      timestamp: "",
    };
  },
  methods: {
    DeleteStorage() {
      this.$emit("DeleteStorage");
    },

    QuitExercise() {
      this.$router.push({
        path: "/main/feed",
        name: "Feed",
        component: Feed,
      });
    },
  },
  created() {
    const now = new Date();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    const weekList = new Array(
      "일요일",
      "월요일",
      "화요일",
      "수요일",
      "목요일",
      "금요일",
      "토요일"
    );
    const week = weekList[now.getDay()];
    this.timestamp = `${month} 월 ${date} 일 ${week}`;
  },
};
</script>

<style lang="scss" scoped></style>
