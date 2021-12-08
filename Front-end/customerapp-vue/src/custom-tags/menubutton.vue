<template>
    <b-col :cols="col">
        <b-button class="menuButton justify-content-end"  v-on:click="GoToMenu">
            <b-icon icon="book"></b-icon>
        </b-button>
    </b-col>
</template>

<script>
    export default {
        props: ["col"],
        name: "menubutton",
        methods: {
            GoToMenu() {
                this.$router.push({name:'Menu'});
            }
        }
    };
</script>