<template>
  <div id="follow-list">
    <div class="search">
      <span class="material-icons-outlined"> search</span>
      <input type="text" placeholder="검색" />
    </div>
    <!-- 나의 팔로우 정보-->
    <div v-if="this.loginUser == this.email">
      <!-- 팔로잉 정보 -->
      <div v-if="viewFollow == 'following'">
        <div class="default" v-if="myFollowing.length == 0">
          <span class="material-icons"> person_add </span>
          <h4>팔로잉</h4>
          <p>
            회원님이 다른 사람을 팔로우하면 <br />여기에 회원님이 팔로우한
            사람이 표시됩니다.
          </p>
        </div>

        <div class="list" v-else>
          <div
            class="follow"
            v-for="(value, index) in myFollowing"
            :key="index"
          >
            <div class="profile">
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div class="username">{{ value.username }}</div>
            <div class="btn">
              <!-- 모두 다 팔로잉 표시 -->
              <button>팔로잉</button>
            </div>
          </div>
        </div>
      </div>
      <!-- 팔로워 정보 -->
      <div v-else>
        <div class="default" v-if="myFollower.length == 0">
          <span class="material-icons"> person_add </span>
          <h4>팔로워</h4>
          <p>
            회원님이 다른 사람을 팔로우하면 <br />여기에 회원님이 팔로우한
            사람이 표시됩니다.
          </p>
        </div>

        <div class="list" v-else>
          <div class="follow" v-for="(value, index) in myFollower" :key="index">
            <div class="profile">
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div class="username">{{ value.username }}</div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <button
                class="follow-btn"
                v-if="unfollowList.includes(value.email)"
              >
                팔로우
              </button>
              <button class="following-btn" v-else>팔로잉</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 다른 유저의 팔로우 정보-->
    <div v-else>
      <!-- 팔로잉 정보 -->
      <div v-if="viewFollow == 'following'">
        <div class="default" v-if="userFollowing.length == 0">
          <span class="material-icons"> person_add </span>
          <h4>팔로잉</h4>
          <p>
            회원님이 다른 사람을 팔로우하면 <br />여기에 회원님이 팔로우한
            사람이 표시됩니다.
          </p>
        </div>

        <div class="list" v-else>
          <div
            class="follow"
            v-for="(value, index) in userFollowing"
            :key="index"
          >
            <div class="profile">
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div class="username">{{ value.username }}</div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <button
                class="follow-btn"
                v-if="unfollowList.includes(value.email)"
              >
                팔로우
              </button>
              <button class="following-btn" v-else>팔로잉</button>
            </div>
          </div>
        </div>
      </div>
      <!-- 팔로워 정보 -->
      <div v-else>
        <div class="default" v-if="userFollower.length == 0">
          <span class="material-icons"> person_add </span>
          <h4>팔로워</h4>
          <p>
            회원님이 다른 사람을 팔로우하면 <br />여기에 회원님이 팔로우한
            사람이 표시됩니다.
          </p>
        </div>

        <div class="list" v-else>
          <div
            class="follow"
            v-for="(value, index) in userFollower"
            :key="index"
          >
            <div class="profile">
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div class="username">{{ value.username }}</div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <button
                class="follow-btn"
                v-if="unfollowList.includes(value.email)"
              >
                팔로우
              </button>
              <button class="following-btn" v-else>팔로잉</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

// 팔로잉 정보 가져오기
// 팔로우 정보 가져오기

export default {
  name: "FollowList",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      viewFollow: this.$route.params.follow,
      unfollowList: [], // 내가 팔로우 하지 않은 사람 (팔로우 표시)
      isFollow: null, // 내가 팔로우 한 사람인지 체크
    };
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
    // 팔로우 정보 가져오기
    ...mapState(["myFollowing", "myFollower", "userFollower", "userFollowing"]),
  },
  methods: {
    // 팔로우 정보 조회하기
    ...mapActions([
      "setMyFollowing",
      "setMyFollower",
      "setUserFollowing",
      "setUserFollower",
    ]),

    // 팔로우 했는지 안했는지 체크
    checkFollow(value) {
      if (value.email == this.loginUser) {
        return true;
      }
      return false;
    },
  },
  async mounted() {
    // 나의 팔로잉 정보 조회하기
    await this.setMyFollowing({
      email: this.email,
      token: this.token,
    });
    console.log("FollowList에서 나의 팔로잉 정보 get요청함!");

    if (this.loginUser == this.email) {
      // 팔로워 정보
      await this.setMyFollower({
        email: this.email,
        token: this.token,
      });
      console.log("FollowList에서 나의 팔로워 정보 get요청함!");

      // myFollower - myFollowing = 내가 팔로우 하지 않은 사람 (팔로우 표시)
      this.unfollowList = this.myFollower.filter(
        (x) => !this.myFollowing.includes(x)
      );
    }
    // 유저 팔로우 정보 조회하기
    else {
      // 팔로잉 정보
      if (this.viewFollow == "following") {
        await this.setUserFollowing({
          email: this.email,
          token: this.token,
        });
        console.log("FollowList에서 유저의 팔로잉 정보 get요청함!");

        // userFollowing - myFollowing = 내가 팔로우 하지 않은 사람 (팔로우 표시)
        this.unfollowList = this.userFollowing.filter(
          (x) => !this.myFollowing.includes(x)
        );

        this.isFollow = this.userFollowing.findIndex(this.checkFollow);
      }
      // 팔로워 정보
      else {
        await this.setUserFollower({
          email: this.email,
          token: this.token,
        });
        console.log("FollowList에서 유저의 팔로워 정보 get요청함!");

        // userFollower - myFollowing = 내가 팔로우 하지 않은 사람 (팔로우 표시)
        this.unfollowList = this.userFollower.filter(
          (x) => !this.myFollowing.includes(x)
        );
      }

      this.isFollow = this.userFollower.findIndex(this.checkFollow);
    }

    this.unfollowList = this.unfollowList.map((x) => x.email);
    console.log("출력!");
    console.log(this.unfollowList);
  },
};
</script>

<style></style>
