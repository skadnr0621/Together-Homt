<template>
  <div id="navbar">
    <ul>
      <li class="menu-feed">
        <router-link
          :to="{ name: 'Feed' }"
          v-on:click.prevent="changeMenu('feed')"
        >
          <span class="material-icons"> home </span>
        </router-link>
      </li>
      <li class="menu-search">
        <router-link :to="{ name: '' }">
          <span class="material-icons"> search </span>
        </router-link>
      </li>
      <li class="menu-add">
        <router-link :to="{ name: '' }">
          <span class="material-icons-outlined"> add_box </span>
        </router-link>
      </li>
      <li class="menu-homt">
        <router-link :to="{ name: '' }">
          <span class="material-icons-outlined"> fitness_center </span>
        </router-link>
      </li>
      <li class="menu-my">
        <router-link :to="{ name: 'UserPage', params: { userName: '죠르디' } }">
          <span class="material-icons"> account_circle </span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  // methods: {
  //   changeMenu(menu) {},
  // },
  // mounted() {
  //   console.log(this.$route.name);
  // },
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

#navbar > ul > li > a:hover {
}

#navbar > ul > li > a > span {
  font-size: 24px;
}

#navbar > ul > .menu-my > a > span {
  color: rgba(41, 98, 255, 0.86);
}

#navbar > ul > .menu-my {
  border-top: 1px solid rgba(41, 98, 255, 0.86);
}

#navbar > ul > .menu-feed {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#navbar > ul > .menu-search {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#navbar > ul > .menu-add {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#navbar > ul > .menu-homt {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
