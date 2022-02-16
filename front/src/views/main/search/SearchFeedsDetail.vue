<template>
  <div id="search-keyword-feedlist" v-if="searchFeedList">
  <div id="search-feed-detail-header">
    <div @click="goBack()">back</div>
    <div v-if="searchKeywordDetail">#{{searchKeywordDetail}}</div>
    <div v-else>탐색 탭</div>
    <div></div>
  </div>
  <div id="search-feed-detail-main">
    <!-- 피드 출력 -->
    <div>
      <div v-if="searchFeedList.length == 0">
        {{ goBack() }}
      </div>
      <div 
      v-else v-for="(feed, index) in searchFeedList" :key="index">
        <!-- 사용자 프로필, 삭제 버튼-->
        <div class="feed-detail-header">
          <div @click="goProfile(feed.username, feed.email)">
            <div><img :src="feed.profileUrl" alt="피드작성자프로필" /></div>
            <div>{{ feed.username }}</div>
          </div>
          <div v-if="canDelete(feed.username, feed.email)">
            <span class="material-icons" @click="deleteFeed(feed.feedId, index)">
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
              @click="unlikeFeed(feed.feedId, index)"
            >favorite</span>
            <span
              v-else
              class="material-icons-outlined"
              @click="likeFeed(feed.feedId, index)"
            >favorite_border</span>
          </div>
          <div>
            <span
              class="material-icons-outlined"
              @click="goComment(myInfo.username, myInfo.email, feed.feedId, index)"
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
            @click="goLikeList(myInfo.username, myInfo.email, feed.feedId, index)"
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
            @click="goComment(feed.username, feed.email, feed.feedId, index)"
          >
            댓글 ???개 모두 보기
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
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from 'vuex';
import "@/assets/css/searchFeedsDetail.css"
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

export default {
  name: "ProfileFeedDetailMain",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      searchKeywordDetail: this.$route.query.searchKeywordDetail,
    };
  },
  computed: {
    ...mapState({ myInfo: (state) => state.myStore.myInfo }),
    ...mapState({ searchFeedList: (state) => state.feedStore.searchFeedList }),
  },
  methods: {
    // 뒤로 가기
    goBack() {
      this.$router.push({ name : 'Search'});
    },
    // 삭제가능
    canDelete(author) {
      if (author == this.myInfo.username) {
        return true
      }
    },

    // 피드 삭제
    async deleteFeed(feedId, index) {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$store.dispatch('feedStore/searchFeedsDelete', {feedId : feedId, index: index})
      }
    },

    // 좋아요 등록
    async likeFeed(feedId, index) {
      await axios
        .post(`/feed/${feedId}/likes`)
        .then(() => {
          this.$store.dispatch('feedStore/updateSearchFeeds', {feedId : feedId, index: index})
          alert("좋아요 등록 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("좋아요 등록 실패!");
        });
        this.$router.go()
    },

              
    // 좋아요 취소
    async unlikeFeed(feedId, index) {
      await axios
        .delete(`/feed/${feedId}/likes`)
        .then(() => {
          this.$store.dispatch('feedStore/updateSearchFeeds', {feedId : feedId, index: index})
          alert("좋아요 취소 성공!");
        })
        .catch((err) => {
          console.log(err);
          alert("좋아요 취소 실패!");
        });
        this.$router.go()
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

    goProfile(name, email) {
      console.log(email)
      this.$router.push({
        name: "Profile",
        params: { username: name, email: email},
      });
    }
  },
};
</script>

