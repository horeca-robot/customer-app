<template>
  <!-- <div>
    <b-card v-b-modal="product.id.toString()" class="card" :img-src="product.img" img-top>
      <b-card-text> € {{ product.price.toFixed(2) }} </b-card-text>
    </b-card>
    <b-modal :id="product.id.toString()">
      <div style="margin-bottom: 20px" v-for="ingredient in product.ingredients" :key="ingredient.id">
        <li>{{ingredient.name}}</li>
      </div>
      <b-button pill variant="success" @click="addToCart()">Add</b-button>
    </b-modal>
  </div> -->
  <div>
    <b-card class="productCard">
      <h1 class="productName">{{product.name}}</h1>
      <div class="productImage">
        <b-card-img :src="product.img" class="Image"></b-card-img>
        <div class="productPrice">
          <p class="textPrice">€ {{product.price.toFixed(2)}}</p>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  props: ["product"],
  methods: {
    addToCart() {
      this.$store.commit("addToCart", this.product);
      this.$bvModal.hide(this.product.id.toString())	
    },
  },
};
</script>

<style>
.productName{
  font-family: Baskerville Old Face;
  font-size: 40px;
  font-weight: bold;
}
.productPrice{
  position: absolute;
  bottom: 0;
  right: 0;
  background-color: #BDAD89;
  height: max-content;
  padding-left: 2px;
  padding-right: 2px;
  
}
.productImage{
  position: relative;
}
.textPrice{
  font-family: Baskerville Old Face;
  font-size: 25px;
  margin:auto;
}
.productCard{
  background-color: #E0DCCC !important;
  margin-bottom: 20px;
  width: 100% !important;
  border-radius: 0px !;
}
.Image{
  height: 150px;
  object-fit: cover;
}
</style>
