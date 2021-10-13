<template>
  <div class="container">
    <div v-for="category in categories" :key="category.id">
      <h2 style="margin-top:20px">{{category.name}}</h2>
      <hr>
      <Test
        v-for="product in category.products"
        :key="product.id"
        :product="product"
      />
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import Test from "../components/Test.vue";

export default {
  name: "Home",
  components: {
    Test,
  },
  data() {
    return {
      categories: [],
    };
  },
  mounted() {
    Vue.axios.get("http://localhost:8080/category").then((response) => {
      this.categories = response.data;
    });
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>