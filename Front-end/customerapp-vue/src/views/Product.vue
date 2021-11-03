<template>
    <div>
        <div class="Header">
        <b-container>
            <b-row class="SearchandStore">
                <b-col cols="6">
                    <b-button class="backButton justify-content-end" v-on:click="GoToCategory">
                        <b-icon icon="reply" flip-h variant="dark"/>
                    </b-button>
                </b-col>
                <b-col cols="6">
                    <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
                        <b-icon icon="cart4" variant="dark"/>
                    </b-button>
                </b-col>
            </b-row>
        </b-container>
        </div>  
        <div>
            <b-img :src="product.image" class="image"></b-img>
            <h1 class="product-name">{{this.product.name}}</h1>           
            <div class="ingrediënten">
                
                <h1 v-if="product.ingredients.length != 0" class="heading"><b>Ingrediënten</b></h1>
                <p class="text" v-for="ingredient in product.ingredients" :key="ingredient.id" style="display: inline;">
                 {{ingredient.name}},
                </p><br/>
                <div>
                    <b-icon icon="dash" font-scale="3" v-on:click="RemoveFromAmount"></b-icon>
                    <b-input class="numberSelected" type="number" v-model.number="amount" placeholder="0" min="0">amount</b-input>
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
</template>

<script>
import Vue from "vue";
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
        amount: 0,
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
        },
        RemoveFromAmount(){
            if(this.amount != 1){
                this.amount--;
            }
        },
        addToCart() {
            for (let i = 0; i < this.amount; i++) {
                this.$store.commit("addToCart", this.product);
            }
            this.$bvModal.hide(this.product.id.toString());
            alert("Uw bestelling is toegevoegd.");
            this.$router.go();
        },
    },
    // get specific product.
    mounted() {
        Vue.axios.get("http://localhost:8080/api/v1/product/byid", {params: {"id": this.productId}}).then((response) => {
        this.product = response.data;
        });
        Vue.axios.get("http://localhost:8080/api/v1/ingredient/byproductid",{params:{"id": this.productId}}).then(response =>{
            this.product.ingredients = response.data;
            this.product.byproduct = response.data;
        })
        this.$store.commit("updateCartFromLocalStorage");
    },
}
</script>

<style scoped>

.ingrediënten{
    background-color: #E0DCCC;
}
.numberSelected{
    width: 20%;
    max-height: 30px;
    text-align: center;
}
.bijgerechten{
    background-color: #CBE1D9;
}
.b-icon.bi{
    vertical-align: middle !important;
}
.form-control{
    display: inline !important;
    margin-bottom: 10px;
}
.place-order{
    background-color: #CBE1D9;
    padding-top: 30px;
}
.SearchandStore{
    padding: 10px;
}
.image{
    height: 200px;
}

</style>