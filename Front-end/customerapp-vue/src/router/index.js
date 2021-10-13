import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Cart from "../views/Cart.vue";
import OrderHistory from "../views/OrderHistory.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/cart",
    name: "Cart",    
    component: Cart,
  },
  {
    path: "/menu",
    name: "Menu",
    component: () => 
      import(/* webpackChunkName: "about" */ "../views/Menu.vue"),
  },
  {
    path: "/menu/:id",
    name: "Category",
    component: () => 
      import(/* webpackChunkName: "about" */ "../views/Category.vue"),
  },
  {
    path: "/orders",
    name: "OrderHistory",    
    component: OrderHistory,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
