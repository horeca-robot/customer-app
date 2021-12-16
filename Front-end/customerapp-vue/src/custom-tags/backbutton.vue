<template>
    <b-col :cols="col">
        <b-button class="backButton justify-content-end" v-on:click="GoToCategory">
            <b-icon style="color: var(--text-color-secondary)" icon="reply" flip-h/>
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
              this.$router.back();
            }
        }
    };
</script>