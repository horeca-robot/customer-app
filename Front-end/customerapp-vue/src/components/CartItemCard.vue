<template>
  <div>
    <b-card-footer>
      <b-card-text> {{ product.name }} </b-card-text>
      <b-card-text> € {{ product.price.toFixed(2) }} </b-card-text>
      <b-button-group>
        <b-button variant="light" @click="addToCart()">+</b-button>
        <b-button variant="light" @click="removeFromCart()">-</b-button>
      </b-button-group>
    </b-card-footer>
  </div>
</template>

<script>
export default {
  props: ["product"],
  methods: {
    addToCart() {
      this.$store.commit("addToCart", this.product);
    },
    removeFromCart() {
      this.$store.commit("removeFromCart", this.product);
    },
  },
};
</script>

<style scoped>
</style>