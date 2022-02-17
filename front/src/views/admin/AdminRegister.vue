<template>
  <div>
    <div @click="$router.go(-1)">back</div>
    <h1>AdminRegister</h1>
    <div>
    <canvas id="canvas" width="auto" height="auto"></canvas>
    <button @click="attendance()">미퇴실 명단</button>
    <img id="image" src="" alt="">
</div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "AdminRegister",
  data(){
    return {
      absent : [],
    }
  },
  methods: {
    attendance() {
      axios({
        method: 'get',
        url: '/exercise/attendance/absentees',
        headers: {
          Authorization : sessionStorage.getItem("jwt")
        }
      })
      .then((res) => {
        var arr = []
        for (let student of res.data) {
          arr.push(student.username)
        }
        this.absent = arr
        console.log(this.absent)
        this.draw()
      })
    },
    
    draw() {
      var canvas = document.getElementById('canvas');
      if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        ctx.font = '20px serif';
        ctx.fillStyle = 'rgb(204,229,255)';
        ctx.fillRect(0, 0, 200, 500);
        ctx.fillStyle = 'rgb(0,0,0)'
        ctx.fillText(this.absent, 80, 80);
      }
      this.saveImage();
    },

    saveImage() {
      var link = document.createElement('a');
      link.download = "미퇴실명단.png"
      link.href = document.getElementById('canvas').toDataURL('image/png')
      link.click();
    }
  }
}
</script>

<style>

</style>