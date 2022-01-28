import FirstPage from "@/views/FirstPage";
import Login from "@/views/user/Login";
import Signup from "@/views/user/Signup";

export default {
  path: "/",
  name: "FirstPage",
  component: FirstPage,
  children: [
    {
      name: "Login",
      path: "",
      component: Login,
    },
    {
      name: "Signup",
      path: "signup",
      component: Signup,
    },
  ],
};