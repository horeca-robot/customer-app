<template>
  <div>
    <b-card>
      <b-card-img :src="product.img"></b-card-img>
      <b-card-footer> 
        <h3>{{product.name}}</h3>
        <p>€ {{product.price.toFixed(2)}}</p> 
        <b-button pill variant="success" v-on:click="addToCart()">Add</b-button>
        <div v-if="product_total">
          <div class="cart-total">
            <h6>Aantal</h6>
            <h6>{{ product_total }}</h6>
          </div>
          <div>
            <b-button class="remove" @click="removeFromCart()">-</b-button>
            <b-button class="add" @click="addToCart()">+</b-button>
          </div>
        </div>       
      </b-card-footer>
    </b-card>
  </div>
</template>

<script>
export default {
    props: ["product"],
    methods: {
    addToCart() {
      this.$store.commit("addToCart", this.product);
    },
    removeFromCart(){
      this.$store.commit("removeFromCart", this.product);
    }
  },
  computed: {
    product_total() {
      return this.$store.getters.productQuantity(this.product)
    },
  },
}
</script>
