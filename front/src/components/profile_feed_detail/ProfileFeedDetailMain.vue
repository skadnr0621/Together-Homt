<template>
  <div id="profile-feed-detail-main">
    <!-- 내 피드 출력 -->
    <div>
      <div v-if="feedInfo.length == 0">
        {{ goBack() }}
      </div>
      <div v-else v-for="(feed, index) in feedInfo" :key="index">
        <!-- 사용자 프로필, 삭제 버튼-->
        <div class="feed-detail-header">
          <div>
            <div><img :src="info.imagePath" alt="내프로필" /></div>
            <div>{{ info.username }}</div>
          </div>
          <div v-if="isDelete">
            <span class="material-icons" @click="deleteFeed(feed.feedId)">
              delete
            </span>
          </div>
        </div>

        <!-- 피드 사진-->
        <div class="feed-detail-img">
          <img :src="feed.mediaUrl" alt="" />
        </div>

        <!-- 피드 메뉴바 (좋아요, 댓글, 스크랩) -->
        <div class="feed-detail-menu">
          <div>
            <span
              v-if="feed.likeStatus"
              class="material-icons-outlined"
              @click="unlikeFeed(feed.feedId)"
            >
              favorite
            </span>
            <span
              v-else
              class="material-icons-outlined"
              @click="likeFeed(feed.feedId)"
            >
              favorite_border
            </span>
          </div>
          <div>
            <span
              class="material-icons-outlined"
              @click="goComment(info.username, info.email, feed.feedId, index)"
            >
              comment
            </span>
          </div>
        </div>

        <!-- 피드 내용 (좋아요 개수, 피드 내용, 댓글) -->
        <div class="feed-detail-content">
          <div v-if="feed.likeCnt == 0">가장 먼저 좋아요를 눌러보세요.</div>
          <div
            v-else
            @click="goLikeList(info.username, info.email, feed.feedId, index)"
          >
            좋아요 {{ feed.likeCnt }}개
          </div>
          <div>
            <div v-if="feed.content.length > 15">
              {{ feed.content.substring(0, 15) }}
              <span @click="goContentDetail()">...더보기</span>
            </div>
            <div v-else>
              {{ feed.content }}
            </div>
          </div>
          <div
            @click="goComment(info.username, info.email, feed.feedId, index)"
          >
            댓글 ??개 모두 보기
          </div>
        </div>

        <!-- 피드 게시 날짜-->
        <div class="feed-detail-date">
          {{ feed.createdAt[0] }}년 {{ feed.createdAt[1] }}월
          {{ feed.createdAt[2] }}일
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

export default {
  name: "ProfileFeedDetailMain",
  props: ["info", "feedInfo", "isDelete"],
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      name: this.$route.params.userName,
    };
  },
  methods: {
    // 뒤로 가기
    goBack() {
      this.$emit("goBack");
    },

    // 피드 삭제
    async deleteFeed(feedId) {
      if (confirm("정말 삭제하시겠습니까?")) {
        await axios
          .delete(`/feed/feeds/${feedId}`)
          .then((res) => {
            alert("삭제 성공!");
            console.log(res);
          })
          .catch((err) => {
            alert("삭제 실패!");
            console.log(err);
          });

        // 내 피드 리스트 가져오기
        await this.$store.dispatch("feedStore/setFeedInfo", {
          email: this.loginUser,
          token: this.token,
        });

        this.$router.go();
      }
    },

    // 좋아요 등록
    async likeFeed(feedId) {
      await axios
        .post(`/feed/${feedId}/likes`)
        .then((res) => {
          console.log(res);
          alert("좋아요 등록 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("좋아요 등록 실패!");
        });

      this.$router.go();
    },

    // 좋아요 취소
    async unlikeFeed(feedId) {
      await axios
        .delete(`/feed/${feedId}/likes`)
        .then((res) => {
          console.log(res);
          alert("좋아요 취소 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("좋아요 취소 실패!");
        });

      this.$router.go();
    },

    // 피드 댓글 이동
    goComment(name, email, id, index) {
      this.$router.push({
        name: "ProfileFeedComment",
        params: { userName: name, email: email, feedId: id, index: index },
      });
    },

    // 좋아요 리스트 이동
    goLikeList(name, email, id, index) {
      this.$router.push({
        name: "ProfileFeedLike",
        params: { userName: name, email: email, feedId: id, index: index },
      });
    },
  },
};
</script>

<style></style>
