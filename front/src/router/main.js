import Main from "@/views/Main";
import Feed from "@/views/main/Feed";
import Search from "@/views/main/Search";
import Register from "@/views/main/Register";
import Exercise from "@/views/main/Exercise";

import Profile from "@/views/main/Profile"; // 프로필 페이지
import ProfileEdit from "@/views/main/ProfileEdit"; // 프로필 편집 페이지
import PasswordEdit from "@/views/main/PasswordEdit"; // 비밀번호 변경 페이지
import ProfileFeedDetail from "@/views/main/ProfileFeedDetail"; // 프로필 피드 상세 페이지
import Follow from "@/views/main/Follow"; // 팔로우 페이지

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
      name: "Search",
      path: "search",
      component: Search,
    },
    {
      name: "Register",
      path: "register",
      component: Register,
    },
    {
      name: "Exercise",
      path: "exercise",
      component: Exercise,
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
  ],
};
