<template>
  <div class="container">
    <h3 class="text-center">Bestellijst</h3>
    <b-card style="margin-top: 20px">
      <CartItemCard
        v-for="product in items"
        :key="product.guid"
        :product="product"
      />
      <hr />
    </b-card>
    <h4>Aantal items: {{items.length}}</h4>
    <h3>Totaal: € {{ cart_total.toFixed(2) }}</h3>
  </div>
</template>

<script>
import CartItemCard from "../components/CartItemCard.vue";
export default {
  components: {
    CartItemCard,
  },
  computed: {
    items() {
      return this.$store.getters.cartItems;
    },
    cart_total() {
      return this.$store.getters.cartTotal;
    },
  },
  mounted() {
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>
