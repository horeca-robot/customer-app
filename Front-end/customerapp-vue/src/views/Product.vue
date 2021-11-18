<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <b-col cols="6">
            <b-button
              class="backButton justify-content-end"
              v-on:click="GoToCategory"
            >
              <b-icon icon="reply" flip-h variant="dark" />
            </b-button>
          </b-col>
          <b-col cols="6">
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
    <div>
      <b-img :src="product.image" class="image"></b-img>
        <div>
            <h1 class="product-name">{{this.product.name}}</h1>           
            <div class="ingrediënten">
                
                <h1 v-if="product.ingredients != 0" class="heading"><b>Ingrediënten</b></h1>
                <p class="text" v-for="ingredient in product.ingredients" :key="ingredient.id" style="display: inline;">
                 {{ingredient.name}},
                </p><br/>
                <div>
                    <b-icon icon="dash" font-scale="3" v-on:click="RemoveFromAmount"></b-icon>
                    <b-input @change="checkAmount" class="numberSelected" type="number" v-model.number="amount" placeholder="0" min="0">amount</b-input>
                    <b-icon icon="plus" font-scale="3" v-on:click="AddToAmount"></b-icon>
                </div>
            </div>
            <div class="bijgerechten">
                <h1 class="heading"><b>Bijgerechten</b></h1>
                <Byproduct
                v-for="byproduct in product.byproduct"
                :key="byproduct.id"
                :byproduct="byproduct"
                />
            </div>
            <div class="place-order">
                <h3 class="heading">Totale prijs: &euro; {{(product.price * this.amount).toFixed(2)}}</h3>
                <b-button class="button-style heading" v-on:click="addToCart">Toevoegen</b-button>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import Byproduct from "../components/Byproduct.vue";
export default {
  components: {
    Byproduct,
  },
  data() {
    return {
      product: [],
      categoryId: this.$route.params.categoryId,
      productId: this.$route.params.id,
      amount: 1,
    };
  },    
  methods: {
      GoToCategory(){
          this.$router.push({name:'Category', params: {id: this.categoryId}})
      },
      GoToCart(){
          this.$router.push({name:'Cart'})
      },
      AddToAmount(){
          this.amount++;
          this.checkAmount();
      },
      RemoveFromAmount(){
          this.amount--;
          this.checkAmount()
      },
      addToCart() {
          for (let i = 0; i < this.amount; i++) {
              this.$store.commit("addToCart", this.product);
          }
          this.$bvModal.hide(this.product.id.toString());
          alert("Uw bestelling is toegevoegd.");
          this.$router.go();
      },
      checkAmount(){
          if(this.amount < 1)
          {
              this.amount = 1;
          }
          else if(this.amount > 100)
          {
              this.amount = 100;
          }
      }
  },
  mounted() {
      this.$axios.get(this.$path.PRODUCT_BY_ID, {params: {"id": this.productId}})
      .then(response => {
        this.product = response.data
      });
      this.$axios.get(this.$path.BYPRODUCT_ID,{params:{"id": this.productId}})
      .then(response => {
        this.product.ingredients = response.data
        this.product.byproduct = response.data
      });
      this.$store.commit("updateCartFromLocalStorage");
  }    
};
</script>

<style scoped>
.image {
  height: 200px;
}
.ingrediënten {
  background-color: #e0dccc;
}
.numberSelected {
  width: 20%;
  max-height: 30px;
  text-align: center;
}
.bijgerechten {
  background-color: #cbe1d9;
}
.b-icon.bi {
  vertical-align: middle !important;
}
.form-control {
  display: inline !important;
  margin-bottom: 10px;
}
.place-order {
  background-color: #cbe1d9;
  padding-top: 30px;
}
.SearchandStore {
  padding: 10px;
}
</style>
