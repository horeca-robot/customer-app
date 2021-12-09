<template>
  <div class="categoryCard" v-on:click="GoToCategory()">
    <b-img :src="category.image" class="image"></b-img>
    <h1 class="categoryName">{{ category.name }}</h1>
  </div>
</template>

<script>
export default {
  props: ["category"],
  name: "CategoryCard",

  methods: {
    GoToCategory() {
      this.$APIService.getCategoryById(this.category.id).then((response) => {
        var category = response.data;
        if (category.products.length) {
          this.$router.push({
            name: "ChildCategory",
            params: { id: category.id },
          });
        } else {
          this.$router.push({
            name: "Category",
            params: { id: category.id },
          });
        }
      });
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
  color: var(--text-color-secondary-light);
}
</style>
