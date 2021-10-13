import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Cart from "../views/Cart.vue";
import OrderHistory from "../views/OrderHistory.vue";
import TableValidator from "../views/TableValidator";
import Test from "../views/Test";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/table/:tableId",
    name: "HomeTable",
    component: Home,
  },
  {
    path: "/tablevalidator/:tableId",
    name: "TableValidator",
    component: TableValidator,
  },
  {
    path: "/Test",
    name: "Test",
    component: Test,
  },
  {
    path: "/cart",
    name: "Cart",
    component: Cart,
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
