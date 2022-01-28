<template>
<div>
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
</style>