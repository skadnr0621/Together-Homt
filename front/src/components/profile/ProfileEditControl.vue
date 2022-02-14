<template>
  <div id="profile-edit-control">
    <div class="edit-btn">
      <router-link :to="{ name: 'ProfileEdit' }">프로필 편집 </router-link>
    </div>
    <div class="calendar-btn">
      <router-link
        :to="{
          name: 'Calendar',
          params: {
            userName: $route.params.userName,
            email: $route.params.email,
          },
        }"
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
