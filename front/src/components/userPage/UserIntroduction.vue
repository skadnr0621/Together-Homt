<template>
  <div id="user-introduction">
    <!-- 안녕하세요!<br />
    운동 시작한지 한달도 안된 헬린이입니다! -->
    {{ myInfo.introduce }}
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserIntroduction",
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
};
</script>

<style>
#user-introduction {
  text-align: center;
  color: rgba(0, 0, 0, 0.87);
  font-size: 12px;
  line-height: 1.6;
  padding: 18px 0;
  /* border-bottom: 1px solid rgba(0, 0, 0, 0.1); */
}
</style>
