<template>
    <b-col cols="8">
        <input id="search-input" type="search" class="form-control" placeholder="Zoeken" v-on:input="ContinuousSearch" v-on:keyup.enter="Search" ref="searchInput"/>
        <b-button id="search-button" type="button" class="button-style button-style-search" v-on:click="Search">
            <b-icon style="color: var(--text-color-secondary)" icon="search"/>
        </b-button>
        <SearchResult ref="Searchresults"
            :filteredProducts="filteredProducts"
            :input="input"
            :nothingFound="nothingFound"
            :categories="categories"/>
    </b-col>
</template> 

<script>
import SearchResult from "../components/SearchResults.vue";
export default {
    name: "Header",
    components: {
        SearchResult,
    },
    data() {
        return {
            categories: [],
            products: [],
            filteredProducts: [],
            input: "",
            nothingFound: "",
        };
    },
    mounted() {
      this.$APIService.getAllCategories()
      .then(response => {
        this.categories = response.data
      })
      this.$APIService.getAllProducts()
      .then(response =>{
          this.products = response.data
      })
    },
    methods: {
        Search() {
            this.input = this.$refs.searchInput.value;
            this.CheckProducts()
            if(this.$refs.searchInput.value == ""){
                this.nothingFound = "Er zijn geen resultaten gevonden";
                setTimeout(() => {  this.nothingFound = "" }, 2000);
            }
        },
        ContinuousSearch(){
            this.input = this.$refs.searchInput.value;
            if(this.$refs.searchInput.value === " "){
                this.$refs.searchInput.value = "";
                this.input = this.$refs.searchInput.value;
            }
            else if(this.$refs.searchInput.value != ""){
                this.CheckProducts();
                document.documentElement.style.overflow = 'hidden';
            }
            else{
                this.CheckProducts();
                document.documentElement.style.overflow = 'auto';
            }
        },
        CheckProducts(){
            this.nothingFound = "";
            this.filteredProducts = this.products.filter((product) => product.name.toLowerCase().includes(this.$refs.searchInput.value.toLowerCase()));

            if(this.filteredProducts.length === 0 && this.$refs.searchInput.value.toLowerCase() != ""){
                this.nothingFound = "Er zijn geen resultaten gevonden";
            }
        }
    }
};
</script>

<style scoped>
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