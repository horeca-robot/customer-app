<template>
    <b-container id="results" ref="results" class="text-center" >
        <div v-on:change="CheckProducts(products, input)" v-for="product in products "
        :key="product.name" >
        <p v-if="product.name.toLowerCase().includes(input.toLowerCase()) && input != empty" v-on:click="GoToProduct(product.id)">{{product.name}}</p>
        </div>
        <p v-if="nothingFound != empty ">{{nothingFound}}</p>
    </b-container>
</template>

<script>
export default {
    props: ["products", "input", "categories", "nothingFound"],
    data() {
        return {
            category: "",
            empty: ""
        };
  },
  
  methods:{
    GoToProduct(product){
        this.GetCategory(product)
        document.documentElement.style.overflow = 'auto';
        this.$router.push({name: "Product", params: { categoryId: this.category, id: product },});
    },
    GetCategory(productId){
        this.categories.forEach(category => {
            category.products.forEach(product =>{
                if(product.id == productId){
                this.category =  category.id;
                }
            })
        });
    },
  }
}
</script>

<style>
#results{
    max-height: 200px;
    overflow: auto;
    background-color:  #CBE1D9 !important;
    border-radius: 5px;
}
</style>