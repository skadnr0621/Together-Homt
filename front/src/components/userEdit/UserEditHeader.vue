<template>
  <div id="user-edit-header">
    <div class="cancel" v-on:click="goBack">취소</div>
    <div class="context">프로필 편집</div>
    <div class="done">완료</div>
  </div>
</template>

<script>
export default {
  name: "UserEditHeader",
  methods: {
    goBack() {
      this.$emit("goBack");
    },
  },
};
</script>

<style>
#user-edit-header {
  display: flex;
  height: 40px;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  padding: 0 10px;
}

#user-edit-header > .cancel,
.done {
  font-size: 15px;
}

#user-edit-header > .done {
  color: rgba(41, 98, 255, 0.86);
}

#user-edit-header > .context {
  font-weight: bold;
}
</style>
