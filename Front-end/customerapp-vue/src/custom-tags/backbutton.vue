<template>
    <b-col :cols="col">
        <b-button class="backButton justify-content-end" v-on:click="GoToCategory">
            <b-icon icon="reply" flip-h variant="dark"/>
        </b-button>
    </b-col>
</template>

<script>
    export default {
        props: ["col"],
        name: "backbutton",
        data() {
            return {
                categoryId: this.$route.params.categoryId,
            };
        },    
        methods: {
            GoToCategory(){
                this.$router.push({name:'Category', params: {id: this.categoryId}})
            }
        }
    };
</script>