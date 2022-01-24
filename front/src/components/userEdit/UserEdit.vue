<template>
  <div id="user-edit">
    <div class="profile">
      <div class="view">
        <img src="@/assets/죠르디2.png" alt="프로필 사진" />
      </div>
      <div class="edit">프로필 사진 바꾸기</div>
    </div>

    <div class="info">
      <div class="email">
        <span>이메일</span>
        <input type="text" value="im-cherry" />
      </div>
      <div class="username">
        <span>닉네임</span>

        <input type="text" value="i.m-cherry" />
      </div>
      <div class="introduction">
        <span>자기소개</span>
        <textarea rows="3">
안녕하세요!
운동 시작한지 한달도 안된 헬린이입니다!</textarea
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserEdit",
};
</script>

<style>
#user-edit {
  width: 100%;
  /* display: flex;
  flex-direction: column;
  align-items: center; */
}

#user-edit > .profile {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2px 0;
  font-size: 12px;
  color: rgba(41, 98, 255, 0.86);
  margin: 8px 0;
}

#user-edit > .profile > * {
  margin: 4px;
}
#user-edit > .profile > .view {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#user-edit > .profile > .view > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#user-edit > .info {
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 10px 10px;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

#user-edit > .info > div {
  padding: 5px 0;
  width: 100%;
}

#user-edit > .info > div > * {
  margin: 3px 0;
}

#user-edit > .info > div > span {
  width: 80px;
  display: inline-block;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
}

#user-edit > .info > div > input,
textarea {
  font-size: 14px;
  width: 100%;
  height: 30px;
  display: inline-block;
  border: 1px solid rgba(0, 0, 0, 0.1);
  padding-left: 10px;
  color: rgba(0, 0, 0, 0.87);
  border-radius: 2px;
}

#user-edit > .info > div > textarea {
  line-height: 20px;
  height: 100%;
  white-space: pre-line;
}
</style>
