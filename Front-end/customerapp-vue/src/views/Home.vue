<template>
  <div class="container">
    <div v-for="category in categories" :key="category.id">
      <h2>{{category.name}}</h2>
      <ProductCard
        v-for="product in category.products"
        :key="product.id"
        :product="product"
      />
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import ProductCard from "../components/ProductCard.vue";

export default {
  name: "Home",
  components: {
    ProductCard,
  },
  data() {
    return {
      categories: [],
    };
  },
  mounted() {
    Vue.axios.get("http://localhost:8080/").then((response) => {
      this.categories = response.data;
    });
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>