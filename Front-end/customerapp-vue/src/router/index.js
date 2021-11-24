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
    name: "Menu",
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
    path: "/winkelwagen",
    name: "Cart",
    component: Cart,
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
    path: "/bestellingen",
    name: "OrderHistory",
    component: OrderHistory,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/'];
  const authRequired = !publicPages.includes(to.path);
  const tableSelected = localStorage.getItem('table');

  if (authRequired && !tableSelected) {
    next('/');
  } else if(!authRequired && tableSelected) {
    next('/menu');
  }
  else{
    next();
  }
});

export default router;