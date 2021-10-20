<template>
  <div class="background">
    <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col>
                    <b-input class="Searchbar" placeholder="zoeken...">
                    </b-input>
                </b-col>
                <b-col >
                    <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
                        <b-icon icon="cart4" variant="dark"/>
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
  methods:{
      GoToCart(){
          this.$router.push("/cart")
      }
  }
};
</script>

<style>
.background{
    background-color: RGBA(203, 225, 217, 0.5);
}
.SearchandStore{
    padding: 10px;
}
.Searchbar{
    width: 115px;
}
</style>