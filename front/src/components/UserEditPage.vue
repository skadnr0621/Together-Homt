<template>
  <div id="user-edit-page">
    <user-edit-header v-on:goBack="onGoBack" />
    <user-edit />
    <user-edit-footer />
  </div>
</template>

<script>
import UserEditHeader from "@/components/userEdit/UserEditHeader";
import UserEdit from "@/components/userEdit/UserEdit";
import UserEditFooter from "@/components/userEdit/UserEditFooter";

export default {
  name: "UserEditPage",
  components: {
    UserEditHeader,
    UserEdit,
    UserEditFooter,
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
  },
};
</script>

<style>
#user-edit-page {
  position: fixed;
  top: 45px;
  left: 0;
  right: 0;
  bottom: 45px;
  overflow: auto;
}
</style>
