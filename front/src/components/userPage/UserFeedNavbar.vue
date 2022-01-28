<template>
  <div id="user-feed-navbar">
    <ul>
      <li class="menu1">
        <router-link :to="{ name: '' }">
          <span class="material-icons" @click="changeMenu(0)"> grid_on </span>
        </router-link>
      </li>
      <li class="menu2">
        <router-link :to="{ name: '' }">
          <span class="material-icons" @click="changeMenu(1)">
            assignment_ind
          </span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "UserFeedNavbar",
  data() {
    return {
      menu: ["menu1", "menu2"],
    };
  },
  methods: {
    changeMenu(num) {
      for (let i = 0; i < 2; i++) {
        // 활성화색으로 바꾸기
        if (num == i) {
          document.querySelector(
            `#user-feed-navbar > ul > .${this.menu[i]}`
          ).style.borderBottomColor = "rgba(0, 0, 0, 0.38)";
          document.querySelector(
            `#user-feed-navbar > ul > .${this.menu[i]} > a > span`
          ).style.color = "rgba(41, 98, 255, 0.87)";
        }
        // 비활성화색으로 바꾸기
        else {
          document.querySelector(
            `#user-feed-navbar > ul > .${this.menu[i]}`
          ).style.borderBottomColor = "rgba(0, 0, 0, 0.1)";
          document.querySelector(
            `#user-feed-navbar > ul > .${this.menu[i]} > a > span`
          ).style.color = "rgba(0, 0, 0, 0.38)";
        }
      }
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Sharp|Material+Icons+Two+Tone|Material+Icons+Outlined");

#user-feed-navbar {
  height: 36px;
}

#user-feed-navbar > ul {
  height: 100%;
  display: flex;
  justify-content: space-around;
}

#user-feed-navbar > ul > li {
  width: 50%;
  padding: 6px 0;
  text-align: center;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#user-feed-navbar > ul > .menu1 {
  border-bottom: 1px solid rgba(0, 0, 0, 0.38);
}

#user-feed-navbar > ul > .menu1 > a > span {
  font-size: 24px;
  color: rgb(41, 98, 255, 0.86);
}

#user-feed-navbar > ul > .menu2 {
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

#user-feed-navbar > ul > .menu2 > a > span {
  font-size: 24px;
  color: rgb(0, 0, 0, 0.38);
}
</style>
