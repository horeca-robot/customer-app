<template>
  <div>
    <b-card class="productCard" v-on:click="GoToProduct(product.id)">
      <h1 class="productName">{{product.name}}</h1>
      <div class="productImage">
        <b-card-img :src="product.image" class="Image"></b-card-img>
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
    GoToProduct(product){
        this.$router.push({name:'Product', params: {categoryId: this.$route.params.id, id: product}})
    }
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
