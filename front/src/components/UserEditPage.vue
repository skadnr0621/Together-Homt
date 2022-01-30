<template>
  <div id="user-edit-page">
    <user-edit-header v-on:goBack="onGoBack" v-on:editProfile="onEditProfile" />
    <user-edit :editInfo="myInfo" :isEdit="isEdit" />
    <user-edit-footer :nickname="myInfo.username" />
  </div>
</template>

<script>
import UserEditHeader from "@/components/userEdit/UserEditHeader";
import UserEdit from "@/components/userEdit/UserEdit";
import UserEditFooter from "@/components/userEdit/UserEditFooter";

import { mapState } from "vuex";

export default {
  name: "UserEditPage",
  components: {
    UserEditHeader,
    UserEdit,
    UserEditFooter,
  },
  data() {
    return {
      isEdit: false,
      // editInfo: {
      //   profile: null,
      //   email: null,
      //   nickname: null,
      //   introduction: null,
      // },
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
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  created() {
    // this.editInfo.profile = this.myInfo.profile_url
    //   ? this.myInfo.profile_url
    //   : "@/assets/profileBasic.png";
    // this.editInfo.profile = "@/profileBasic.png";
    // this.editInfo.email = this.myInfo.email;
    // this.editInfo.nickname = this.myInfo.username;
    // this.editInfo.introduction = this.myInfo.introduce;
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
