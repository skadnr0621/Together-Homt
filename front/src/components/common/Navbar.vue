<template>
  <div id="navbar">
    <ul>
      <li class="menu-feed">
        <router-link :to="{ name: 'Feed' }">
          <span class="material-icons" @click="changeMenu(0)"> home </span>
        </router-link>
      </li>
      <li class="menu-search">
        <router-link :to="{ name: 'searchPage' }">
          <span class="material-icons" @click="changeMenu(1)"> search </span>
        </router-link>
      </li>
      <li class="menu-add">
        <router-link
          :to="{
            name: 'RegisterFeedPage',
            params: { userName: myInfo.username },
          }"
        >
          <span class="material-icons-outlined" @click="changeMenu(2)">
            add_box
          </span>
        </router-link>
      </li>
      <li class="menu-homt">
        <router-link :to="{ name: 'ExerciseList' }">
          <span class="material-icons-outlined" @click="changeMenu(3)">
            fitness_center
          </span>
        </router-link>
      </li>
      <li class="menu-my">
        <router-link
          :to="{ name: 'UserPage', params: { userName: myInfo.username } }"
        >
          <span class="material-icons" @click="changeMenu(4)">
            account_circle
          </span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import "@/components/css/common.css";

import { mapState } from "vuex";

export default {
  name: "Navbar",
  data() {
    return {
      menu: ["menu-feed", "menu-search", "menu-add", "menu-homt", "menu-my"],
    };
  },
  methods: {
    // 이전값이랑 비교?
    changeMenu(num) {
      for (let i = 0; i < 5; i++) {
        // 활성화색으로 바꾸기
        if (num == i) {
          document.querySelector(
            `#navbar > ul > .${this.menu[i]}`
          ).style.borderTopColor = "rgba(41, 98, 255, 0.87)";
          document.querySelector(
            `#navbar > ul > .${this.menu[i]} > a > span`
          ).style.color = "rgba(41, 98, 255, 0.87)";
        }
        // 비활성화색으로 바꾸기
        else {
          document.querySelector(
            `#navbar > ul > .${this.menu[i]}`
          ).style.borderTopColor = "rgba(0, 0, 0, 0.1)";
          document.querySelector(
            `#navbar > ul > .${this.menu[i]} > a > span`
          ).style.color = "rgba(0, 0, 0, 0.87)";
        }
      }
    },
  },
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
};
</script>

<style>
#navbar {
  position: fixed;
  bottom: 0;
  height: 46px;
  width: 100%;
  /* border-top: 1px solid rgba(0, 0, 0, 0.1); */
}

#navbar > ul {
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#navbar > ul > li {
  text-align: center;
  height: 100%;
  padding: 11px;
  width: 25%;
}

#navbar > ul > li > a > span {
  font-size: 24px;
}

#navbar > ul > * {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#navbar > ul > * > a > span {
  color: rgba(0, 0, 0, 0.87);
}

#navbar > ul > .menu-feed {
  border-top: 1px solid rgba(41, 98, 255, 0.87);
}

#navbar > ul > .menu-feed > a > span {
  color: rgba(41, 98, 255, 0.87);
}
</style>
