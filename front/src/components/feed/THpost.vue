<template>
  <div class="th-post">
    불러오세요.
    <div class="print" v-for="item in tmp" v-bind:key="item.user_id">
      <!-- re:사용자 프로필, 삭제 버튼 -->
      <div class="header-level">
        <div>
          <div><img :src="프로필url" alt="프로필 사진" /></div>
          <div>{{ item.username }}</div>
        </div>
        <div v-if="isDelete">
          <span class="material-icons" @click="deleteFeed(item.feedId)">
            delete
          </span>
        </div>
      </div>

      <!-- re: 피드 이미지 -->
      <div class="feed-image-container">
        <!-- 피드컨텐츠url 없을 경우 -->
        <img :src="피드컨텐츠url" alt="피드 기본 이미지" />
      </div>

      <!-- re: 피드 컨텐츠 - 좋아요 개수, 피드 내용, 댓글 -->

      <div class="feed-content">
        <!-- 좋아요 -->
        <div class="heart" v-if="좋아요상태따라서분기">
          <i class="fa-regular fa-heart fa-2x" @click="Like()"></i>
        </div>
        <div v-else>
          <i class="fa-solid fa-heart fa-2x" @click="UnLike()"></i>
        </div>
        <div class="likes">
          <div v-if="item.likeCnt == 0" @click="golikeList(item.feedId)">
            좋아요를 눌러보세요.
          </div>
          <div v-else @click="golikeList()">좋아요 {{ item.likeCnt }} 개</div>
        </div>

        <!-- 댓글 말풍선 -->
        <div class="speech">
          <i
            class="fa-regular fa-comment fa-2x"
            @click="goComment(item.feedId)"
          ></i>
        </div>

        <!-- 피드 내용 -->
        <div class="caption">
          <div class="username">{{ item.username }}</div>
          <div class="caption">{{ item.content }}</div>
        </div>
      </div>
      <div class="comments" @click="goComment(item.feedId)">
        댓글한개만출력가능한가
      </div>
      <div class="Tags">#{{ item.tags }} 태그</div>

      <!--피드 게시 날짜 -->
      <div class="createTime" v-if="item.createdAt != null">
        {{ item.createdAt[0] }}년 {{ item.createdAt[1] }}월
        {{ item.createdAt[2] }}일
      </div>
      <div class="createTime2" v-else>생성날짜가 없습니다.</div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "Thpost",
  data() {
    return {
      //이메일을 가져오는 방법을 잘 모르겠는데, 이 방법이 맞는지
      token: sessionStorage.getItem("jwt"),
      email: this.$route.params.email,
      //이 부분 틀린것 같음 이메일을 받는 방법을 찾아야함
    };
  },
  props: {
    tmp: Array,
  },
  computed: {
    ...mapState({ loginUser: (state) => state.userStore.LoginUser }),
    //로그인유저는 유저네임을 말하는 것 같음 이런식으로 선언하면 안됨
  },
  methods: {
    //프로필(유저네임과 이메일)
    goProfile(username, email) {
      this.$router.push({
        name: "Profile",
        query: {
          userName: username,
          email: email,
        },
      });
    },

    //좋아요
    Like: () => {
      console.log("click!");
      //1. 좋아요 상태 체크
      //체크되어있으면 좋아요 취소,
      //체크안되어있으면 좋아요 체크,
      //그에 따른 좋아요 개수 --, ++
    },

    //댓글상세페이지
    goComment(id) {
      this.$router.push({
        name: "CommentPage",
        query: { feedId: id },
      });
    },

    //좋아요 리스트(피드 아이디)
    golikeList(id) {
      this.$router.push({
        name: "LikeListPage",
        query: { feedId: id },
      });
    },

    //피드 상세페이지(유저네임, 이메일, 피드아이디)
    goCaptionDetail(username, email, id) {
      this.$router.push({
        name: "ProfileFeedDetail",
        query: {
          userName: username,
          email: email,
          feedId: id,
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>
