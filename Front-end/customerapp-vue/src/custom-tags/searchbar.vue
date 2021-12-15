<template>
  <b-col :cols="col">
    <b-input-group>
      <input
        id="search-input"
        type="search"
        class="form-control"
        placeholder="Zoeken"
        v-on:input="ContinuousSearch"
        v-on:keyup.enter="Search"
        ref="searchInput"
      />
      <b-input-group-append>
        <b-button
          id="search-button"
          type="button"
          class="searchbar-button"
          v-on:click="Search"
        >
          <b-icon style="color: var(--text-color-secondary)" icon="search" />
        </b-button>
        <b-button @click="filter = !filter" class="searchbar-button"
          ><b-icon-filter
        /></b-button>
      </b-input-group-append>
    </b-input-group>

    <category-filter :categories="categories" :visible="filter" />

    <SearchResult
      ref="Searchresults"
      :filteredProducts="filteredProducts"
      :input="input"
      :nothingFound="nothingFound"
      :categories="categories"
    />
  </b-col>
</template>

<script>
import CategoryFilter from "../components/CategoryFilter.vue";
import SearchResult from "../components/SearchResults.vue";
export default {
  props: ["col"],
  name: "Header",
  components: {
    SearchResult,
    CategoryFilter,
  },
  data() {
    return {
      categories: [],
      products: [],
      filteredProducts: [],
      input: "",
      nothingFound: "",
      filter: false,
    };
    
  },
  mounted() {
    this.$APIService.getAllCategories().then((response) => {
      this.categories = response.data;
    });
    this.$APIService.getAllProducts().then((response) => {
      this.products = response.data;
    });
  },
  methods: {
    Search() {
      this.input = this.$refs.searchInput.value;
      this.CheckProducts();
      if (this.$refs.searchInput.value == "") {
        this.nothingFound = "Er zijn geen resultaten gevonden";
        setTimeout(() => {
          this.nothingFound = "";
        }, 2000);
      }
    },
    ContinuousSearch() {
      this.input = this.$refs.searchInput.value;
      if (this.$refs.searchInput.value === " ") {
        this.$refs.searchInput.value = "";
        this.input = this.$refs.searchInput.value;
      } else if (this.$refs.searchInput.value != "") {
        this.CheckProducts();
        document.documentElement.style.overflow = "hidden";
      } else {
        this.CheckProducts();
        document.documentElement.style.overflow = "auto";
      }
    },
    CheckProducts() {
      this.nothingFound = "";
      this.filteredProducts = this.products.filter((product) => {
        if (
          !product.name
            .toLowerCase()
            .includes(this.$refs.searchInput.value.toLowerCase())
        )
          return false;

        if (this.$store.state.tagFilter.selectedFilters.length === 0)
          return true;

        for (const tag of product.tags) {
          if (
            this.$store.getters["tagFilter/selectedFilterIds"].includes(tag.id)
          )
            return true;
        }

        return false;
      });

      if (
        this.filteredProducts.length === 0 &&
        this.$refs.searchInput.value.toLowerCase() != ""
      ) {
        this.nothingFound = "Er zijn geen resultaten gevonden";
      }
    },
  },
};
</script>

<style scoped>
.Searchbar {
  width: 115px;
}
.form-control {
  width: 50% !important;
  display: inline !important;
}
.search {
  position: absolute;
  margin-top: -7%;
  margin-left: 5%;
}
/* .button-style {
  width: 50%;
  height: auto;
  padding: 0.375rem 0.75rem;
  display: inline;
  vertical-align: baseline !important;
} */
.searchbar-button {
  background-color: var(--secondary-color) !important;
  border: 2pt solid var(--secondary-color-light) !important;
  color: var(--text-color-primary) !important;
}
</style>
