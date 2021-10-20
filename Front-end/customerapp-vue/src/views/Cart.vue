<template>
  <div>
    <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col cols="8">
                      <input id="search-input" type="search" class="form-control" placeholder="Search"/>
                      <b-button id="search-button" type="button" class="button-style button-style-search" v-on:click="Search">
                        <b-icon icon="search"/>
                      </b-button>
                </b-col>
                <b-col cols="4">
                    <b-button class="menuButton justify-content-end" v-on:click="GoToMenu">
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

<style lang="scss" scoped>
  html {
    background-color: #CBE1D9;
  }
  .cart{
    background-color: #CBE1D9;
  }
  .container .row .col {
    margin-left: 3%;
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
.menuButton{
    background-color: #BDAD89 !important;
    border: 2px solid #E0DCCC !important;
}
.form-control{
  width: 70% !important;
  display: inline !important;
}
.search{
  position: absolute;
  margin-top: -7%;
  margin-left: 5%;
}
.button-style-search{
  width: 30%;
  padding: 0.375rem 0.75rem;
  height: 38px;
  display: inline;
  vertical-align: baseline !important;
}
</style>
