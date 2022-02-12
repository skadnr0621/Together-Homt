<template>
  <div id="profile-feed-detail-main">
    <!-- 내 피드 출력 -->
    <div v-if="email == loginUser">
      <div v-for="(userFeed, index) in userFeeds.feeds" :key="index">
        <!-- 사용자 프로필, 삭제 버튼-->
        <div class="feed-detail-header">
          <div>
            <div><img :src="myInfo.profileUrl" alt="내프로필" /></div>
            <div>{{ myInfo.username }}</div>
          </div>
          <div>
            <span class="material-icons" @click="deleteFeed(userFeed.feed_id)">
              delete
            </span>
          </div>
        </div>

        <!-- 피드 사진-->
        <div class="feed-detail-img">
          <img :src="userFeed.media_url" alt="" />
        </div>

        <!-- 피드 메뉴바 (좋아요, 댓글, 스크랩) -->
        <div class="feed-detail-menu">
          <div>
            <span class="material-icons-outlined" @click="likeFeed()">
              favorite_border
            </span>
          </div>
          <div>
            <span
              class="material-icons-outlined"
              @click="
                goComment(myInfo.username, myInfo.email, userFeed.feed_id)
              "
            >
              comment
            </span>
          </div>
        </div>

        <!-- 피드 내용 (좋아요 개수, 피드 내용, 댓글) -->
        <div class="feed-detail-content">
          <div v-if="userFeed.like_cnt == 0" @click="goLikeList()">
            가장 먼저 좋아요를 눌러보세요.
          </div>
          <div v-else @click="goLikeList()">
            좋아요 {{ userFeed.like_cnt }}개
          </div>
          <div>
            <div>{{ userInfo.username }}</div>
            <div v-if="userFeed.content.length > 15">
              {{ userFeed.content.substring(0, 15) }}
              <span @click="goContentDetail()">...더보기</span>
            </div>
            <div v-else>
              {{ userFeed.content }}
            </div>
          </div>
          <div
            @click="goComment(myInfo.username, myInfo.email, userFeed.feed_id)"
          >
            댓글 3개 모두 보기
          </div>
        </div>

        <!-- 피드 게시 날짜-->
        <div class="feed-detail-date">
          {{ userFeed.created_at[0] }}년 {{ userFeed.created_at[1] }}월
          {{ userFeed.created_at[2] }}일
        </div>
      </div>
    </div>

    <!-- 다른 사용자 피드 출력 -->
    <div v-else>
      <div v-for="(userFeed, index) in userFeeds.feeds" :key="index">
        <!-- 사용자 프로필, 삭제 버튼-->
        <div class="feed-detail-header">
          <div>
            <div><img :src="userInfo.profileUrl" alt="사용자프로필" /></div>
            <div>{{ userInfo.username }}</div>
          </div>
        </div>

        <!-- 피드 사진-->
        <div class="feed-detail-img">
          <img :src="userFeed.media_url" alt="" />
        </div>

        <!-- 피드 메뉴바 (좋아요, 댓글, 스크랩) -->
        <div class="feed-detail-menu">
          <div>
            <span class="material-icons-outlined" @click="likeFeed()">
              favorite_border
            </span>
          </div>
          <div>
            <span
              class="material-icons-outlined"
              @click="
                goComment(userInfo.username, userInfo.email, userInfo.feed_id)
              "
            >
              comment
            </span>
          </div>
        </div>

        <!-- 피드 내용 (좋아요 개수, 피드 내용, 댓글) -->
        <div class="feed-detail-content">
          <div v-if="userFeed.like_cnt == 0" @click="goLikeList()">
            가장 먼저 좋아요를 눌러보세요.
          </div>
          <div v-else @click="goLikeList()">
            좋아요 {{ userFeed.like_cnt }}개
          </div>
          <div>
            <div>{{ userInfo.username }}</div>
            <div v-if="userFeed.content.length > 15">
              {{ userFeed.content.substring(0, 15) }}
              <span @click="goContentDetail()">...더보기</span>
            </div>
            <div v-else>
              {{ userFeed.content }}
            </div>
          </div>
          <div
            @click="
              goComment(userInfo.username, userInfo.email, userInfo.feed_id)
            "
          >
            댓글 3개 모두 보기
          </div>
        </div>

        <!-- 피드 게시 날짜-->
        <div class="feed-detail-date">
          {{ userFeed.created_at[0] }}년 {{ userFeed.created_at[1] }}월
          {{ userFeed.created_at[2] }}일
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
  name: "ProfileFeedDetailMain",
  data() {
    return {
      email: this.$route.params.email,
      name: this.$route.params.userName,
    };
  },
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 내 정보
    ...mapState(["myInfo", "userInfo", "userFeeds"]),
  },
  async mounted() {
    // 유저 피드 조회
    await this.setUserFeeds({
      email: this.email,
      token: this.token,
    });
    console.log("ProfileFeedDetailMain에서 유저 피드 get요청함!");
  },
  methods: {
    ...mapActions(["setUserFeeds"]),

    // 피드 삭제
    async deleteFeed(value) {
      if (confirm("정말 삭제하시겠습니까?")) {
        await axios
          .delete(`/feed/${value}`)
          .then((res) => {
            alert("삭제 성공!");
            console.log(res);
          })
          .catch((err) => {
            alert("삭제 실패!");
            console.log(err);
          });

        this.$router.go();
      }
    },

    // 좋아요 클릭
    likeFeed() {
      console.log("좋아요!");
    },

    // 피드 댓글 이동
    goComment(name, email, id) {
      this.$router.push({
        name: "ProfileFeedComment",
        params: { userName: name, email: email, feedId: id },
      });
    },
  },
};
</script>

<style></style>
:userName/:email:/:feedId
