<template>
  <div id="user-feed-list">
    <div class="feed">feed1</div>
    <div class="feed">feed2</div>
    <div class="feed">feed3</div>
    <div class="feed">feed4</div>
    <div class="feed">feed5</div>
    <div class="feed">feed6</div>
    <div class="feed">feed6</div>
    <div class="feed">feed7</div>
    <div class="feed">feed8</div>
    <div class="feed">feed9</div>
    <div class="feed">feed10</div>
    <div class="feed">feed11</div>
    <div class="feed">feed12</div>
  </div>
</template>

<script>
export default {
  name: "UserFeedList",
};
</script>

<style>
#user-feed-list {
  margin: 2px 0;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  width: 100%;
  gap: 1px 1px;
}

#user-feed-list > .feed {
  border: 1px solid rgba(0, 0, 0, 0.1);
  aspect-ratio: 1;
}
</style>
