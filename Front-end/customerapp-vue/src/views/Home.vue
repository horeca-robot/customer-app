<template>
  <div class="container">
    <ProductCard v-for="product in products" :key="product.id" :product="product"/>    
  </div>
</template>

<script>
import Vue from "vue";
import ProductCard from "../components/ProductCard.vue"

export default {
  name: "Home",
  components: {
    ProductCard
  },
  data() {
    return {
      products: [],
    };
  },
  mounted() {
    Vue.axios.get("http://localhost:8080/").then((response) => {
      this.products = response.data;
    });
  },
};
</script>
