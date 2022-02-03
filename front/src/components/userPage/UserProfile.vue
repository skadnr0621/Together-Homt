<template>
  <div id="user-profile">
    <div class="profile">
      <img
        v-if="myInfo.profile_url"
        :src="pictureUrl()"
        alt="유저 프로필 사진"
      />
      <img v-else src="@/assets/profileBasic.png" alt="기본 프로필 사진" />
    </div>
    <div class="name">@{{ nickname }}</div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserProfile",
  props: ["nickname"],
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  methods: {
    pictureUrl() {
      console.log(this.myInfo.profile_url)
      return "http://localhost:8092" + this.myInfo.profile_url;
    },
  },
};
</script>

<style>
#user-profile {
  height: 110px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin-top: 10px;
  /* position: relative; */
  /* margin-bottom: 4px; */
}

#user-profile > .profile {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#user-profile > .profile > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#user-profile > .name {
  font-weight: bold;
  color: rgba(0, 0, 0, 0.87);
}
</style>
