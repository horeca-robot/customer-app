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
    <div>
      <OrderCard :orders="list" />
    </div>
  </div>
</template>

<script>
import OrderCard from "../components/orderHistory/OrderHistoryCard";
import CartButton from "../custom-tags/cartbutton.vue";
import MenuButton from "../custom-tags/menubutton.vue";
import localStorageHelper from "../helpers/localStorageHelper";

export default {
  name: "OrderHistory",
  components: { MenuButton, OrderCard, CartButton },
  data() {
    return {
      list: [],
    };
  },
  mounted() {
    this.$APIService.getOrdersById(localStorageHelper.load('table').tableId).then((response) => {
      this.list = response.data;
    });
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
