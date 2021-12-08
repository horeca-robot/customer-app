<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <menu-button :col="6" />
          <cart-button :col="6" />
        </b-row>
      </b-container>
    </div>
    <div v-for="order in restaurantOrders" :key="order.id">
      <OrderHistoryItem  :order="order" :number="restaurantOrders.indexOf(order) + 1" />
    </div>
    <h3 v-if="restaurantOrders.length === 0">U heeft nog geen bestellingen geplaats, <a href="/menu">klik hier</a> om uw bestelling samen te stellen</h3>
  </div>
</template>

<script>
import CartButton from "../custom-tags/cartbutton.vue";
import MenuButton from "../custom-tags/menubutton.vue";
import localStorageHelper from "../helpers/localStorageHelper";
import OrderHistoryItem from "../components/orderHistory/OrderHistoryItem.vue"

export default {
  name: "OrderHistory",
  components: { MenuButton, OrderHistoryItem, CartButton },
  data() {
    return {
      restaurantOrders: [],
    };
  },
  mounted() {
    this.$APIService.getOrdersById(localStorageHelper.load('table').tableId).then((response) => {
      this.restaurantOrders = response.data;
    });
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
