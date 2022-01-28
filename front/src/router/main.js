import MainPage from "@/views/MainPage";
import Feed from "@/components/Feed";
import UserPage from "@/components/UserPage";
import FollowPage from "@/components/FollowPage";
import UserEditPage from "@/components/UserEditPage";

import ExerciseList from "@/views/exercise/ExerciseList";
import SearchList from "@/views/search/SearchList";

export default {
  path: "/homet",
  component: MainPage,
  children: [
    {
      name: "Feed",
      path: "feed",
      component: Feed,
    },
    {
      name: "UserPage",
      path: ":userName",
      component: UserPage,
      props: {
        default: true,
      },
    },
    {
      name: "FollowPage",
      path: "follow/:userName",
      component: FollowPage,
      props: {
        default: true,
      },
    },
    {
      name: "UserEditPage",
      path: "edit/:userName",
      component: UserEditPage,
      props: {
        default: true,
      },
    },

    {
      name: "ExerciseList",
      path: "exerciselist",
      component: ExerciseList,
    },
    {
      name: "SearchList",
      path: "searchlist",
      component: SearchList,
    }
  ],
};
