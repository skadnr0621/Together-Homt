<template>
  <div id="feed-detail">
    <!-- 사용자 프로필, 삭제 버튼-->
    <div class="feed-detail-header">
      <div>
        <div><img :src="myInfo.profile_url" alt="사용자프로필" /></div>
        <div>{{ myInfo.username }}</div>
      </div>
      <div>
        <span class="material-icons" @click="deleteFeed()"> delete </span>
      </div>
    </div>

    <!-- 피드 사진-->
    <div class="feed-detail-img">
      <img :src="myInfo.feeds[feedId].media_url" alt="" />
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
      <div @click="goLikeList()">좋아요 46개</div>
      <!-- <div @click="goLikeList()">가장 먼저 좋아요를 눌러보세요.</div> -->
      <div>
        <div>{{ myInfo.username }}</div>
        <div v-if="myInfo.feeds[feedId].content.length > 15">
          {{ myInfo.feeds[feedId].content.substring(0, 15) }}
          <span @click="goComment()">...더보기</span>
        </div>
        <div v-else>
          {{ myInfo.feeds[feedId].content }}
        </div>
      </div>
      <div @click="goComment()">댓글 3개 모두 보기</div>
    </div>

    <!-- 피드 게시 날짜-->
    <div class="feed-detail-date">
      {{ myInfo.feeds[feedId].createdAt[0] }}년
      {{ myInfo.feeds[feedId].createdAt[1] }}월
      {{ myInfo.feeds[feedId].createdAt[2] }}일
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "FeedDetail",
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  props: ["feedId"],
  methods: {
    goLikeList() {
      this.$emit("goLikeList");
    },
    goComment() {
      this.$emit("goComment");
    },
    deleteFeed() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$emit("deleteFeed");
      }
    },
    likeFeed() {
      this.$emit("likeFeed");
    },
  },
};
</script>

<style>
#feed-detail > .feed-detail-header {
  display: flex;
  height: 50px;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}

#feed-detail > .feed-detail-header > div:first-child {
  display: flex;
  align-items: center;
}

#feed-detail > .feed-detail-header > div:first-child > div:first-child {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#feed-detail > .feed-detail-header > div > div > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#feed-detail > .feed-detail-header > div > div:nth-child(2) {
  margin-left: 10px;
  font-size: 15px;
  font-weight: bold;
  color: rgba(0, 0, 0, 0.87);
}

#feed-detail > .feed-detail-header > div > span {
  color: rgba(0, 0, 0, 0.38);
  font-size: 24px;
}

#feed-detail > .feed-detail-img > img {
  width: 100%;
}

#feed-detail > .feed-detail-menu {
  display: flex;
  align-items: center;
  height: 44px;
  padding: 0 10px;
}

#feed-detail > .feed-detail-menu > div {
  height: 24px;
  width: 24px;
  margin-right: 14px;
  color: rgba(0, 0, 0, 0.87);
}

#feed-detail > .feed-detail-content {
  padding: 0 10px;
  display: flex;
  flex-direction: column;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.87);
}

/* 좋아요 */
#feed-detail > .feed-detail-content > div:nth-child(1) {
  font-weight: bold;
  margin-bottom: 10px;
}

/* 닉네임 내용 */
#feed-detail > .feed-detail-content > div:nth-child(2) {
  display: flex;
}

#feed-detail > .feed-detail-content > div:nth-child(2) > div {
  margin-right: 5px;
}

#feed-detail > .feed-detail-content > div:nth-child(2) > div:nth-child(1) {
  font-weight: bold;
}

#feed-detail
  > .feed-detail-content
  > div:nth-child(2)
  > div:nth-child(2)
  > span {
  color: rgba(0, 0, 0, 0.38);
}

/* 댓글 */
#feed-detail > .feed-detail-content > div:nth-child(3) {
  color: rgba(0, 0, 0, 0.38);
}

#feed-detail > .feed-detail-date {
  padding: 10px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.38);
}
</style>
