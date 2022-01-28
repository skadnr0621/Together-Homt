<template>
  <div id="user-profile-control">
    <div class="edit-btn">
      <router-link
        :to="{ name: 'UserEditPage', params: { userName: myInfo.username } }"
        >프로필 편집
      </router-link>
    </div>
    <div class="calendar-btn">
      <router-link
        :to="{ name: 'CalendarPage', params: { userName: myInfo.username } }"
      >
        <span class="material-icons"> event_available </span>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserProfileControl",
  computed: {
    // 로그인한 유저 정보
    ...mapState(["myInfo"]),
  },
};
</script>

<style>
#user-profile-control {
  width: 100%;
  height: 30px;
  margin-top: 15px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgba(0, 0, 0, 0.87);
}

#user-profile-control > .edit-btn {
  height: 100%;
  width: 40%;
  padding: 4px 0;
  /* padding: 4px 46px; */
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 2px;
  font-size: 14px;
  margin-right: 5px;
}

#user-profile-control > .calendar-btn {
  margin-left: 5px;
  height: 100%;
  width: 30px;
  padding: 3px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 2px;
  height: 100%;
}

#user-profile-control > .calendar-btn > a > span {
  font-size: 24px;
  color: rgba(0, 0, 0, 0.87);
}
</style>
