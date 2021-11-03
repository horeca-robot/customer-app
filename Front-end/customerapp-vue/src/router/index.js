import Vue from "vue";
import VueRouter from "vue-router";
import Menu from "../views/Menu.vue";
import Cart from "../views/Cart.vue";
import OrderHistory from "../views/OrderHistory.vue";
import TableValidator from "../views/TableValidator";
import Home from "../views/Home";

Vue.use(VueRouter);

const routes = [
  {
    path: "/menu",
    name: "menu",
    component: Menu,
  },
  {
    path: "/tablevalidator/:tableId",
    name: "TableValidator",
    component: TableValidator,
  },
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
    path: "/menu/:categoryId/:id",
    name: "Product",
    component: () => 
      import(/* webpackChunkName: "about" */ "../views/Product.vue"),
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
