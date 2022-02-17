<template>
  <div id="feed">
    <THpost :tmp="tmp"></THpost>
    <!-- <router-link
      :to="{
        name: 'Profile',
        params: { userName: `aaa`, email: `aaa@aaa.com` },
      }"
    >
      aaa
    </router-link>
    <router-link
      :to="{
        name: 'Profile',
        params: { userName: `bbb`, email: `bbb@bbb.com` },
      }"
    >
      bbb
    </router-link>
    <router-link
      :to="{
        name: 'Profile',
        params: { userName: `ccc`, email: `ccc@ccc.com` },
      }"
    >
      ccc
    </router-link>
    <router-link
      :to="{
        name: 'Profile',
        params: { userName: `ddd`, email: `ddd@ddd.com` },
      }"
    >
      ddd
    </router-link>
    <router-link
      :to="{
        name: 'Profile',
        params: { userName: `eee`, email: `eee@eee.com` },
      }"
    >
      eee
    </router-link> -->
  </div>
</template>

<script>
import axios from "axios";
import THpost from "@/components/feed/THpost";

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
      .get("http://3.38.103.222:8092/feed/feeds/follower", {
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
  computed: {},
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
