<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  display: flex;
  justify-content: center;
  height: 640px;
}

a {
  text-decoration: none;
  color: rgba(0, 0, 0, 0.87);
}

a:visited {
  color: rgba(0, 0, 0, 0.87);
}

ul {
  list-style: none;
}
</style>
