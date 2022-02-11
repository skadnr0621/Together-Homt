<template>
  <div id="profile-feed-detail-main">
    <div>
      <div v-for="(userFeed, index) in userFeeds.feeds" :key="index">
        <!-- 사용자 프로필, 삭제 버튼-->
        <div class="feed-detail-header">
          <div>
            <div><img :src="userInfo.profileUrl" alt="사용자프로필" /></div>
            <div>{{ userInfo.username }}</div>
          </div>
          <div v-if="userInfo.email == myInfo.email">
            <span class="material-icons" @click="deleteFeed()"> delete </span>
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
            <span class="material-icons-outlined" @click="goComment()">
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
              <span @click="goComment()">...더보기</span>
            </div>
            <div v-else>
              {{ userFeed.content }}
            </div>
          </div>
          <div @click="goComment()">댓글 3개 모두 보기</div>
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
import { mapState } from "vuex";

export default {
  name: "ProfileFeedDetailMain",
  computed: {
    // 로그인한 사용자 이메일 가져오기
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

    // 내 정보
    ...mapState(["myInfo", "userInfo", "userFeeds"]),
  },
  methods: {
    // 좋아요 클릭
    likeFeed() {
      console.log("좋아요!");
    },
  },
};
</script>

<style></style>
