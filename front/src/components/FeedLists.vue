<template>
  <div class="Feed-Lists">
    <div class="feed">
      <th-post
        v-for="post in posts"
        :post="post"
        :key="posts.indexOf(post)"
      ></th-post>
    </div>
  </div>
</template>

<script>
import Thpost from "./THpost";
export default {
  name: "Feedlists",
  props: {
    posts: Array,
  },
  components: {
    "th-post": Thpost,
  },
};
</script>

<style lang="scss" scoped></style>
