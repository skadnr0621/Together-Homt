import Main from "@/views/Main";
import Feed from "@/views/main/feed/Feed";
import CommentPage from "@/views/main/feed/CommentPage";
import LikeListPage from "@/views/main/feed/LikeListPage";
import SearchPage from "@/views/main/SearchPage";
import Register from "@/views/main/Register";
import ExerciseList from "@/views/main/exercise/ExerciseList";
import MotionDetect from "@/views/main/exercise/MotionDetect";

import Profile from "@/views/main/Profile"; // 프로필 페이지
import ProfileEdit from "@/views/main/ProfileEdit"; // 프로필 편집 페이지
import PasswordEdit from "@/views/main/PasswordEdit"; // 비밀번호 변경 페이지
import ProfileFeedDetail from "@/views/main/ProfileFeedDetail"; // 프로필 피드 상세 페이지
import Follow from "@/views/main/Follow"; // 팔로우 페이지
import ProfileFeedComment from "@/views/main/ProfileFeedComment"; // 프로필 피드 댓글 페이지

export default {
  // MainPage : 피드, 검색, (피드)등록, 운동, 프로필
  path: "/main",
  name: "Main",
  component: Main,
  children: [
    {
      name: "Feed",
      path: "feed",
      component: Feed,
    },
    {
      name: "CommentPage",
      path: "feed/comment",
      component: CommentPage,
    },
    {
      name: "LikeListPage",
      path: "feed/likelistpage",
      component: LikeListPage,
    },
    {
      name: "Search",
      path: "search",
      component: SearchPage,
    },
    {
      name: "Register",
      path: "register",
      component: Register,
    },
    {
      name: "Exercise",
      path: "exercise",
      component: ExerciseList,
    },
    {
      name: "MotionDetect",
      path: "motiondetect",
      component: MotionDetect,
    },
    {
      name: "Profile",
      path: "profile/:userName/:email",
      component: Profile,
      props: {
        default: true,
      },
    },
    {
      name: "Follow",
      path: "profile/:follow/:userName/:email",
      component: Follow,
      props: {
        default: true,
      },
    },
    {
      name: "ProfileEdit",
      path: "profile/edit",
      component: ProfileEdit,
    },
    {
      name: "PasswordEdit",
      path: "profile/password",
      component: PasswordEdit,
    },
    {
      name: "ProfileFeedDetail",
      path: "profile/feed/:userName/:email:/:feedId",
      component: ProfileFeedDetail,
      props: {
        default: true,
      },
    },
    {
      name: "ProfileFeedComment",
      path: "profile/feed/comment/:userName/:email:/:feedId",
      component: ProfileFeedComment,
      props: {
        default: true,
      },
    },
  ],
};
