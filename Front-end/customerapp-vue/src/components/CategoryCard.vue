<template>
  <div class="categoryCard" v-on:click="GoToCategory(category.id)">
      <b-img :src="category.image" class="image"></b-img>
      <h1 class="categoryName">{{category.name}}</h1>  
  </div>
</template>

<script>
export default {
  props: ["category"],
  name: 'CategoryCard',
  methods: {
    GoToCategory(category) {
      this.$router.push({ name: "Category", params: { id: category } });
    },
  },
};
</script>

<style>
.image {
  width: 100%;
  height: 125px;
  object-fit: cover;
}
.categoryName {
  font-family: Baskerville Old Face;
  font-size: 40px;
  font-weight: bold;
}
.categoryCard {
  margin-bottom: 20px;
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary);
}
</style>
