<template>
  <div class="search-list">
    <div id="feed-container">
      <div
      v-for="(feed, index) in showList"
      :key="index">
        <div v-if="mediaURL(index).slice(-3) == 'jpg' || mediaURL(index).slice(-3) == 'png'">
          <img
          :src="mediaURL(index)">
        </div>
        <div v-else>
          <video
          :src="mediaURL(index)"
          controls autoplay></video>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const server = "http://i6b206.p.ssafy.io:8092"
export default {
  name: "SearchList",
  props: {
    showFeedList: Array,
  },
  data: function() {
    return {
      allFeeds: null,
      showList: null,
    }
  },
  methods: {
    mediaURL(idx) {
      return server + this.showList[idx].media_url;
    }
  },
  mounted() {
    axios({
      method: 'get',
      url: `/slide1/feedlist`,
    })
    .then((res)=> {
      console.log(res)

      this.allFeeds = res.data
      this.$emit('all-feeds', this.allFeeds)
      
      if (this.showFeedList == null) {
        this.showList = this.allFeeds
      } else {
        this.showList = this.showFeedList
      }
    })
  },
}
</script>

<style>
#feed-container {
  width: 360px;
  display: grid;
  grid-gap: 1px;
  background-color: white;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: 1fr;
}

#feed-container img {
  width: 119.3px;
  height: 119.3px;
}

#feed-container video {
  background: black;
  width: 119.3px;
  height: 119.3px;
}

</style>