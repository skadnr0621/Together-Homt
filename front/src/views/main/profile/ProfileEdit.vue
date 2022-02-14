<template>
  <div id="profile-edit">
    <profile-edit-header
      v-on:goBack="onGoBack"
      v-on:editProfile="onEditProfile"
    />
    <profile-edit-main :isEdit="isEdit" />
    <profile-edit-footer />
  </div>
</template>

<script>
import ProfileEditHeader from "@/components/profileEdit/ProfileEditHeader";
import ProfileEditMain from "@/components/profileEdit/ProfileEditMain";
import ProfileEditFooter from "@/components/profileEdit/ProfileEditFooter";

import "@/assets/css/profileEdit.css";

export default {
  name: "ProfileEdit",
  components: {
    ProfileEditHeader,
    ProfileEditMain,
    ProfileEditFooter,
  },
  data() {
    return {
      isEdit: false,
    };
  },
  methods: {
    onGoBack() {
      this.$router.back();
    },
    onEditProfile(value) {
      this.isEdit = value;
    },
  },
};
</script>

<style></style>
