<template>
  <div id="register-main">
    <!-- 피드에 등록할 이미지와 내용-->
    <div id="register-feed">
      <div class="feed-img">
        <input
          type="file"
          id="upload_file"
          @change="handleFileChange($event)"
          style="display: none"
          accept="image/*"
        />
        <label for="upload_file">
          <img v-if="viewImg" :src="viewImg" alt="피드 사진" id="upload_file" />
          <img
            v-else
            src="https://cdn.pixabay.com/photo/2017/02/25/22/55/color-2098963_960_720.png"
            alt="기본 이미지"
            id="upload_file"
          />
        </label>
      </div>
      <div class="feed-content">
        <textarea
          placeholder="문구 입력..."
          v-model="postData.content"
        ></textarea>
      </div>
    </div>

    <!-- 키워드 태그 하기 -->
    <div id="register-feed-tag">
      <div class="feed-tag-input">
        <span class="material-icons"> local_offer </span>
        <input
          @keyup.enter="addTag()"
          type="text"
          placeholder="키워드 태그하기..."
          v-model="tag"
        />
      </div>
      <div class="feed-tag-list">
        <div v-for="(tag, index) in postData.tags" :key="index">
          {{ tag }}
          <span class="material-icons" @click="deleteTag(index)"> close </span>
        </div>
      </div>
    </div>

    <!-- 피드 타입 설정-->
    <div id="register-feed-setting">
      <div class="container">
        일상 피드
        <span class="material-icons on"> toggle_on </span>
      </div>
      <div class="container">
        나만의 운동
        <span class="material-icons off"> toggle_off </span>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "RegisterMain",
  props: {
    isRegister: {
      type: Boolean,
    },
  },
  data() {
    return {
      viewImg: null,
      tag: null,

      postData: {
        content: null,
        tags: [],
        media: null,
      },
    };
  },
  computed: {
    // 내 정보
    ...mapState(["myInfo"]),
  },
  methods: {
    addTag() {
      this.postData.tags.push(this.tag.replace(/ /gi, ""));
      this.tag = "";
    },
    deleteTag(index) {
      this.postData.tags.splice(index, 1);
    },
    handleFileChange(event) {
      this.postData.media = event.target.files[0];
      console.log(this.postData.media);

      const reader = new FileReader();
      reader.onload = (e) => {
        this.viewImg = e.target.result;
      };
      reader.readAsDataURL(this.postData.media);
    },
  },
  watch: {
    // 게시글 등록하기
    async isRegister(value) {
      if (value) {
        const userName = this.myInfo.username;
        console.log(this.myInfo.username);
        let formData = new FormData();

        formData.append("content", this.postData.content);
        formData.append("nameList", this.postData.tags.join(", "));
        formData.append("media", this.postData.media);
        formData.append("userName", userName);

        console.log(formData);

        await axios
          .post("/feed/create", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: sessionStorage.getItem("jwt"),
            },
          })
          .then((res) => {
            alert("게시글 등록 완료!");
            console.log(res);
          })
          .catch((err) => {
            alert("게시글 등록 실패");
            console.log(err);
          });

        this.$router.push({
          name: "Profile",
          params: { userName: this.myInfo.username, email: this.myInfo.email },
        });
      }
    },
  },
};
</script>

<style></style>
