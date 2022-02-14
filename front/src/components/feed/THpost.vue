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
              src="@/assets/스카피.jpg"
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
          <i class="fa-regular fa-heart fa-2x" @click="Like()"></i>
          <!-- <i class="fa-solid fa-heart fa-2x @click="UnLike"></i> -->
        </div>

        <div class="speech">
          <i
            class="fa-regular fa-comment fa-2x"
            @click="goComment(item.feedId)"
          ></i>
        </div>

        <div class="likes">
          <div v-if="item.likeCnt == 0" @click="golikeList(item.feedId)">
            좋아요를 눌러보세요.
          </div>
          <div v-else @click="golikeList()">좋아요 {{ item.likeCnt }} 개</div>
        </div>

        <div class="caption">
          <div class="username">{{ item.username }}</div>
          <div class="caption">{{ item.content }}</div>
        </div>
      </div>
      <div class="comments" @click="goComment(item.feedId)">
        댓글한개만출력가능한가
      </div>
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
  text-align: center;
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
  cursor: pointer;
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
