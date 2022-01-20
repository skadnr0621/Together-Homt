import MainPage from "@/views/MainPage";
import Feed from "@/components/Feed";
import MyPage from "@/components/MyPage";

export default {
  path: "/main",
  component: MainPage,
  children: [
    {
      name: "Feed",
      path: "",
      component: Feed,
    },
    {
      name: "MyPage",
      path: "mypage",
      component: MyPage,
    },
  ],
};
