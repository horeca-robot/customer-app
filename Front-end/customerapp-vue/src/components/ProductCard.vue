<template>
  <div>
    <b-card v-b-modal="product.id.toString()" class="card" :img-src="product.img" img-top>
      <b-card-text> € {{ product.price.toFixed(2) }} </b-card-text>
    </b-card>
    <b-modal :id="product.id.toString()">
      <div style="margin-bottom: 20px" v-for="ingredient in product.ingredients" :key="ingredient.id">
        <li>{{ingredient.name}}</li>
      </div>
      <b-button pill variant="success" @click="addToCart()">Add</b-button>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: ["product"],
  methods: {
    addToCart() {
      this.$store.commit("addToCart", this.product);
      this.$bvModal.hide(this.product.id.toString())	
    },
  },
};
</script>

<style scoped>
.card {
  margin: 0 auto; /* Added */
  float: none; /* Added */
  margin-top: 10px;
  margin-bottom: 10px; /* Added */
  max-width: 400px;
  background-color: rgba(0, 0, 0, 0.062);
}
</style>
