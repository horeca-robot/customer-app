<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <b-col cols="8">
            <input
              id="search-input"
              class="form-control"
              placeholder="Search"
              v-on:input="Search"
              v-on:keyup.enter="Search"
              v-bind="find"
            >
            <b-button
              id="search-button"
              type="button"
              class="button-style"
              v-on:click="Search"
            >
              <b-icon icon="search" />
            </b-button>
          </b-col>
          <b-col cols="4">
            <b-button
              class="shoppingCartButton justify-content-end"
              v-on:click="GoToCart"
            >
              <b-icon icon="cart4" variant="dark" />
            </b-button>
          </b-col>
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

export default {
  name: "Menu",
  components: {
    CategoryCard,
  },
  data() {
    return {
      categories: [],
      find: "dd",
    };
  },
  mounted() {
    this.axios.get("http://localhost:8080/api/v1/category/all").then((response) => {
      this.categories = response.data;
    });
    this.$store.commit("updateCartFromLocalStorage");
  },
  methods: {
    GoToCart() {
      this.$router.push("/cart");
    },
    Search() {
      console.log(this.find);
    },
  },
};
</script>

<style scoped>
.background {
  background-color: RGBA(203, 225, 217, 0.5);
}
.SearchandStore {
  padding: 10px;
}
.Searchbar {
  width: 115px;
}
.form-control {
  width: 70% !important;
  display: inline !important;
}
.search {
  position: absolute;
  margin-top: -7%;
  margin-left: 5%;
}
.button-style {
  width: 30%;
  padding: 0.375rem 0.75rem;
  height: 38px;
  display: inline;
  vertical-align: baseline !important;
}
</style>
