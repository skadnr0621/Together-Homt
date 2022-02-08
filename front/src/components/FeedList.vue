!
<template>
  <div class="feed-list">
    this is feed list.
    <th-post
      v-for="post in posts"
      :post="post"
      :key="posts.indexOf(post)"
    ></th-post>
  </div>
</template>

<script>
import THpost from "./THpost.vue";

export default {
  name: "Feedlist",
  props: {
    posts: Array,
  },
  components: {
    "th-post": THpost,
  },
};
</script>

<style lang="scss" scoped></style>
