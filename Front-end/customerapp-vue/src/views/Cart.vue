<template>
  <div class="container">
    <b-alert class="alert"
      :show="dismissCountDown"
      variant="success"
      @dismissed="dismissCountDown = 0"
      @dismiss-count-down="countDownChanged"
    >
      <p>Bestelling geplaatst</p>
    </b-alert>
    <h3>Bestellijst</h3>
    <b-card style="margin-top: 20px">
      <CartItemCard
        v-for="product in items"
        :key="product.guid"
        :product="product"
      />
      <hr />
    </b-card>
    <h4>Aantal items: {{ items.length }}</h4>
    <h3>Totaal: € {{ cart_total.toFixed(2) }}</h3>
    <div v-if="items.length > 0">
      <b-button pill variant="primary" @click="showAlert">Bestel</b-button>
    </div>
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
  data() {
    return {
      dismissSecs: 3,
      dismissCountDown: 0,
      showDismissibleAlert: false,
    };
  },
  methods: {
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs;
    },
  },
  mounted() {
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>

<style scoped>
.alert{
  position: absolute;
  top:0;
  width: 300px;
  margin-top: 20px;
  margin-left: auto;
  margin-right: auto;
  left: 0;
  right: 0;
}
</style>