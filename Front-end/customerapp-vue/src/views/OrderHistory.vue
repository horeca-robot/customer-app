<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <b-col offset="6" cols="6">
            <b-button
              class="shoppingCartButton justify-content-end"
              v-on:click="goToCart"
            >
              <b-icon icon="cart4" variant="dark" />
            </b-button>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div>
      <OrderCard :orders="list" />
    </div>
  </div>
</template>

<script>
import OrderCard from "../components/orderHistory/OrderCard";

export default {
  name: "OrderHistory",
  components: { OrderCard },
  methods: {
    goToCart() {
      this.$router.push({ name: "Cart" });
    },
  },
  data() {
    return {
      list: [
        { id: 1, name: "Poggers", price: "17.35", totalProducts: 11 },
        { id: 2, name: "Poggers", price: "43.35", totalProducts: 5 },
        { id: 3, name: "Poggers", price: "173.35", totalProducts: 78 },
      ],
    };
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
