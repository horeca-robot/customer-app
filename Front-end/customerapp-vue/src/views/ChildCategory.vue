<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar />
          <back-button :col="2" />
          <cart-button :col="2" />
        </b-row>
      </b-container>
    </div>
    <div>
      <ProductCard
        v-for="product in filteredProducts"
        :key="product.id"
        :product="product"
      />
    </div>    
  </div>
</template>

<script>
import ProductCard from "../components/ProductCard.vue";
import SearchBar from "../custom-tags/searchbar.vue";
import CartButton from "../custom-tags/cartbutton.vue";
import BackButton from "../custom-tags/backbutton.vue";

export default {
  name: "ChildCategory",
  components: {
    ProductCard,
    SearchBar,
    CartButton,
    BackButton,
  },
  computed: {
    filteredProducts() {
      if (!this.category.products) return [];
      if(this.$store.state.tagFilter.selectedFilters.length === 0) return this.category.products;
      return this.category.products.filter((item) => {
        for (const tag of item.tags) {
          if (this.$store.getters["tagFilter/selectedFilterIds"].includes(tag.id))
            return true;
        }
        return false;
      });
    },
  },
  data() {
    return {
      category: [],
      categoryId: this.$route.params.id,
    };
  },
  // get all products per category
  mounted() {
    this.$APIService.getCategoryById(this.categoryId).then((response) => {
      this.category = response.data;
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
