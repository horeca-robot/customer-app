import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios"
import pathEnum from "./enum"
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueQrcodeReader from "vue-qrcode-reader";
// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueQrcodeReader);

Vue.config.productionTip = false;
axios.defaults.baseURL = "http://localhost:8080"
Vue.prototype.$axios = axios
Vue.prototype.$path = pathEnum

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
