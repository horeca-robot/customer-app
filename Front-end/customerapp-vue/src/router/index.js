import Vue from "vue";
import VueRouter from "vue-router";
import Menu from "../views/Menu.vue";
import Cart from "../views/Cart.vue";
import OrderHistory from "../views/OrderHistory.vue";
import TableValidator from "../views/TableValidator";
import Test from "../views/Test";

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
