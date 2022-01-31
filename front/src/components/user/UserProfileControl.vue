<template>
  <div id="user-profile-control">
    <div class="edit-btn">
      <router-link
        :to="{ name: 'UserEditPage', params: { userName: '죠르디' } }"
        >프로필 편집
      </router-link>
    </div>
    &nbsp;
    <div class="calendar-btn">
      <img src="@/assets/icon/calendar.svg" alt="달력" />
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfileControl",
};
</script>

<style>
#user-profile-control {
  height: 30px;
  margin-top: 15px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
}

#user-profile-control > .edit-btn {
  padding: 4px 46px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}

#user-profile-control > .calendar-btn {
  padding: 4px 4px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 2px;
  height: 100%;
}

#user-profile-control > .calendar-btn > img {
  height: 100%;
}
</style>
