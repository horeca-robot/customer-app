<template>
  <div>
    <b-card>
      <b-row v-on:click="changeIcon" class="order-item" no-gutters>
        <b-col cols="7">
          <b-card-text class="heading">
            {{ product.name }} <slot name="order-status"></slot>
          </b-card-text>
        </b-col>
        <b-col cols="3">
          <b-card-text class="text price">
            1x &euro;{{ product.price.toFixed(2) }}
          </b-card-text>
        </b-col>
        <b-col cols="2">
          <b-icon style="color: var(--text-color-secondary)" :icon="sortIcon"></b-icon>
        </b-col>
      </b-row>
      <b-row class="order-item-info" v-show="hidden">
        <b-col cols="5">
          <img class="order-image" :src="product.image" />
        </b-col>
        <b-col cols="7" v-if="product.ingredients != null">
          <b-card-text class="heading bold"> Ingrediënten </b-card-text>
          <p
            v-for="ingredient in product.ingredients"
            :key="ingredient.id"
            style="display: inline"
          >
            {{ ingredient.name }},
          </p>
        </b-col>
        <b-col>
          <slot name="action-area" v-bind:product="product"></slot>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
export default {
  props:{
    product: Object,
  },
  data() {
    return {
      sortDirection: "asc",
      hidden: false,
    };
  },
  computed: {
    sortIcon() {
      return this.sortDirection === "asc"
        ? "arrow-down-square-fill"
        : "arrow-up-square-fill";
    },
  },
  methods: {
    changeIcon() {
      this.sortDirection = this.sortDirection === "asc" ? "desc" : "asc";
      this.hidden = !this.hidden;
    },
  },
  mounted(){
    this.$APIService.getIngredientsByProductId({ params: { id: this.product.id } }).then(response =>{
      this.product.ingredients = response.data;
    })
  }
};
</script>

<style lang="scss" scoped>
.card .row {
  --bs-gutter-x: 0;
}

.price {
  text-align: left !important;
}

.order-image {
  height: 100px;
  width: 100%;
  object-fit: cover;
}

.order-item {
  border-bottom: 1pt solid black;
}

.order-item-info {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light);
  border-bottom: 1pt solid black;
}

.card-body {
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary-light);
  padding: 3% 0 0 0;
}
</style>
