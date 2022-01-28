<template>
  <div id="user-profile">
    <div class="profile">
      <img src="@/assets/죠르디2.png" alt="프로필 사진" />
    </div>
    <div class="name">@i.m-cherry</div>
  </div>
</template>

<script>
export default {
  name: "UserProfile",
};
</script>

<style>
#user-profile {
  height: 110px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin-top: 10px;
  /* position: relative; */
  /* margin-bottom: 4px; */
}

#user-profile > .profile {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

#user-profile > .profile > img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#user-profile > .name {
  font-weight: bold;
  color: rgba(0, 0, 0, 0.87);
}
</style>
