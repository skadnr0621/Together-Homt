<template>
  <div id="register-feed-container">
    <!-- 게시글 내용-->
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
          <img v-if="viewImg" :src="viewImg" alt="피드 사진" />
          <img
            v-else
            src="https://cdn.pixabay.com/photo/2017/02/25/22/55/color-2098963_960_720.png"
            alt="기본 이미지"
            id="upload_file"
          />
        </label>
      </div>
      <div class="feed-content">
        <textarea placeholder="문구 입력..." v-model="content"></textarea>
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
        <div v-for="(tag, index) in tagList" :key="index">
          {{ tag
          }}<span class="material-icons" @click="deleteTag(index)">
            close
          </span>
        </div>
      </div>
    </div>

    <!-- 게시글 설정-->
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
// import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "RegisterFeed",
  props: {
    isRegister: {
      type: Boolean,
    },
    nickname: {
      type: String,
    },
  },
  data() {
    return {
      feedImg: null,
      viewImg: null,
      content: "",
      tag: "",
      tagList: [],
    };
  },
  // computed: {
  //   // 로그인한 사용자 이메일 가져오기
  //   ...mapState({ loginUser: (state) => state.userStore.LoginUser }),

  //   // 로그인한 유저 정보
  //   ...mapState(["myInfo"]),
  // },
  methods: {
    addTag() {
      this.tagList.push("# " + this.tag.replace(/ /gi, ""));
      this.tag = "";
    },
    deleteTag(index) {
      this.tagList.splice(index, 1);
    },
    handleFileChange(event) {
      // png 파일 업로드시 자동 request 요청이 됨... 이상;;;;
      this.feedImg = event.target.files[0];
      console.log(this.feedImg);

      const reader = new FileReader();
      reader.onload = (e) => {
        this.viewImg = e.target.result;
      };
      reader.readAsDataURL(this.feedImg);
    },
  },
  watch: {
    async isRegister(value) {
      if (value) {
        // 게시글 등록하기
        let formData = new FormData();

        formData.append("content", this.content);
        formData.append("tag", this.tag);
        formData.append("media", this.feedImg);
        formData.append("userName", this.nickname);

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
          name: "UserPage",
          params: { userName: this.nickname },
        });
      }
    },
  },
};
</script>

<style>
#register-feed {
  padding: 15px;
  display: flex;
  height: 120px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

#register-feed > .feed-img {
  width: 90px;
  height: 90px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin-right: 15px;
}

#register-feed > .feed-img > label > img {
  width: 100%;
  height: 100%;
}

#register-feed > .feed-content {
  flex: 1 1 auto;
  height: 90px;
}

#register-feed > .feed-content > textarea {
  padding: 0px;
  border: none;
  width: 100%;
  height: 100%;
  font-size: 14px;
  line-height: 20px;
}

/* 태그하기 스타일 */
#register-feed-tag {
  padding: 15px;
  display: flex;
  flex-direction: column;
  /* height: 120px; */
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
}

#register-feed-tag > .feed-tag-input {
  display: flex;
  height: 24px;
  padding: 0 5px;
  margin-bottom: 10px;
  align-items: center;
  border-radius: 5px;
}

#register-feed-tag > .feed-tag-input > span {
  font-size: 18px;
}

#register-feed-tag > .feed-tag-input > input {
  border: none;
  padding: 0 5px;
  flex: 1 1 auto;
}

#register-feed-tag > .feed-tag-list {
  height: 24px;
  align-items: center;
  display: flex;
  white-space: nowrap;
  overflow: auto;
}

#register-feed-tag > .feed-tag-list > div {
  display: flex;
  align-items: center;
  padding: 2px 8px;
  margin-right: 10px;
  background-color: #ebf7ff;
  /* background-color: rgba(0, 0, 0, 0.025); */
  border-radius: 10px;
}

#register-feed-tag > .feed-tag-list > div > span {
  display: inline-block;
  font-size: 14px;
  margin-left: 4px;
}

/* 게시글 설정 */
#register-feed-setting {
  padding: 15px;
  display: flex;
  flex-direction: column;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
}

#register-feed-setting > .container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* margin-bottom: 10px; */
}

#register-feed-setting > .container > .on {
  display: inline-block;
  font-size: 36px;
  line-height: 28px;
  color: rgba(41, 98, 255, 0.87);
}

#register-feed-setting > .container > .off {
  display: inline-block;
  font-size: 36px;
  line-height: 28px;
  color: rgba(0, 0, 0, 0.38);
}
</style>
