<template>
  <div id="follow-list">
    <div class="search">
      <span class="material-icons-outlined"> search </span>
      <input type="text" placeholder="검색" />
    </div>

    <div class="default" v-if="false">
      <span class="material-icons"> person_add </span>
      <h4>팔로잉</h4>
      <p>
        회원님이 다른 사용자를 팔로우하면 <br />여기에 해당 사용자가 표시됩니다.
      </p>
    </div>

    <div class="list" v-else>
      <div class="follow">
        <div class="profile">
          <img src="@/assets/팬다주니어.jpg" alt="프로필 사진" />
        </div>
        <div class="username">penda_jr</div>
        <div class="btn">
          <button>삭제</button>
        </div>
      </div>
      <div class="follow">
        <div class="profile">
          <img src="@/assets/스카피.jpg" alt="프로필 사진" />
        </div>
        <div class="username">scappy</div>
        <div class="btn">
          <button>삭제</button>
        </div>
      </div>
      <div class="follow">
        <div class="profile">
          <img src="@/assets/앙몬드.jpg" alt="프로필 사진" />
        </div>
        <div class="username">ang_mond</div>
        <div class="btn">
          <button>삭제</button>
        </div>
      </div>
      <div class="follow">
        <div class="profile">
          <img src="@/assets/콥.jpg" alt="프로필 사진" />
        </div>
        <div class="username">cob_</div>
        <div class="btn">
          <button>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FollowList",
};
</script>

<style>
/* 다른 사용자가 회원님을 팔로우하면 여기에 해당 사용자가 표시됩니다. */
#follow-list {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0 15px;
}

#follow-list > .search {
  display: flex;
  justify-content: center;
  margin: 10px 0;
  width: 100%;
  height: 34px;
  border-radius: 2px;
}

#follow-list > .search > input {
  background-color: rgba(0, 0, 0, 0.05);
  width: 100%;
  height: 100%;
  border: none;
}

#follow-list > .search > span {
  background-color: rgba(0, 0, 0, 0.05);
  padding: 5px;
  width: 34px;
  height: 34px;
  font-size: 24px;
  color: rgba(0, 0, 0, 0.87);
}

#follow-list > .default {
  display: flex;
  flex-direction: column;
  text-align: center;
}

#follow-list > .default > *:first-child {
  margin-top: 25%;
}

#follow-list > .default > * {
  margin-top: 5px;
}

#follow-list > .default > span {
  font-size: 64px;
  color: rgba(0, 0, 0, 0.87);
}

#follow-list > .default > p {
  font-size: 12px;
  color: rgba(0, 0, 0, 0.38);
}

#follow-list > .default > h4 {
  color: rgba(0, 0, 0, 0.87);
}

#follow-list > .list > .follow {
  display: flex;
  flex-direction: row;
  align-items: center;
  font-weight: bold;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.87);
  margin: 15px 0;
}

#follow-list > .list > .follow:first-child {
  margin: 0;
}

#follow-list > .list > .follow > .profile {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#follow-list > .list > .follow > .profile > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#follow-list > .list > .follow > .username {
  margin-left: 10px;
}

#follow-list > .list > .follow > .btn {
  margin-left: auto;
}

#follow-list > .list > .follow > .btn > button {
  padding: 4px 6px;
  background-color: #ffffff;
  color: rgba(0, 0, 0, 0.87);
  font-weight: bold;
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
