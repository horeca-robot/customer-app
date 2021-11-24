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
      <CartItemCard :product="product" />
    </div>
  </div>
</template>

<script>
import CartItemCard from "../components/OrderItem";
export default {
  name: "OrderHistory",
  components: { CartItemCard },
  methods: {
    goToCart() {
      this.$router.push({ name: "Cart" });
    },
  },
  data() {
    return {
      product: {
        id: "d257fd08-781e-4c16-b76a-cec5686d3b55",
        name: "Tzatziki",
        image:
          "https://img.static-rmg.be/a/food/image/q75/w640/h400/1086911/tzatziki.jpg%22,%22price%22:4.5,%22discountPrice%22:0.0,%22description%22:%22Griekse yoghurt met knoflook en komkommer",
        containsAlcohol: false,
        tags: [],
        ingredients: [],
      },
    };
  },
  mounted() {},
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
