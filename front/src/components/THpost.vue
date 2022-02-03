<template>
  <div class="th-post">
    <div class="header level">
      <div class="level-left">
        <figure class="image is-32x32">
          <img :src="post.userImage" />
        </figure>
        <span class="username">{{ post.username }}</span>
      </div>
    </div>
    <div
      class="image-container"
      :class="post.filter"
      :style="{ backgroundImage: 'url(' + post.postImage + ')' }"
      @dblclick="like"
    >
      이미지 컨테이너
    </div>
    <div class="content">
      <div class="heart">
        <i
          class="far fa-heart fa-lg"
          :class="{ fas: this.post.hasBeenLiked }"
          @click="like"
        ></i>
      </div>
      <p class="likes">{{ post.likes }} likes</p>
      <p class="caption">
        <span>{{ post.username }}</span> {{ post.caption }}
      </p>
    </div>
    <div class="comments">댓글 div 테스트 중입니다.</div>
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
    like() {
      this.post.hasBeenLiked ? this.post.likes-- : this.post.likes++;
      this.post.hasBeenLiked = !this.post.hasBeenLiked;
    },
  },
};
</script>

<style lang="scss" scoped>
.th-post > * {
  border: 1px solid black;
  margin: 2px 0;
}
</style>
