<template>
    <b-container id="results" ref="results" class="text-center" >
        <div v-on:change="CheckProducts(products, input)" v-for="product in products "
        :key="product.name" >
        <p v-if="product.name.toLowerCase().includes(input.toLowerCase()) && input != empty" v-on:click="GoToProduct(product.id, product.categories[0].id)">{{product.name}}</p>
        </div>
        <p v-if="nothingFound != empty ">{{nothingFound}}</p>
    </b-container>
</template>

<script>
export default {
    props: ["products", "input", "categories", "nothingFound"],
    data() {
        return {
            empty: ""
        };
  },
  
  methods:{
    GoToProduct(product, category){
        document.documentElement.style.overflow = 'auto';
        this.$router.push({name: "Product", params: { categoryId: category, id: product },});
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