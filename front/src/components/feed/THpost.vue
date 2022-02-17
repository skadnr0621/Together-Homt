<template>
  <div class="th-post">
    <div class="print" v-for="(item, index) in tmp" v-bind:key="index">
      <!-- re:사용자 프로필, 삭제 버튼 -->
      <div class="header-level">
        <div>
          <div id="img"><img :src="item.profileUrl" alt="프로필 사진" /></div>
          <div id="usrnm" @click="goProfile(item.name, item.email)">
            {{ item.username }}
          </div>
          <!-- 프로필로 이동하기 -->
        </div>
      </div>

      <!-- re: 피드 이미지 -->
      <div class="feed-image">
        <div class="feed-image-container" v-if="item.mediaUrl == null">
          <img :src="item.mediaUrl" alt="피드 기본 이미지" />
        </div>
        <div class="feed-image-container2" v-else>
          <img :src="item.mediaUrl" alt="피드 저장 이미지" />
        </div>
      </div>
      <!-- re: 피드 컨텐츠 - 좋아요 개수, 피드 내용, 댓글 -->
      <div class="feed-content">
        <div class="imoticon">
          <!-- 좋아요 이모티콘-->
          <div class="heart" v-if="item.likeStatus">
            <i
              class="fa-solid fa-heart fa-2x"
              @click="unlikeFeed(item.feedId)"
            ></i>
          </div>
          <div class="heart2" v-else>
            <i
              class="fa-regular fa-heart fa-2x"
              @click="likeFeed(item.feedId)"
            ></i>
          </div>
          <!-- 댓글 이모티콘 -->
          <div class="speech">
            <i
              class="fa-regular fa-comment fa-2x"
              @click="goComment(item.username, item.email, item.feedId)"
            ></i>
          </div>
        </div>

        <!-- 좋아요 -->
        <div class="likes">
          <div
            id="click-likes"
            v-if="item.likeCnt == null"
            @click="golikeList(item.username, item.email, item.feedId)"
          >
            제일 먼저 좋아요를 눌러보세요.
          </div>
          <div
            id="click-likes"
            v-else
            @click="golikeList(item.username, item.email, item.feedId)"
          >
            좋아요 {{ item.likeCnt }} 개
          </div>
        </div>

        <!-- 피드 내용 -->
        <div class="caption">
          <div class="username">{{ item.username }}</div>
          <div
            class="caption-content"
            v-if="item.content.length > 15"
            @click="goCaptionDetail(item.username, item.email, item.feedId)"
          >
            {{ item.content.substring(0, 15) }}
            <span
              class="detail"
              @click="goCaptionDetail(item.username, item.email, item.feedId)"
              >...더보기</span
            >
          </div>
          <div class="caption-content2" v-else>
            {{ item.content }}
          </div>
        </div>
      </div>
      <div
        class="comments"
        @click="goComment(item.username, item.email, item.feedId)"
      >
        전체 댓글 보기
      </div>
      <div class="Tags">태그 #{{ item.tags }}</div>

      <!--피드 게시 날짜 -->
      <div class="createTime" v-if="item.createdAt != null">
        {{ item.createdAt[0] }}년 {{ item.createdAt[1] }}월
        {{ item.createdAt[2] }}일
      </div>
      <div class="createTime2" v-else>
        <font id="font">생성날짜가 없습니다.</font>
      </div>
    </div>
  </div>
</template>

<script>
import api from "@/api/api.js";
api.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

import "@/assets/css/THpost.css";

export default {
  name: "Thpost",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      name: this.$route.params.userName,
    };
  },
  props: ["info", "feedInfo", "isDelete", "tmp"],
  computed: {},
  methods: {
    //프로필 이동
    goProfile(name, email) {
      this.$router.push({
        name: "Profile",
        params: { userName: name, email: email },
      });
    },

    //좋아요
    async likeFeed(feedId) {
      await api
        .post(`/feed/${feedId}/likes`, null, {
          headers: {
            Authorization: this.token,
          },
        })
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
      await api
        .delete(`/feed/${feedId}/likes`, {
          headers: {
            Authorization: this.token,
          },
        })
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

    // goComment(name, email, id) {
    //   this.$router.push({
    //     name: "CommentPage",
    //     query: {
    //       feedId: this.tmp.feedId,
    //       username: this.tmp.username,
    //     },
    //   });
    // },
    // 피드 댓글 이동
    goComment(name, email, id) {
      this.$router.push({
        name: "ProfileFeedComment",
        params: { userName: name, email: email, feedId: id },
      });
      this.$router.go();
    },

    //좋아요 리스트 조회
    goLikeList(name, email, id) {
      this.$router.push({
        name: "ProfileFeedLike",
        params: { userName: name, email: email, feedId: id },
      });
    },

    //피드 상세페이지
    goCaptionDetail(name, email, id) {
      this.$router.push({
        name: "ProfileFeedDetail",
        params: { userName: name, email: email, feedId: id },
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>
