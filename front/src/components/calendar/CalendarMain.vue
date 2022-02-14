<template>
  <div id="calendar-main">
    <div class="calendar">
      <v-calendar :attributes="attrs" @dayclick="onDayClick" is-expanded />
    </div>
    <div class="record" v-if="content">
      <div>
        {{ content.date }}
      </div>
      <div>
        {{ content.exercise }}
      </div>
    </div>
    <div class="record" v-else>운동 기록 없음</div>
  </div>
</template>

<script>
export default {
  name: "CalendarMain",
  data() {
    return {
      content: null,
      attrs: [
        {
          highlight: {
            color: "blue",
            fillMode: "light",
          },
          dates: [new Date(2022, 1, 10)],
        },
        {
          highlight: {
            color: "blue",
            fillMode: "light",
          },
          dates: [new Date(2022, 1, 11)],
        },
        {
          highlight: {
            color: "red",
            fillMode: "light",
          },
          dates: [new Date(2022, 1, 14)],
        },
      ],
      records: [
        {
          date: "2022-2-10",
          exercise: "스쿼트",
        },
        {
          date: "2022-2-11",
          exercise: "런지",
        },
      ],
    };
  },
  methods: {
    onDayClick(day) {
      const index = this.records.findIndex(
        (record) => record.date == day.year + "-" + day.month + "-" + day.day
      );
      this.content = this.records[index];
    },
  },
};
</script>

<style></style>
