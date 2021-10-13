<template>
  <div class="background">
      <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col>
                    <b-input class="Searchbar" placeholder="zoeken...">
                    </b-input>
                </b-col>
                <b-col>
                    <b-button class="backButton justify-content-end" v-on:click="GoToMenu">
                        <b-icon icon="reply" flip-h variant="dark"/>
                    </b-button>
                </b-col>
                <b-col >
                    <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
                        <b-icon icon="cart4" variant="dark"/>
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
      },
      GoToCart(){
          this.$router.push("/cart")
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
.background{
    background-color: RGBA(203, 225, 217, 0.5);
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