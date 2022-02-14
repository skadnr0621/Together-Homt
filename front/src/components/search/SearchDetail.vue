<template>
  <div class="search-detail">
    <div id="search-nav">
      <div id="popularity" @click="activeCategory('popularity')">인기</div>
      <div id="user" @click="activeCategory('user')">계정</div>
      <div id="tag" @click="activeCategory('tag')">태그</div>
    </div>
    <div id="search-items">
      <div
        id="tag-box"
        v-if="tagSearchFeeds != null && tagSearchFeeds.length != 0"
        v-show="active == 'popularity' || active == 'tag'"
        @click="showList()"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="55px"
          viewBox="-1 -1 52 52"
          width="55px"
          fill="#000000"
        >
          <g><circle cx="25" fill="none" cy="25" r="25" stroke="#E5E5E5" /></g>
          <g>
            <path
              transform="translate(13 13)"
              d="M20,10V8h-4V4h-2v4h-4V4H8v4H4v2h4v4H4v2h4v4h2v-4h4v4h2v-4h4v-2h-4v-4H20z M14,14h-4v-4h4V14z"
            />
          </g>
        </svg>
        <div id="info-box">
          <p id="main-info">{{ searchKeyword }}</p>
          <p id="sub-info">{{ tagSearchFeeds.length }} 게시물</p>
        </div>
      </div>

      <div v-show="active == 'popularity' || active == 'user'">
        <div v-for="(item, index) in searchUsers" :key="index">
          <div id="account-box" @click="goProfile(item.username, item.email)">
            <img :src="mediaURL(index)" alt="프로필" />
            <div id="info-box">
              <p id="main-info">{{ item.username }}</p>
              <p id="sub-info">{{ item.introduce }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const server = "http://i6b206.p.ssafy.io:8092";

export default {
  name: "SearchDetail",
  data: function () {
    return {
      active: "popularity",
      sortedResult: null,
    };
  },
  props: {
    searchKeyword: String,
    searchUsers: Array,
    tagSearchFeeds: Array,
  },
  methods: {
    activeCategory: function (subject) {
      if (this.active != subject) {
        document
          .querySelector(`#${this.active}`)
          .classList.remove("active-sub");
        this.active = subject;
        document.querySelector(`#${subject}`).classList.add("active-sub");
      }
    },

    mediaURL(idx) {
      return server + this.searchUsers[idx].profile_url;
    },

    goProfile(username, email) {
      this.$router.push({
        name: "Profile",
        params: { userName: username, email: email },
      });
    },

    showList() {
      this.$emit("show-search-feeds", this.tagSearchFeeds);
    },
  },
  mounted() {
    document.querySelector("#popularity").classList.add("active-sub");
  },
};
</script>

<style>
#search-nav {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
#search-nav .active-sub {
  border-bottom: 1px solid black;
  font-weight: 600;
}

#search-nav > div {
  width: 100%;
  padding: 10px 0px;
  text-align: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

#search-items #account-box,
#tag-box {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding-left: 25px;
  padding-top: 17px;
}

#search-items #account-box > img {
  width: 55px;
  height: 55px;
  object-fit: cover;
  border-radius: 70%;
}

#search-items #info-box {
  margin: 0 25px;
}

#info-box > #main-info {
  font-weight: 800;
  font-size: 14px;
}

#info-box > #sub-info {
  font-family: "Noto Sans KR", sans-serif;
  color: rgba(0, 0, 0, 0.6);
  font-size: 13px;
}
</style>
