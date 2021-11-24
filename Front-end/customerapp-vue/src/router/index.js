import Vue from "vue";
import VueRouter from "vue-router";
import Menu from "../views/Menu.vue";
import Cart from "../views/Cart.vue";
import OrderHistory from "../views/OrderHistory.vue";
import TableValidator from "../views/TableValidator";
import Home from "../views/Home";
import OrderHistoryDetails from "../views/OrderHistoryDetails";

Vue.use(VueRouter);

const routes = [
  {
    path: "/menu",
    name: "Menu",
    meta: {
      title: "Menu"
    },
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
    meta: {
      title: "Home"
    },
    component: Home,
  },
  {
    path: "/bestelling",
    name: "Cart",
    meta: {
      title: "Jouw bestelling"
    },
    component: Cart,
  },
  {
    path: "/menu/:id",
    name: "Category",
    meta: {
      title: "Categorie"
    },
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Category.vue"),
  },
  {
    path: "/menu/:categoryId/:id",
    name: "Product",
    meta: {
      title: "Product"
    },
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Product.vue"),
  },
  {
    path: "/bestellingen",
    name: "OrderHistory",
    meta: {
      title: "Bestellingen"
    },
    component: OrderHistory,
  },
  {
    path: "/orders/:id",
    name: "OrderHistoryDetails",
    component: OrderHistoryDetails,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ["/"];
  const authRequired = !publicPages.includes(to.path);
  const tableSelected = localStorage.getItem("table");

  if (authRequired && !tableSelected) {
    next("/");
  } else if (!authRequired && tableSelected) {
    next("/menu");
  } else {
    next();
  }
});

export default router;
