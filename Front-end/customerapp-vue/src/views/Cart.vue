<template>
  <div>
    <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col>
                    <b-input class="Searchbar" placeholder="zoeken...">
                    </b-input>
                </b-col>
                <b-col >
                    <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToMenu">
                        <img src="../assets/menuIcon.png" alt="Shoppingcart" width="25" height="20">
                    </b-button>
                </b-col>
            </b-row>
        </b-container>
    </div>  
    <div class="cart">
      <CartItemCard
        v-for="product in items"
        :key="product.guid"
        :product="product"
      />
      <p>Aantal items: {{ items.length }}</p>
      <h3>Totaal: € {{ cart_total.toFixed(2) }}</h3>
      <b-container>
          <p>Er zijn geen notities toegevoegd...</p>
          <b-row>
            <b-col>
              <b-button class="button-style heading">Notitie +</b-button>
            </b-col>
            <b-col>
              <b-button class="button-style heading">Bestellen</b-button>
            </b-col>
          </b-row>
      </b-container>
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
  methods: {
      GoToMenu: function (){
          this.$router.push("/menu")
      },
  },
  mounted() {
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>

<style lang="scss">
  html {
    background-color: #CBE1D9;
  }
  .cart{
    background-color: #CBE1D9;
  }
  .container .row .col {
    margin-left: 3%;
  }

  .button-style {
    background-color: #BDAD89 !important;
    border: 2pt solid #E0DCCC !important;
    height: 30px;
    width: 120px;
    color: black !important;
    padding: 1px !important;
    margin-top: 3px;
    margin-bottom: 3px;
  }

  .btn-secondary:active, .btn-secondary:hover {
    background-color: #E0DCCC;
    border: 2pt solid #BDAD89;
  }

  .container {
    background-color: #CBE1D9;
    margin: 0;
    max-width: 100% !important;
  }

  .SearchandStore{
    padding: 10px;
}
.Searchbar{
    width: 115px;
}
.shoppingCartButton{
    background-color: #BDAD89;
    border: 2px solid #E0DCCC;
}
</style>
