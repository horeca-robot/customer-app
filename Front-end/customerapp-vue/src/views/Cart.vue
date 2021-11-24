<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar />
          <menu-button :col="4" />
        </b-row>
      </b-container>
    </div>
    <div class="cart">
      <CartItemCard
        v-for="product in items"
        :key="product.guid"
        :product="product"
      >
        <template v-slot:action-area="{ product }">
          <b-button
            class="button-style heading"
            v-on:click="addProductToCart(product)"
            >Toevoegen</b-button
          >
          <b-button
            class="button-style heading"
            v-on:click="removeProductFromCart(product)"
            >Verwijderen</b-button
          >
        </template>
      </CartItemCard>
      <p>Aantal items: {{ items.length }}</p>
      <h3>Totaal: € {{ cart_total.toFixed(2) }}</h3>
      <b-container v-if="items.length">
        <p>Er zijn geen notities toegevoegd...</p>
        <b-row>
          <b-col>
            <b-button class="button-style heading">Notitie +</b-button>
          </b-col>
          <b-col>
            <b-button :disabled="ordering" @click="PlaceOrder" class="button-style heading"
              >Bestellen</b-button
            >
          </b-col>
        </b-row>
        <b-alert
          :show="dismissCountDown"
          fade
          variant="success"
          @dismiss-count-down="countDownChanged"
        >
          Bestelling met succes geplaatst.
        </b-alert>
      </b-container>
    </div>
  </div>
</template>

<script>
import CartItemCard from "../components/OrderItem.vue";
import SearchBar from "../custom-tags/searchbar.vue";
import MenuButton from "../custom-tags/menubutton.vue";

export default {
  components: {
    CartItemCard,
    SearchBar,
    MenuButton,
  },
  data() {
    return {
      response: {},
      dismissSecs: 4,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      ordering: false,
    };
  },
  computed: {
    items() {
      return this.$store.getters.cartItems;
    },
    cart_total() {
      return this.$store.getters.cartTotal;
    },
  },
  methods: {
    PlaceOrder() {
      this.ordering = true;
      const order = {
        products: this.items,
        tableId: JSON.parse(localStorage.getItem('table')).tableId,
        notes: "",
      };
      if (this.items.length > 0) {
        this.$APIService.placeOrder(order).then((response) => {
          this.response = response.data;
        });
        if (this.response != null) {
          this.$store.commit("removeCartFromLocalStorage");
          this.showAlert();
          setTimeout(() => {
            this.$router.go();
          }, this.dismissSecs * 1000);
        }
      }
    },
    addProductToCart(product) {
      this.$store.commit("addToCart", product);
    },
    removeProductFromCart(product) {
      this.$store.commit("removeFromCart", product);
    },
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

<style lang="scss" scoped>
html {
  background-color: #cbe1d9;
}
.cart {
  background-color: #cbe1d9;
}
.container .row .col {
  margin-left: 3%;
}
.container {
  background-color: #cbe1d9;
  margin: 0;
  max-width: 100% !important;
}
</style>
