<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar/>
          <cart-button :col="4"/>
        </b-row>
      </b-container>
    </div>
    <CategoryCard
      v-for="category in categories"
      :key="category.id"
      :category="category"
    />
  </div>
</template>

<script>
  import CategoryCard from "../components/CategoryCard.vue";
  import SearchBar from "../custom-tags/searchbar.vue";
  import CartButton from "../custom-tags/cartbutton.vue";

  export default {
    name: "Menu",
    components: {
      CategoryCard,
      SearchBar,
      CartButton,
    },
    data() {
      return {
        categories: [],
      };
    },
    mounted() {
      this.$APIService.getAllCategories()
      .then(response => {
        this.categories = response.data
      })
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
