<template>
  <div>
    <!-- <nav class="navbar navbar-light">
      <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">투게더 홈트</a>
        <button class="btn btn-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div style="width: 45%;" class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
          <div class="offcanvas-header">
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">설정</a>
                <a class="nav-link" aria-current="page" href="#">보관</a>
                <a class="nav-link" aria-current="page" href="#">사람 찾아보기</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav> -->
  <div id="header">
    <img src="@/assets/logo.png" alt="로고" />
    <div>
      <span class="btn js-click-modal material-icons-outlined"> menu </span>
      <div class="modal">
        <ul class="body">
          <li>설정</li>
          <li>보관</li>
        </ul>
        <div class="header">설정 목록</div>
        <a class="btn js-close-modal">X</a>
      </div>
    </div>
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
