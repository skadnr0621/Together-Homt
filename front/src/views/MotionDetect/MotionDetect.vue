<template>
<div>
  <h1>운동하세요!</h1>
  <div>Teachable Machine Pose Model</div>
  <button type="button" @click="init()">Start</button>
  <div><canvas id="canvas"></canvas></div>
  <div id="label-container"></div>
</div>
</template>

<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>
<script>
const URL = "./my_model/";
let model, webcam, ctx, labelContainer, maxPredictions;

export default {

  methods: {
    init: async function() {
    // 1. 제작한 모델 불러오기
    const modelURL = URL + "model.json";
    const metadataURL = URL + "metadata.json";

    // load the model and metadata
    // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
    // Note: the pose library adds a tmPose object to your window (window.tmPose)
    model = await tmPose.load(modelURL, metadataURL);
    maxPredictions = model.getTotalClasses();

    // 2. 웹캠 켜기
    // Convenience function to setup a webcam
    const size = 700;
    const flip = true; // whether to flip the webcam
    webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
    await webcam.setup(); // request access to the webcam
    await webcam.play();

    // 3. loop 라는 함수를 돈다
    window.requestAnimationFrame(loop);

    // append/get elements to the DOM
    const canvas = document.getElementById("canvas");
    canvas.width = size; canvas.height = size;
    ctx = canvas.getContext("2d");
    labelContainer = document.getElementById("label-container");
    for (let i = 0; i < maxPredictions; i++) { // and class labels
        labelContainer.appendChild(document.createElement("div"));
      }
    },
    // 4. 웹캠을 계속 업데이트 하면서 예측(predict)를 수행
    loop: async function(timestamp) {
      webcam.update(); // update the webcam frame
      await predict();
      window.requestAnimationFrame(loop);
    },
    // 5. 예측
    predict: async function() {
      // Prediction #1: run input through posenet
      // estimatePose can take in an image, video or canvas html element

      // 모델(.model)을 이용해서 webcam이미지(webcam.canvas)를 예측
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);


      // 0 : defalut, 1: hi
      if (prediction[1].className == "hi" && prediction[1].probability.toFixed(2) == 1.00) {
          labelContainer.childNodes[0].innerHTML = "hi~ 성공!"
      } else {
          labelContainer.childNodes[0].innerHTML = "미완료"
      }


      // for (let i = 0; i < maxPredictions; i++) {
      //     // 클래스명 : 어느정도(최대 1)
      //     const classPrediction =
      //         prediction[i].className + ": " + prediction[i].probability.toFixed(2);
      //     labelContainer.childNodes[i].innerHTML = classPrediction;
      // }

      // finally draw the poses
      drawPose(pose);
    },

    drawPose: function(pose) {
      if (webcam.canvas) {
        ctx.drawImage(webcam.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
          const minPartConfidence = 0.5;
          tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
          tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
        } 
      }
    }
  }
}


</script>


<style>

</style>