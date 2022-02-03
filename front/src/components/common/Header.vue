<template>
  <div id="header">
    <img src="@/assets/logo.png" alt="로고" />
    <div>
      <span class="btn js-click-modal material-icons-outlined"> menu </span>
      <!-- <div class="modal">
        <ul class="body">
          <li>설정</li>
          <li>보관</li>
        </ul>
        <div class="header">설정 목록</div>
        <a class="btn js-close-modal">X</a>
      </div> -->
    </div>
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
