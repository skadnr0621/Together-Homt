<template>
  <div class="th-post">
    <div class="print" v-for="(item, index) in tmp" v-bind:key="index">
      <!-- re:사용자 프로필, 삭제 버튼 -->
      <div class="header-level">
        <div>
          <div id="img"><img :src="item.profileUrl" alt="프로필 사진" /></div>
          <div id="usrnm">{{ item.username }}</div>
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
            <i class="fa-solid fa-heart fa-2x" @click="Like(item.feedId)"></i>
          </div>
          <div class="heart2" v-else>
            <i
              class="fa-regular fa-heart fa-2x"
              @click="UnLike(item.feedId)"
            ></i>
          </div>
          <!-- 댓글 이모티콘 -->
          <div class="speech">
            <i
              class="fa-regular fa-comment fa-2x"
              @click="goComment(item.feedId)"
            ></i>
          </div>
        </div>

        <!-- 좋아요 -->
        <div class="likes">
          <div
            id="click-likes"
            v-if="item.likeCnt == null"
            @click="golikeList(item.feedId)"
          >
            제일 먼저 좋아요를 눌러보세요.
          </div>
          <div id="click-likes" v-else @click="golikeList(item.feedId)">
            좋아요 {{ item.likeCnt }} 개
          </div>
        </div>

        <!-- 피드 내용 -->
        <div class="caption">
          <div class="username">{{ item.username }}</div>
          <div class="caption-content" v-if="item.content.length > 15">
            {{ item.content.substring(0, 15) }}
            <span class="detail" @click="goContentDetail()">...더보기</span>
          </div>
          <div class="caption-content2" v-else>
            {{ item.content }}
          </div>
        </div>
      </div>
      <div class="comments" @click="goComment(item.feedId)">전체 댓글 보기</div>
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
import axios from "axios";
axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");

export default {
  name: "Thpost",
  data() {
    return {
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      //여기서 likes를 넘겨줘야 아래에서 받는 것 같아.
      //feed: tmp,
    };
  },
  props: {
    tmp: Array,
  },
  computed: {},
  methods: {
    //프로필
    goProfile: function () {
      //유저를 식별할 수 있는 인자를 가지고 이동해야하나
      this.$router.push({
        name: "UserPage",
      });
    },
    //좋아요
    Like(feedId) {
      axios
        .post(`/feed/${feedId}/likes`)
        .then((res) => {
          //likestatus 변경
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //좋아요 취소
    unLike(feedId) {
      axios
        .delete(`/feed/${feedId}/likes`)
        .then((res) => {
          //likestatus 변경
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //댓글모양 클릭하면 피드게시물의 번호만 가지고 댓글 페이지로 이동
    goComment: function () {
      this.$router.push({
        name: "CommentPage",
        query: {
          feedId: this.tmp.feedId,
          username: this.tmp.username,
        },
      });
    },
    //좋아요 리스트
    golikeList: function () {
      this.$router.push({
        name: "LikeListPage",
      });
    },
    //피드 상세페이지
    goCaptionDetail: function () {
      //해당 피드 아이디를 가지고 이동??
    },
  },
};
</script>

<style lang="scss" scoped>
.print {
  border: 1px solid black;
  margin: 20px;
}
.print > .header-level > div {
  display: flex;
}
.th-post > .print > .header-level > div > #img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
.th-post > .print > .header-level > div > #usrnm {
  text-align: center;
  display: flex;
  justify-content: center;
}
.th-post > .print > .header-level > div > div > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.th-post > .print > .feed-image > .feed-image-container {
  margin-left: 5px;
}
.th-post > .print > .feed-image > .feed-image-container > .img {
  width: 100%;
  height: 100%;
}
.th-post > .print > .feed-image > .feed-image-container2 {
  margin-left: 5px;
}
.th-post > .print > .feed-image > .feed-image-container2 > .img {
  width: 100%;
  height: 100%;
}
.print > .header-level > div > div {
  margin: 5px;
}
.print > .header-level > div > div {
  margin: 5px;
}
.th-post > .print > .feed-content > .imoticon {
  display: flex;
  margin: 5px;
}
.th-post > .print > .feed-content > .imoticon > .heart {
  display: flex;
  cursor: pointer;
  width: 30px;
  height: 30px;
  margin: 3px;
  margin-right: 15px;
}
.th-post > .print > .feed-content > .imoticon > .heart2 {
  display: flex;
  cursor: pointer;
  width: 30px;
  height: 30px;
  margin: 3px;
  margin-right: 15px;
}
.th-post > .print > .feed-content > .imoticon > .speech {
  display: flex;
  cursor: pointer;
  width: 30px;
  height: 30px;
  margin: 3px;
}
.th-post > .print > .feed-content > .likes > #click-likes {
  margin: 5px;
  margin-top: 5px;
  margin-bottom: 5px;
  cursor: pointer;
}
.th-post > .print > .feed-content > .caption > .username {
  margin-left: 5px;
  cursor: pointer;
}
.th-post > .print > .feed-content > .caption > .caption-content > .detail {
  margin-left: 5px;
  cursor: pointer;
}
.th-post > .print > .feed-content > .caption > .caption-content {
  margin-left: 5px;
  cursor: pointer;
}
.th-post > .print > .feed-content > .caption > .caption-content2 {
  margin-left: 5px;
  cursor: pointer;
}
.th-post > .print > .comments {
  margin-left: 5px;
  cursor: pointer;
}
.th-post > .print > .Tags {
  margin-left: 5px;
}
.th-post > .print > .createTime {
  margin-left: 5px;
  font-size: 0.8em;
  color: grey;
}
.th-post > .print > .createTime2 > #font {
  margin-left: 5px;
  font-size: 0.8em;
  color: grey;
}
</style>
