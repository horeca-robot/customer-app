<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar/>
          <menu-button :col="4"/>
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
              <b-button @click="PlaceOrder" class="button-style heading">Bestellen</b-button>
            </b-col>
          </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
  import CartItemCard from "../components/CartItemCard.vue";
  import SearchBar from "../custom-tags/searchbar.vue";
  import MenuButton from "../custom-tags/menubutton.vue";

  export default {
    components: {
      CartItemCard,
      SearchBar,
      MenuButton
    },
    data(){
      return {
        response: {}
      }
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
        PlaceOrder()
        {
          const order = {          
              products: this.items,
              tableNumber: 1,
              notes: ""          
          };
          this.$axios.post(this.$path.ORDER, order)
          .then(response => {
            this.response = response.data
          })
          if (this.response != null) {
            this.$store.commit("removeCartFromLocalStorage");
            this.$router.go();
          }
        }
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
