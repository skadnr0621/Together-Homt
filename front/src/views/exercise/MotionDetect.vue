<template>
<div class="motion-detect">
  <div class="container">
    <div id="exercise-header">
      <router-link :to="{ name: 'ExerciseList'}">
        <span class="back-btn material-icons-outlined">keyboard_arrow_left</span>
      </router-link>
      <p class="exercise-title">Let's <strong>{{exercise}}</strong></p>
      <button class="is-exercise fail-btn" @click="doneExercise()" >미완료</button>
    </div>

    <div><canvas id="canvas"></canvas></div>
    <div id="exercise-gage">
      <div id="label-container"></div>
      <progress id="percent" :value="percent" max="1.00"></progress>  
    </div>
  </div>

  <!-- countdown -->
  <!-- <div id="wrap">
    <div class="c"></div>
    <div class="o"></div>
    <div class="u"></div>
    <div class="n"></div>
    <div class="t"></div>
  </div>
  <svg>
  <defs>
    <filter id="filter">
      <feGaussianBlur in="SourceGraphic" stdDeviation="18" result="blur" />
      <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 28 -10" result="filter" />
      <feComposite in="SourceGraphic" in2="filter" operator="atop" />
    </filter>
  </defs>
</svg> -->
</div>
</template>

<script>
import * as tmPose from "@teachablemachine/pose";
import "@/components/css/motiondetect.scss";

const URL = "https://teachablemachine.withgoogle.com/models/76bwaNQBY/";
let model, webcam, ctx, labelContainer, maxPredictions;

export default {
  data() {
    return {
      exercise: this.$route.query.exercise,
      status: "",
      percent: null,
      success: false,
    }
  },
  watch: {
    success: function() {
      const btnStatus = document.querySelector('.is-exercise')
      btnStatus.classList.remove('fail-btn')
      btnStatus.classList.add('success-btn')
      btnStatus.innerHTML = '완료'
    }
  },

  created() {
  },
  mounted() {
    // this.ready();
    this.init();
  },

  methods: {
    async init() {
    const modelURL = URL + "model.json";
    const metadataURL = URL + "metadata.json";

    model = await tmPose.load(modelURL, metadataURL);
    maxPredictions = model.getTotalClasses();


    const size = 400;
    const flip = true; // whether to flip the webcam
    webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam.setup(); // request access to the webcam
    await webcam.play();


    window.requestAnimationFrame(this.loop);

    const canvas = document.getElementById("canvas");
    canvas.width = 300
    canvas.height = size
    ctx = canvas.getContext("2d");
    labelContainer = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions; i++) {
        labelContainer.appendChild(document.createElement("div"));
      }
    },

    async loop() {
      webcam.update();
      await this.predict();
      window.requestAnimationFrame(this.loop);
    },


    
    async predict() {
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      const prediction = await model.predict(posenetOutput);

      // 0 : defalut, 1: hi
      if (prediction[0].probability.toFixed(2) == 1.00) {
        this.status = "default"
      } else if (prediction[1].probability.toFixed(2) == 1.00) {
        if (this.status == "default") {
          this.success = true
        }
        this.status="hi"
      }
      
      // 클래스명 : 어느정도(최대 1)
      // const classPrediction =
      //     prediction[1].className + ": " + prediction[1].probability.toFixed(2);
      // labelContainer.childNodes[1].innerHTML = classPrediction;
      this.percent = prediction[1].probability.toFixed(2);

      this.drawPose(pose);
    },

    drawPose(pose) {
      if (webcam.canvas) {
        ctx.drawImage(webcam.canvas, 0, 0, webcam.canvas.width , webcam.canvas.height);
        // draw the keypoints and skeleton
        if (pose) {
          const minPartConfidence = 1;
          tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
          tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
       }
      }
    },

    
    doneExercise() {
      // DB로 보내기
      if (this.success) {
        // axisos
        this.$router.push({ name: 'exerciseList'})
      }
      else {
        alert('운동을 완료해주세요')
      }
      
    },



    // ready() {
    //   var i = 3;
    //   let wrap = document.querySelector('#wrap')
    //   function countdown(){
    //     if (i < 0) {
    //       i = 3;
    //       setTimeout(function(){
    //         countdown();
    //       }, 2000);
    //       return false;
    //     }
    //     wrap.classList.remove("class");
    //     setTimeout(function(){
    //       wrap.classList.add('wrap-' + i);
    //       setTimeout(function(){
    //         i--;
    //         countdown();
    //       }, 1000);
    //     }, 600);
    //   }
    //   countdown();
    //   setTimeout(()=>{
    //     this.init();
    //   },2500)
    // }
  }
}


</script>


<style>

</style>