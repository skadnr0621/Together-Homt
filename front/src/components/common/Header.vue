<template>
  <div id="header">
    <img src="@/assets/logo.png" alt="로고" />
    <span class="material-icons-outlined"> menu </span>
  </div>
</template>

<script>
export default {
  name: "Header",
};
</script>

<style>
#header {
  position: fixed;
  top: 0;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  height: 45px;
  width: 100%;
  padding-top: 2px;
  /* padding: 2px 0; */
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#header > img {
  height: 100%;
}

#header > span {
  font-size: 24px;
  text-align: center;
  margin-right: 10px;
  color: rgba(0, 0, 0, 0.87);
}
</style>
