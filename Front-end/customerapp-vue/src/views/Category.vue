<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <b-col cols="8">
            <input
              id="search-input"
              type="search"
              class="form-control"
              placeholder="Search"
            />
            <b-button
              id="search-button"
              type="button"
              class="button-style"
              v-on:click="Search"
            >
              <b-icon icon="search" />
            </b-button>
          </b-col>
          <b-col cols="2">
            <b-button
              class="backButton justify-content-end"
              v-on:click="GoToMenu"
            >
              <b-icon icon="reply" flip-h variant="dark" />
            </b-button>
          </b-col>
          <b-col cols="2">
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
    <ProductCard
      v-for="product in category.products"
      :key="product.id"
      :product="product"
    />
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
  methods: {
    GoToMenu: function () {
      this.$router.push("/menu");
    },
    GoToCart() {
      this.$router.push("/cart");
    },
    Search() {
      //change this method
    },
  },
  data() {
    return {
      category: [],
      categoryId: this.$route.params.id,
    };
  },
  mounted() {
    Vue.axios
      .get("http://localhost:8080/category/id", {
        params: { categoryId: this.categoryId },
      })
      .then((response) => {
        this.category = response.data;
      });
    this.$store.commit("updateCartFromLocalStorage");
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
