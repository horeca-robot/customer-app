<template>
  <b-container fluid class="card-background">
    <OrderHistoryItem v-for="order in orders" :key="order.id" :order="order" :number="orders.indexOf(order) + 1" />
  </b-container>
</template>

<script>
import OrderHistoryItem from "./OrderHistoryItem";
export default {
  name: "OrderHistoryCard",
  components: { OrderHistoryItem },
  props: {
    orders: Array,
  },
};
</script>

<style scoped>
.card-background {
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary);
}
</style>
