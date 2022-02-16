<template>

    <!-- <div class="bg">
      <div class="circle-blue right-1"></div>
      <div class="circle-big"></div>
      <div class="circle-blue left-2"></div>
      <div class="circle-yellow left-1"></div>
      <div class="circle-yellow right-4"></div>
      <div class="circle-green left-3"></div>
      <div class="circle-green right-2"></div>
      <div class="circle-pink right-3"></div>
    </div> -->
    <div id="app">
      <router-view />
    </div>

</template>

<script>
import "@/assets/css/style.css"

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
