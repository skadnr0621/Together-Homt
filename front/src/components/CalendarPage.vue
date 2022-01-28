<template>
  <div>
    <v-calendar :attributes="attributes">
      <template #day-popover>
        <div>Using my own content now</div>
      </template>
    </v-calendar>
  </div>
</template>

<script>
export default {
  name: "CalendarPage",
  data() {
    const todos = [{}];
    return {
      incId: todos.length,
      todos,
    };
  },
  computed: {
    attributes() {
      return [
        ...this.todos.map((todo) => ({
          dates: { weekdays: 6 },
          dot: {
            color: todo.color,
            class: todo.isComplete ? "opacity-75" : "",
          },
          popover: true,
          customData: todo,
        })),
      ];
    },
  },
};
</script>

<style></style>
