<template>
  <div id="feed">
    피드어딧음

    <THpost :tmp="tmp"></THpost>
  </div>
</template>

<script>
import axios from "axios";
import THpost from "@/components/THpost";

export default {
  name: "Feed",
  data: () => {
    return {
      tmp: [],
    };
  },
  components: {
    THpost,
  },
  created() {
    var vm = this;
    axios
      .get("/feed/feeds/follower", {
        // 팔로우하고있는 피드만 조회
        // /slide1/feedlist
        headers: {
          Authorization: sessionStorage.getItem("jwt"),
        },
      })
      .then((res) => {
        vm.tmp = res.data;
        console.log(vm.tmp);
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
