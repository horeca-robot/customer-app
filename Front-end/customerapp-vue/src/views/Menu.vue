<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <b-col cols="8">
            <input
              id="search-input"
              class="form-control"
              placeholder="Zoeken"
              v-on:input="ContinuousSearch"
              v-on:keyup.enter="Search"
              ref="searchInput"
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
        <SearchResult v-if="input != '' " ref="Searchresults"
        :products="products"
        :input="input"
        :nothingFound="nothingFound"
        :categories="categories"/>
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
import SearchResult from "../components/SearchResults.vue";

export default {
  name: "Menu",
  components: {
    CategoryCard,
    SearchResult,
  },
  data() {
    return {
      categories: [],
      products: [],
      number: 0,
      input: "",
      nothingFound: "",
    };
  },
  mounted() {
    this.axios.get("http://localhost:8080/api/v1/category/all").then((response) => {
      this.categories = response.data;
    });
    this.axios.get("http://localhost:8080/api/v1/product/all").then((response) => {
      this.products = response.data;
    })
    this.$store.commit("updateCartFromLocalStorage");
  },
  methods: {
    GoToCart() {
      this.$router.push("/cart");
    },
    Search() {
      this.input = this.$refs.searchInput.value;
      this.CheckProducts()
      if(this.$refs.searchInput.value == ""){
        alert("U moet eerst iets invullen voordat u kunt zoeken.")
      }
    },
    ContinuousSearch(){
      this.input = this.$refs.searchInput.value;
      this.CheckProducts();
      if(this.$refs.searchInput.value == " "){
        this.$refs.searchInput.value = "";
        alert("U mag niet beginnen met een spatie.")
        this.input = this.$refs.searchInput.value;
      }
      else if(this.$refs.searchInput.value != ""){
        document.documentElement.style.overflow = 'hidden';
      }
      else{
        document.documentElement.style.overflow = 'auto';
      }
    },
    CheckProducts(){
      this.number = 0;
      this.nothingFound = "";
        this.products.forEach(product => {
          if(product.name.includes(this.$refs.searchInput.value)){
            this.number++;
          }
        })
        if(this.number == 0){
            this.nothingFound = "Er zijn geen resultaten gevonden";
        }
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
