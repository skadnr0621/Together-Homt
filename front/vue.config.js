// module.exports = {
//   devServer: {
//     proxy: "http://localhost:8092/",
//   },
//};

module.exports = {
  devServer: {
    //proxy: "http://i6b206.p.ssafy.io:8092",
    proxy: "http://localhost:8092/api",
  },
};

// module.exports = {
//     devServer: {
//         proxy: {
//             '/api': {
//                 target: 'http://3.38.103.222:8092' // 개발서버
//             }
//         }
//     }
// }

