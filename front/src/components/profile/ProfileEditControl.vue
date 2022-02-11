<template>
  <div id="profile-edit-control">
    <div class="edit-btn">
      <router-link :to="{ name: 'ProfileEdit' }">프로필 편집 </router-link>
    </div>
    <div class="calendar-btn">
      <router-link
        :to="{ name: 'CalendarPage', params: { userName: `유저네임` } }"
      >
        <span class="material-icons"> event_available </span>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileEditControl",
};
</script>

<style></style>
