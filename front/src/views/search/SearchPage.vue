<template>
  <div id="search-page">
    <h1>Search</h1>
    <input @click="search()" v-model="searchKeyword" type="text">

    <div v-if="searchMode">
      <search-detail
      :searchKeyword="searchKeyword"
      ></search-detail>
      <button @click="noSearch()">취소</button>
    </div>
    <div v-else>
      <search-list></search-list>
    </div>
  </div>
</template>

<script>
import SearchDetail from './SearchDetail.vue'
import SearchList from './SearchList.vue'

export default {
  name: "SearchPage",
  components: { 
    SearchList, 
    SearchDetail
  },
  data: function(){
    return {
      searchKeyword: null,
      searchMode: false,
    }
  },
  methods: {
    search: function() {
      this.searchMode = true
    },
    noSearch: function() {
      this.searchKeyword = null
      this.searchMode = false
    }
  }
}
</script>

<style>
#search-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>