<template>
    <b-col :cols="col">
        <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
            <b-icon style="color: var(--text-color-secondary)" icon="cart4" />
        </b-button>
    </b-col>
</template>

<script>
    export default {
        props: ["col"],
        name: "cartbutton",
        methods: {
            GoToCart() {
                this.$router.push({name:'Cart'});
            }
        }
    };
</script>
