<template>
  <div id="follow-list">
    <div class="search">
      <span class="material-icons-outlined"> search</span>
      <input type="text" placeholder="검색" />
    </div>
    <!-- 나의 팔로우 정보-->
    <div v-if="loginUser == this.email">
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
            <div
              class="profile"
              @click="goProfile(value.username, value.email)"
            >
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div
              class="username"
              @click="goProfile(value.username, value.email)"
            >
              {{ value.username }}
            </div>
            <div class="btn">
              <!-- 모두 다 팔로잉 표시 -->
              <button class="following-btn" @click="onUnfollow(value.email)">
                팔로잉
              </button>
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
            <div
              class="profile"
              @click="goProfile(value.username, value.email)"
            >
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div
              class="username"
              @click="goProfile(value.username, value.email)"
            >
              {{ value.username }}
            </div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <button
                class="following-btn"
                v-if="myFollowingList.includes(value.email)"
                @click="onUnfollow(value.email)"
              >
                팔로잉
              </button>
              <button class="follow-btn" v-else @click="onFollow(value.email)">
                팔로우
              </button>
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
            <div
              class="profile"
              @click="goProfile(value.username, value.email)"
            >
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div
              class="username"
              @click="goProfile(value.username, value.email)"
            >
              {{ value.username }}
            </div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <span v-if="value.email == loginUser"></span>
              <button
                class="following-btn"
                v-else-if="myFollowingList.includes(value.email)"
                @click="onUnfollow(value.email)"
              >
                팔로잉
              </button>
              <button class="follow-btn" v-else @click="onFollow(value.email)">
                팔로우
              </button>
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
            <div
              class="profile"
              @click="goProfile(value.username, value.email)"
            >
              <img :src="value.profile_url" alt="프로필 사진" />
            </div>
            <div
              class="username"
              @click="goProfile(value.username, value.email)"
            >
              {{ value.username }}
            </div>
            <div class="btn">
              <!-- 팔로우 하지 않은 사람 (팔로우 표시)-->
              <span v-if="value.email == loginUser"></span>
              <button
                class="following-btn"
                v-else-if="myFollowingList.includes(value.email)"
                @click="onUnfollow(value.email)"
              >
                팔로잉
              </button>
              <button class="follow-btn" v-else @click="onFollow(value.email)">
                팔로우
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

import { mapState, mapActions } from "vuex";

export default {
  name: "FollowList",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      viewFollow: this.$route.params.follow,
      myFollowingList: [], // 내가 팔로우한 유저의 이메일
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

    // 프로필 페이지 이동하기
    goProfile(name, email) {
      this.$router.push({
        name: "Profile",
        params: { userName: name, email: email },
      });
    },

    // 팔로우 하기
    async onFollow(email) {
      await axios
        .post(`/follow/${email}`)
        .then((res) => {
          console.log(res);
          alert("팔로우 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("팔로우 실패!");
        });
      this.$router.go();
    },

    // 언팔로우 하기
    async onUnfollow(email) {
      await axios
        .delete(`/follow/${email}`)
        .then((res) => {
          console.log(res);
          alert("언팔로우 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("언팔로우 실패!");
        });
      this.$router.go();
    },
  },
  async mounted() {
    // 나의 팔로잉 정보 조회하기
    await this.setMyFollowing({
      email: this.loginUser,
      token: this.token,
    });
    console.log("FollowList에서 나의 팔로잉 정보 get요청함!");

    this.myFollowingList = this.myFollowing.map((x) => x.email); // 객체 배열 중 email만 뽑아서 배열 생성하기
    console.log(this.myFollowingList);

    if (this.loginUser == this.email) {
      // 팔로워 정보
      await this.setMyFollower({
        email: this.loginUser,
        token: this.token,
      });
      console.log("FollowList에서 나의 팔로워 정보 get요청함!");
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
      }
      // 팔로워 정보
      else {
        await this.setUserFollower({
          email: this.email,
          token: this.token,
        });
        console.log("FollowList에서 유저의 팔로워 정보 get요청함!");
      }
    }
  },
};
</script>

<style></style>
