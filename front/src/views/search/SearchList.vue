<template>
  <div id="">
    <h1>SearchList</h1>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "SearchList",
  data: function() {
    return {
      sortedFeeds: null,
    }
  },
  mounted() {
    axios({
      method: 'get',
      url: `/feed/all`,
    })
    .then((res)=> {
      console.log(res)
      this.sortedFeeds = res.data
    })
  }
}
</script>

<style>
</style>