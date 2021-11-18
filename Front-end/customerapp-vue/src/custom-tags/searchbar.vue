<template>
    <b-col cols="8">
        <input id="search-input" type="search" class="form-control" placeholder="Search"/>
        <b-button id="search-button" type="button" class="button-style button-style-search" v-on:click="Search">
            <b-icon icon="search"/>
        </b-button>
    </b-col>
</template>

<script>
    export default {
        name: "Header",
        methods: {
            Search() {
                //change this method
            }
        }
    };
</script>

<style scoped>
    .Searchbar {
        width: 115px;
    }
    .form-control {
        width: 70% !important;
        display: inline !important;
    }
    .search {
        position: absolute;
        margin-top: -7%;
        margin-left: 5%;
    }
    .button-style {
        width: 30%;
        padding: 0.375rem 0.75rem;
        height: 38px;
        display: inline;
        vertical-align: baseline !important;
    }
</style>