<template>
  <div id="feed-page">
    <h1>Feed</h1>
    <Feed-Lists :posts="posts" />
  </div>
</template>

<script>
import Feedlists from "./FeedLists";
import posts from "../assets/posts";
import VueDragscroll from "vue-dragscroll";
Vue.use(VueDragscroll);

export default {
  name: "Feed",
  data() {
    return {
      posts,
    };
  },
  components: {
    "Feed-Lists": Feedlists,
  },
  methods: {
    /*
  1. feed 테이블에 접근한다. 
  2. 모든 feed를 가져온다. 
  3. 가져올 때 객체 형태로 가져온다.
  4. data로 return 해준다. 

  1. 피드 댓글을 조회한다. 
  2. 모든 댓글을 가져온다. 
  3. 가져올 때 객체 형태로 가져온다.
  4. data로 return 해준다. 

  1. 좋아요 - 가져온 feed_id를 통해 접근???
*/
    getData: function () {
      axios({
        method: "get",
        url: `/feed/main`,
        data: this.feed,
        // ???
      })
        .then(function (response) {
          //body에 담긴 데이터를 data로 상속??해야 합니다. return posts
          console.log(response);
        })
        .catch((error) => {
          alert(error);
        });
    },
  },
};
</script>

<style>
#feed-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
