<template>
  <div id="follow-navbar">
    <ul>
      <li class="following">3 팔로잉</li>
      <li class="follower">4 팔로워</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "FollowNavbar",
};
</script>

<style>
#follow-navbar {
  height: 30px;
  /* border: 1px solid black; */
}

#follow-navbar > ul {
  display: flex;
  height: 100%;
  width: 100%;
}

#follow-navbar > ul > li {
  padding-bottom: 5px;
  font-size: 14px;
  text-align: center;
  width: 50%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  /* border-bottom: 1px solid black; */
}

#follow-navbar > ul > .following {
  border-bottom: 1px solid rgba(0, 0, 0, 0.87);
  color: rgba(0, 0, 0, 0.87);
  font-weight: bold;
}

#follow-navbar > ul > .follower {
  color: rgba(0, 0, 0, 0.38);
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
