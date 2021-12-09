<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar :col="8" />
          <back-button :col="2" />
          <cart-button :col="2" />
        </b-row>
      </b-container>
    </div>
    <div v-if="category.childCategories">
      <category-card
        v-for="childCategory in category.childCategories"
        :key="childCategory.id"
        :category="childCategory"
      />
    </div>
  </div>
</template>

<script>
import SearchBar from "../custom-tags/searchbar.vue";
import CartButton from "../custom-tags/cartbutton.vue";
import BackButton from "../custom-tags/backbutton.vue";
import CategoryCard from "../components/CategoryCard.vue";

export default {
  name: "Category",
  components: {
    SearchBar,
    CartButton,
    BackButton,
    CategoryCard,
  },
  data() {
    return {
      category: {},
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
    color: var(--text-color-primary-light);
  }
</style>
