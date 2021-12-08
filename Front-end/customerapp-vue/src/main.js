import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueQrcodeReader from "vue-qrcode-reader";
import APIService from './services/axios.service';
// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";


Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueQrcodeReader);

Vue.config.productionTip = false;
Vue.prototype.$APIService = APIService

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
