<template>
    <b-container id="results" ref="results" class="text-center" >
        <div v-on:change="CheckProducts(products, input)" v-for="product in filteredProducts "
        :key="product.name" >
        <p v-if="input" v-on:click="GoToProduct(product.id, product.categories[0].id)">{{product.name}}</p>
        </div>
        <p v-if="nothingFound">{{nothingFound}}</p>
    </b-container>
</template>

<script>
export default {
    props: ["filteredProducts", "input", "categories", "nothingFound"],
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