<template>
<div class="excerciselist">
  <button @click="exercise('hi')">hi운동</button>
  <button @click="exercise('neck')">목 스트레칭</button>
  <button @click="exercise('waist')">허리 스트레칭</button>
</div>
</template>

<script>
export default {
  methods: {
    exercise: function(exercise) {
      this.$router.push({ name: 'MotionDetect', query: { exercise: exercise}})
    }
  },
}
</script>

<style>
.excerciselist {
  margin: 100px;
}
</style>