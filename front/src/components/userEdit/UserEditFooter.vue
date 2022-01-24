<template>
  <div id="user-edit-footer">
    <router-link :to="{ name: '' }">비밀번호 변경</router-link>
    <a>회원 탈퇴</a>
  </div>
</template>

<script>
export default {
  name: "UserEditFooter",
};
</script>

<style>
#user-edit-footer {
  position: fixed;
  bottom: 45px;
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin: 10px 0;
}

#user-edit-footer > * {
  margin-right: 10px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.38);
  /* font-weight: bold; */
}
</style>
