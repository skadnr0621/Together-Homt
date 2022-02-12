<template>
  <div class="th-post">
    불러오세요.
    <div class="print" v-for="item in tmp" v-bind:key="item.user_id">
      <!-- 이거지금 user_id 상관없나??? 여기서 key로 넣어주는 게 기준??-->
      <div class="header-level">
        <div class="level-left">
          <div class="pimage">
            <!-- <img :src="post.프로필사진" /> -->
            <img
              class="headerimg"
              src="@/assets/죠르디2.png"
              alt="프로필 사진"
            />
          </div>
          <div class="username">{{ item.username }}</div>
        </div>
      </div>
      <div class="image-container" @dblclick="Like">
        이미지/비디오
        <!-- <div
          v-if="
            mediaURL(index).slice(-3) == 'jpg' ||
            mediaURL(index).slice(-3) == 'png'
          "
        >
          <img :src="mediaURL(index)" />
        </div>
        <div v-else>
          <video :src="mediaURL(index)" controls autoplay></video>
        </div> -->
      </div>
      <div class="content">
        <div class="heart">
          <!-- <i class="fa-regular fa-heart"></i> -->
          <i class="fa-regular fa-heart fa-2x" @click="Like"></i>
          <!-- 클릭으로 좋아요를 체크합니다.  -->
        </div>
        <div class="speech">
          <i class="fa-regular fa-comment fa-2x" @click="goComment"></i>
        </div>
        <div class="likes" @click="golikeList">
          좋아요 {{ item.like_cnt }} 개
        </div>
        <div class="caption">
          <div class="username">{{ item.username }}</div>
          <div class="caption">{{ item.content }}</div>
        </div>
      </div>
      <div class="comments" @click="goComment">댓글을 한개만 출력</div>
      <div class="Tags">#{{ item.tags }} 태그</div>
      <div class="createTime" v-if="item.createdAt != null">
        {{ item.createdAt[0] }}년 {{ item.createdAt[1] }}월
        {{ item.createdAt[2] }}일
      </div>
      <div class="createTime2" v-else>생성날짜가 없습니다.</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Thpost",
  props: {
    tmp: Array,
  },
  methods: {
    //프로필
    goProfile: function () {
      //유저를 식별할 수 있는 인자를 가지고 이동해야하나
      this.$router.push({
        name: "UserPage",
      });
    },
    //좋아요
    Like: () => {
      console.log("click!");
      //1. 좋아요 상태 체크
      //체크안되어있으면 좋아요를 보내고 되어 있으면 취소보내고
      //2. 좋아요 개수 변경
      // 좋아요 상태 체크해서 ++하거나 --하거나
      //this.item.like_cnt++;
    },
    //댓글모양 클릭하면 피드게시물의 번호만 가지고 댓글 페이지로 이동
    goComment: function () {
      this.$router.push({
        name: "CommentPage",
        params: {
          feedId: this.feedId,
          username: this.username,
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
.th-post {
  border: 1px solid black;
  margin: 10px;
  padding: 1px;
}
.th-post > .print {
  border: 2px solid blue;
  margin: 10px;
  padding: 2px;
}
.th-post > .print > .image-container {
  cursor: pointer;
}
.th-post > .print > .header-level > .level-left {
  border: 1px solid black;
  margin: 2px;
  display: flex;
}
.th-post > .print > .header-level > .level-left > .pimage {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin: 2px;
}
.th-post > .print > .header-level > .level-left > .pimage > .headerimg {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.th-post > .print > .header-level > .level-left > .username {
  border: 1px solid black;
  margin: 2px;
  text-align: center;
  cursor: pointer;
}
.th-post > .print > .content > .heart {
  width: 35px;
  height: 35px;
  border: 1px solid black;
  margin: 1px;
  display: inline-block;
  cursor: pointer;
}
.th-post > .print > .content > .speech {
  width: 35px;
  height: 35px;
  border: 1px solid black;
  margin: 1px;
  display: inline-block;
  cursor: pointer;
}
.th-post > .print > .content > .likes {
  border: 1px solid black;
  margin: 2px;
  cursor: pointer;
}
.th-post > .print > .content > .username {
  width: 30px;
  height: 30px;
  border: 1px solid black;
  margin: 5px;
  display: inline;
  text-align: center;
  cursor: pointer;
}
.th-post > .print > .content > .caption {
  border: 1px solid black;
  margin: 2px;
  cursor: pointer;
}
.th-post > .print > .header > .level-left {
  border: 1px solid black;
  display: flex;
  margin: 2px;
}
.th-post > .print > .comments {
  border: 1px solid black;
  margin: 2px;
  cursor: pointer;
}
.th-post > .print > .createTime {
  border: 1px solid black;
  margin: 2px;
}
.th-post > .print > .Tags {
  border: 1px solid black;
  margin: 2px;
}
.th-post > .print > .image-container {
  border: 1px solid black;
  margin: 2px;
}
.th-post > .print > .content > .profile-image {
  display: flex;
  border: 1px solid black;
  margin: 2px;
}
</style>
