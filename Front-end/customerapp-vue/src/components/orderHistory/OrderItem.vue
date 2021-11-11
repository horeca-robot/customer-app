<template>
  <b-row class="border-bottom border-secondary border-large pt-3">
    <b-col class="horizontal-center-text">{{ order.name }}</b-col>
    <b-col class="horizontal-center-text"
      >{{ order.totalProducts }} Producten</b-col
    >
    <b-col>
      <b-row>
        <b-col class="horizontal-center-text">€{{ order.price }}</b-col>
        <b-col class="text-right">
          <span class="h3"
            ><b-icon-eye-fill @click="goToOrderDetail(order.id)" />
          </span>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "OrderItem",
  props: {
    order: Object,
  },
  methods: {
    goToOrderDetail(id) {
      this.$router.push({ name: "OrderHistoryDetails", params: { id } });
    },
  },
};
</script>

<style scoped>
.text-right {
  text-align: right;
}
.horizontal-center-text {
  margin: auto;
}
.border-large {
  border-bottom-width: 0.15em !important;
  border-bottom-color: black !important;
}
</style>
