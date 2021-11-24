<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <back-button :col="4" />
          <menu-button :col="4" />
          <cart-button :col="4" />
        </b-row>
      </b-container>
    </div>
    <div>
      <CartItemCard v-for="productOrder in order.productOrders" :key="productOrder.id" :product="productOrder.product" />
    </div>
  </div>
</template>

<script>
import CartItemCard from "../components/OrderItem";
import MenuButton from "../custom-tags/menubutton";
import CartButton from "../custom-tags/cartbutton";
import BackButton from "../custom-tags/backbutton";

export default {
  name: "OrderHistory",
  components: { MenuButton, CartButton, CartItemCard, BackButton },
  data() {
    return {
      order: {},
    };
  },
  mounted() {
    this.$APIService
      .getOrderDetails({ params: { id: this.$route.params.id } })
      .then((response) => {
        console.log(response.data)
        this.order = response.data;
      });
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
