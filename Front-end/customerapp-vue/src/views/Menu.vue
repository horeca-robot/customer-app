<template>
  <div>
    <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col>
                    <b-input class="Searchbar" placeholder="zoeken...">
                    </b-input>
                </b-col>
                <b-col >
                    <b-button class="shoppingCartButton justify-content-end">
                        <img src="https://www.studio-22.nl/wp-content/uploads/2020/09/WW.png" alt="Shoppingcart" width="25" height="20">
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
import Vue from "vue";
import CategoryCard from "../components/CategoryCard.vue";

export default {
  name: "Home",
  components: {
    CategoryCard,
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

<style>
.Header{
    background-color: #82C4B5;
}
.SearchandStore{
    padding: 10px;
}
.Searchbar{
    width: 115px;
}
.shoppingCartButton{
    background-color: #BDAD89;
    border: 2px solid #E0DCCC;
}
</style>