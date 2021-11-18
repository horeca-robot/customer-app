<template>
    <b-col :cols="col">
        <b-button class="menuButton justify-content-end"  v-on:click="GoToMenu">
            <img src="../assets/menuIcon.png" alt="Shoppingcart" width="25" height="20"/>
        </b-button>
    </b-col>
</template>

<script>
    export default {
        props: ["col"],
        name: "menubutton",
        methods: {
            GoToMenu() {
                this.$router.push("/menu");
            }
        }
    };
</script>