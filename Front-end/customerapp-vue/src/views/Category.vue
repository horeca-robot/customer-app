<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar/>
          <menu-button :col="2"/>
          <cart-button :col="2"/>
        </b-row>
      </b-container>
    </div>
    <ProductCard
      v-for="product in category.products"
      :key="product.id"
      :product="product"
    />
  </div>
</template>

<script>
  import ProductCard from "../components/ProductCard.vue";
  import SearchBar from "../custom-tags/searchbar.vue";
  import CartButton from "../custom-tags/cartbutton.vue";
  import MenuButton from "../custom-tags/menubutton.vue";

  export default {
    name: "Category",
    components: {
      ProductCard,
      SearchBar,
      CartButton,
      MenuButton
    },
    data() {
      return {
        category: [],
        categoryId: this.$route.params.id,
      };
    },
    // get all products per category
    mounted() {
      this.$APIService.getCategoryById(this.categoryId)
      .then(response => {
        this.category = response.data
      });
      this.$store.commit("updateCartFromLocalStorage");
    },
  };
</script>

<style scoped>
  .background {
    background-color: var(--primary-color-light);
    color: var(--text-color-primary);
  }
</style>
