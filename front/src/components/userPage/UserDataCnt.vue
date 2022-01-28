<template>
  <div id="user-data-cnt">
    <div class="data">
      <router-link :to="{ name: '' }">
        <div class="cnt">{{ myInfo.feeds.length }}</div>
        <div class="name">게시글</div>
      </router-link>
    </div>

    <div class="data">
      <router-link
        :to="{ name: 'FollowPage', params: { userName: myInfo.username } }"
      >
        <div class="cnt">{{ myInfo.following.length }}</div>
        <div class="name">팔로잉</div>
      </router-link>
    </div>

    <div class="data">
      <router-link
        :to="{ name: 'FollowPage', params: { userName: myInfo.username } }"
      >
        <div class="cnt">{{ myInfo.follower.length }}</div>
        <div class="name">팔로워</div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserDataCnt",
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
};
</script>

<style>
#user-data-cnt {
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  margin-top: 10px;
}

#user-data-cnt > .data {
  /* border: 1px solid black; */
}

#user-data-cnt > .data > a > .cnt {
  color: rgba(0, 0, 0, 0.87);
  font-weight: bold;
}

#user-data-cnt > .data > a > .name {
  color: rgba(0, 0, 0, 0.6);
  font-size: 12px;
}
</style>
