<template>
<div class="excerciselist">
  <button @click="exercise('hi')">hi운동</button>
</div>
</template>

<script>
export default {
  methods: {
    exercise: function(exercise) {
      this.$router.push({ name: 'MotionDetect', query: { exercise: exercise}})
    }
  }
}
</script>

<style>
.excerciselist {
  margin: 100px;
}
</style>