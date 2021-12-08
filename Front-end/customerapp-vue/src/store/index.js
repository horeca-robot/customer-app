import Vue from "vue";
import Vuex from "vuex";
import { v4 as uuid } from "uuid";
import tableModule from "./tableModule";
import tagFilterModule from "./tagFilterModule";

Vue.use(Vuex);

function updateLocalStorage(cart) {
  localStorage.setItem("cart", JSON.stringify(cart));
}

export default new Vuex.Store({
  state: {
    cart: [],
  },
  getters: {
    cartItems: (state) => {
      return state.cart;
    },
    cartTotal: (state) => {
      return state.cart.reduce((a, b) => a + b.price * 1, 0);
    },
  },
  mutations: {
    addToCart(state, product) {
      state.cart.push({ ...product, guid: uuid() });

      updateLocalStorage(state.cart);
    },
    removeFromCart(state, product) {
      let item = state.cart.find((i) => i.guid === product.guid);

      if (item) {
        state.cart = state.cart.filter((i) => i.guid !== product.guid);
      }

      updateLocalStorage(state.cart);
    },
    updateCartFromLocalStorage(state) {
      const cart = localStorage.getItem("cart");
      if (cart) {
        state.cart = JSON.parse(cart);
      }
    },
    removeCartFromLocalStorage(){
      const cart = localStorage.getItem("cart");
      if (cart) {
        localStorage.removeItem("cart");
      }
    }
  },
  actions: {},
  modules: {
    tableModule: tableModule,
    tagFilter: tagFilterModule,
  },
});
