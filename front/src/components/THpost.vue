<template>
  <div class="th-post">
    <div class="header level">
      <div class="level-left">
        <figure class="image is-32x32">
          <!-- <img :src="post.프로필사진" /> -->
        </figure>
        <span class="username">{{ post.user_id }}</span>
      </div>
    </div>
    <div class="image-container" @dblclick="like">
      <!--  :style="{ backgroundImage: 'url(' + ㅇㅇ + 피드에올라가는사진')' }" -->
      이미지 컨테이너
    </div>
    <div class="content">
      <div class="heart">
        <!-- 좋아요했는지 여부 체크 -->
        <!-- 하트 이거 왜 안나옴??? -->
        <!-- <i
          class="far fa-heart fa-lg"
          :class="{ fas: this.post.hasBeenLiked }"
          @click="like"
        ></i> -->
      </div>
      <p class="likes">{{ post.like_cnt }} 좋아요개수</p>
      <p class="caption">
        <span>{{ post.user_id }} 유저닉네임 </span> {{ post.content }} 캡션.
        이미지외에 적은글
      </p>
    </div>
    <div class="comments">댓글</div>
    <div class="CreateTime">{{ post.createdAt }} 생성일자</div>
    <div class="Tags">{{ post.tag }} 생성일자</div>
  </div>
</template>

<script>
// import axios from "axios";
export default {
  name: "Thpost",
  props: {
    post: Object,
  },
  methods: {
    // like() {
    //   this.post.hasBeenLiked ? this.post.likes-- : this.post.likes++;
    //   this.post.hasBeenLiked = !this.post.hasBeenLiked;
    // },
  },
};
</script>

<style lang="scss" scoped>
.th-post > * {
  border: 1px solid black;
  margin: 2px 0;
}
</style>
