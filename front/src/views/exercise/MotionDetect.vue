<template>
<div class="motion-detect">
  <div class="container">
    <div id="exercise-header">
      <div @click="goOut()">
        <span class="back-btn material-icons-outlined">keyboard_arrow_left</span>
      </div>
      <p class="exercise-title">Let's <strong>{{exercise}}</strong></p>
      <button class="is-exercise fail-btn" @click="doneExercise()" >미완료</button>
    </div>

    <div v-if="webcam != null && model != null"><canvas id="canvas"></canvas></div>
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

let ctx, labelContainer, maxPredictions;

export default {
  data() {
    return {
      exercise: this.$route.query.exercise,
      status: "default",
      percent: null,
      percent2: null,
      success: false,
      
      webcam: null,
      model: null,

      leftCnt: 0,

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
    this.setModel();
  },

  methods: {
    goOut() {
      this.$router.push({ name: 'ExerciseList'})
      this.webcam.stop();
      this.webcam = null;
    },

    async setModel() {
      if (this.exercise == 'hi'){ // hi
        this.URL = "https://teachablemachine.withgoogle.com/models/76bwaNQBY/";
      }
      else if (this.exercise == 'neck'){ // 목 스트레칭
        this.URL = "https://teachablemachine.withgoogle.com/models/XoVPimrL9/";
      }
      else if (this.exercise == 'waist'){ // 허리 스트레칭
        this.URL = "https://teachablemachine.withgoogle.com/models/yKKZxJXRk/";
      }
      else if (this.exercise == 'arm') { // 팔 뻗기
        this.URL = "";
      }
      else if (this.exercise == 'squat') { // 스쿼트
        this.URL = "";
      }
      

      const modelURL = this.URL + "model.json";
      const metadataURL = this.URL + "metadata.json";

      this.model = await tmPose.load(modelURL, metadataURL);
      this.init();
    },


    async init() {
      const size = 400;
      const flip = true; // whether to flip the webcam
      this.webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
      await this.webcam.setup(); // request access to the webcam
      await this.webcam.play();


      window.requestAnimationFrame(this.loop);

      const canvas = document.getElementById("canvas");
      canvas.width = 300
      canvas.height = size
      ctx = canvas.getContext("2d");
      labelContainer = document.getElementById("label-container");

      maxPredictions = this.model.getTotalClasses();
      for (let i = 0; i < maxPredictions; i++) {
          labelContainer.appendChild(document.createElement("div"));
        }
    },

    async loop() {
      if (this.webcam) {
        this.webcam.update();
      
        if (this.exercise == 'hi') {
          await this.hiPredict();
        }
        else if (this.exercise == 'neck' || this.exercise == 'waist') {
          await this.neckwaistPredict();
        }
        window.requestAnimationFrame(this.loop);
      }
    },


    
    async hiPredict() {
      const { pose, posenetOutput } = await this.model.estimatePose(this.webcam.canvas);
      const prediction = await this.model.predict(posenetOutput);

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

    async neckwaistPredict() {
      const { pose, posenetOutput } = await this.model.estimatePose(this.webcam.canvas);
      const prediction = await this.model.predict(posenetOutput);


      // 0 : defalut, 1: left, 2: right
      if (prediction[0].probability.toFixed(2) == 1.00) {
        this.status = "default"
      } else if (prediction[1].probability.toFixed(2) == 1.00) {
        if (this.status == "default") {
          this.leftCnt = 1
        }
        this.status = "left"
      } else if (prediction[2].probability.toFixed(2) == 1.00) {
        if (this.leftCnt != 0 && this.status == "default") {
          this.success = true
          this.leftCnt = 0
        }
        this.status = "right"
      }

      if (this.leftCnt == 0 || this.status == 'left') {
        labelContainer.innerHTML = 'left';
        this.percent = prediction[1].probability.toFixed(2);
      } else if(this.leftCnt != 0 && this.status == 'default') {
        labelContainer.innerHTML = 'right';
        this.percent = prediction[2].probability.toFixed(2);
      }

      this.drawPose(pose);
    },


    drawPose(pose) {
      if (this.webcam && this.webcam.canvas) {
        ctx.drawImage(this.webcam.canvas, 0, 0);
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
        this.$router.push({ name: 'ExerciseList'})
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