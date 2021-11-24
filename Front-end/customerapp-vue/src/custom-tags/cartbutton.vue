<template>
    <b-col :cols="col">
        <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
            <b-icon icon="cart4" variant="dark" />
        </b-button>
    </b-col>
</template>

<script>
    export default {
        props: ["col"],
        name: "cartbutton",
        methods: {
            GoToCart() {
                this.$router.push({name:'Winkelwagen'});
            }
        }
    };
</script>
