<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <back-button :col="6" />
          <cart-button :col="6" />
        </b-row>
      </b-container>
    </div>
    <div>
      <b-img :src="product.image" class="image"></b-img>
      <div>
        <h1 class="product-name">{{ this.product.name }}</h1>
        <div class="ingrediënten">
          <h1 v-if="product.ingredients != 0" class="heading">
            <b>Ingrediënten</b>
          </h1>
          <p class="text">{{ this.ingredients }}</p>
          <br />
          <div>
            <b-icon
              icon="dash"
              font-scale="3"
              v-on:click="RemoveFromAmount"
              style="color: var(--text-color-secondary)"
            ></b-icon>
            <b-input
              @change="checkAmount"
              class="numberSelected"
              type="number"
              v-model.number="amount"
              placeholder="0"
              min="0"
              >amount</b-input
            >
            <b-icon
              style="color: var(--text-color-secondary)"
              icon="plus"
              font-scale="3"
              v-on:click="AddToAmount"
            ></b-icon>
          </div>
        </div>
        <div class="bijgerechten">
          <h1 v-if="product.byProducts && product.byProducts.length" class="heading">
            <b>Bijgerechten</b>
          </h1>
          <Byproduct
            v-for="byproduct in product.byProducts"
            :key="byproduct.id"
            :byproduct="byproduct"
            ref="bijproduct"
          />
        </div>
        <div class="place-order">
          <h3 class="heading">
            Totale prijs: &euro; {{ totalPrice.toFixed(2) }}
          </h3>
          <b-button class="button-style heading" v-on:click="addToCart"
            >Toevoegen</b-button
          >
        </div>
        <b-alert
          :show="dismissCountDown"
          fade
          variant="success"
          @dismiss-count-down="countDownChanged"
        >
          {{ this.orderAmount }}x {{ product.name }} met succes toegevoegd aan
          de
          <router-link class="alert-link" :to="{ name: 'Cart' }"
            >bestelling.</router-link
          >
        </b-alert>
      </div>
    </div>
  </div>
</template>

<script>
import Byproduct from "../components/Byproduct.vue";
import CartButton from "../custom-tags/cartbutton.vue";
import BackButton from "../custom-tags/backbutton.vue";

export default {
  components: {
    Byproduct,
    CartButton,
    BackButton,
  },
  data() {
    return {
      product: [],
      productId: this.$route.params.id,
      amount: 1,
      orderAmount: 0,
      dismissSecs: 4,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      rendered: false,
      totalPrice: 0,
    };
  },
  computed: {
    ingredients() {
      return this.product.ingredients.map(ingredient => ingredient.name).join(", ");
    }
  },
  methods: {
    AddToAmount() {
      this.amount++;
      this.checkAmount();
    },
    RemoveFromAmount() {
      this.amount--;
      this.checkAmount();
    },
    addToCart() {
      for (let i = 0; i < this.amount; i++) {
        this.$store.commit("addToCart", this.product);
      }
      if (this.$refs.bijproduct != undefined) {
        for (let i = 0; i < this.$refs.bijproduct.length; i++) {
          for (let x = 0; x < this.$refs.bijproduct[i].amount; x++) {
            this.$store.commit("addToCart", this.$refs.bijproduct[i].byproduct);
          }
        }
      }

      this.$bvModal.hide(this.product.id.toString());
      this.showAlert(this.amount);
    },
    checkAmount() {
      if (this.amount < 1) {
        this.amount = 1;
      } else if (this.amount > 100) {
        this.amount = 100;
      }
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert(amount) {
      if (this.dismissCountDown == 0) {
        this.orderAmount = 0;
      }
      this.dismissCountDown = this.dismissSecs;
      this.orderAmount += amount;
    },
    calcTotalPrice() {
      var byproductPrices = 0;
      if (this.$refs.bijproduct != undefined) {
        for (let i = 0; i < this.$refs.bijproduct.length; i++) {
          byproductPrices += this.$refs.bijproduct[i].totalprice();
          this.totalPrice = this.amount * this.product.price + byproductPrices;
        }
      }
      else{
        this.totalPrice = this.amount * this.product.price;
      }
    },
  },
  mounted() {
    this.$APIService
      .getProductById({ params: { id: this.productId } })
      .then((response) => {
        this.product = response.data;
      });
    this.$APIService
      .getIngredientsByProductId({ params: { id: this.productId } })
      .then((response) => {
        this.product.ingredients = response.data;
      });
    this.$store.commit("updateCartFromLocalStorage");
  },
  beforeUpdate() {
    this.calcTotalPrice();
  },
};
</script>

<style scoped>
.image {
  height: 200px;
}
.ingrediënten {
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary-light);
}
.numberSelected {
  width: 20%;
  max-height: 30px;
  text-align: center;
}
.bijgerechten {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light);
}
.b-icon.bi {
  vertical-align: middle !important;
}
.form-control {
  display: inline !important;
  margin-bottom: 10px;
}
.place-order {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light);
  padding-top: 30px;
}
</style>
