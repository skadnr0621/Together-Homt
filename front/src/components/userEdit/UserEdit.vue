<template>
  <div id="user-edit">
    <div class="profile">
      <div class="view">
        <img v-if="viewProfile" :src="viewProfile" alt="프로필 사진" />
        <img
          v-else
          src="https://cdn.pixabay.com/photo/2022/01/29/08/40/08-40-47-951_960_720.png"
          alt="프로필 사진"
        />
      </div>
      <div class="edit">
        <label for="upload_profile">프로필 사진 바꾸기</label>
        <input
          type="file"
          id="upload_profile"
          @change="handleFileChange($event)"
          style="display: none"
          accept="image/*"
        />
      </div>
    </div>

    <div class="info">
      <div class="email">
        <span>이메일</span>
        <input type="text" v-model="myInfo.email" />
      </div>
      <div class="username">
        <span>닉네임</span>

        <input type="text" v-model="nickname" />
      </div>
      <div class="introduction">
        <span>자기소개</span>
        <textarea rows="3" v-model="introduction" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

import { mapState } from "vuex";

export default {
  name: "UserEdit",
  data() {
    return {
      viewProfile: null,
      profile: null,
      nickname: "",
      introduction: "",
    };
  },
  props: {
    // editInfo: {
    //   type: Object,
    //   required: true,
    // },
    isEdit: {
      type: Boolean,
    },
  },
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
  methods: {
    handleFileChange(event) {
      this.profile = event.target.files[0];
      console.log(this.profile);

      const reader = new FileReader();
      reader.onload = (e) => {
        this.viewProfile = e.target.result;
      };
      reader.readAsDataURL(this.profile);
    },
  },
  created() {
    this.viewProfile = this.myInfo.profile_url;
    this.nickname = this.myInfo.username;
    this.introduction = this.myInfo.introduce;
  },
  watch: {
    async isEdit(value) {
      if (value) {
        let formData = new FormData();

        formData.append("username", this.nickname);
        formData.append("introduce", this.introduction);

        if (this.profile) {
          console.log("들어옴");
          formData.append("media", this.profile);
        }

        // 프로필 편집하기
        await axios
          .put(`/user/profile/update`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: sessionStorage.getItem("jwt"),
            },
          })
          .then((res) => {
            alert("변경 완료");
            console.log(res);
          })
          .catch((err) => {
            alert("변경 실패");
            console.log(err.response.data.msg);
          });
        this.$router.push({
          name: "UserPage",
          params: { userName: this.nickname },
        });
      }
    },
  },
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

#user-edit > .profile > * {
  margin: 4px;
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
