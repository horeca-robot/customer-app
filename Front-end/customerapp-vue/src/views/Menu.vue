<template>
  <div class="container">
    <div v-for="category in categories" :key="category.id">
      <h2 style="margin-top: 20px">{{ category.name }}</h2>
      <hr />
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
  name: "Menu",
  components: {
    ProductCard,
  },
  data() {
    return {
      categories: [],
    };
  },
  beforeMount() {
    if (!this.$route.params.tableId) return;
    this.$store.dispatch("tableModule/setTable", this.$route.params.tableId);
  },
  mounted() {
    Vue.axios.get("http://localhost:8080/").then((response) => {
      this.categories = response.data;
    });
  },
};
</script>
