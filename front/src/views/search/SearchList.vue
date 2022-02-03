<template>
  <div class="search-list">
    <div id="feed-container">
      <img
      v-for="(feed, index) in showList"
      :key="index"
      :src="mediaURL(index)" alt="">
    </div>
  </div>
</template>

<script>
import axios from 'axios'

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
      return "http://localhost:8092" + this.showList[idx].media_url;
    }
  },
  mounted() {
    axios({
      method: 'get',
      url: `/feed/all`,
    })
    .then((res)=> {
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
</style>