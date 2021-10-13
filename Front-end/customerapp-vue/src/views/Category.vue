<template>
  <div>
      <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col>
                    <b-input class="Searchbar" placeholder="zoeken...">
                    </b-input>
                </b-col>
                <b-col>
                    <b-button class="backButton justify-content-end" v-on:click="GoToMenu">
                        <img src="https://cdn0.iconfinder.com/data/icons/controls-add-on/48/v-38-512.png" alt="Shoppingcart" width="25" height="20">
                    </b-button>
                </b-col>
                <b-col >
                    <b-button class="shoppingCartButton justify-content-end">
                        <img src="https://www.studio-22.nl/wp-content/uploads/2020/09/WW.png" alt="Shoppingcart" width="25" height="20">
                    </b-button>
                </b-col>
            </b-row>
        </b-container>
        </div>  
        <ProductCard v-for="product in category.products"
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
      GoToMenu: function (){
          this.$router.push("/menu")
      }
  },
  data() {
    return {
      category: [],
      categoryId: this.$route.params.id,
    };
  },
  mounted() {
    Vue.axios.get("http://localhost:8080/category/id", {params: {"categoryId": this.categoryId}}).then((response) => {
      this.category = response.data;
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
.backButton{
    background-color: #BDAD89;
    border: 2px solid #E0DCCC;
}
</style>