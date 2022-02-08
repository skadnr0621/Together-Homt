<template>
  <div id="feed">
    <div>feed</div>
    <!-- <FeedList :posts="posts"></FeedList> -->

    <FeedList></FeedList>
  </div>
</template>

<script>
//import { mapState } from "vuex";
import axios from "axios";
import FeedList from "@/components/FeedList";

export default {
  name: "Feed",
  data: () => {
    return {
      tmp: [],
    };
  },
  components: {
    FeedList,
  },
  mounted() {
    //화살표 생략
    axios
      .get("/feed/all", {
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        this.tmp = res.data;
        //로그인 응답으로 받은 데이터를 store에 저장
        //피드화면으로 뿌려줘야합니다.
        console.log(this.tmp); //기록
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    //...mapState(["myInfo"]),
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
#feed {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.025);
}
</style>
