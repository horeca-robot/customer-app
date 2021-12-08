<template>
  <b-container class="card-background">
    <OrderItem v-for="order in orders" :key="order.id" :order="order" :number="orders.indexOf(order) + 1" />
  </b-container>
</template>

<script>
import OrderItem from "./OrderHistoryItem";
export default {
  name: "OrderHistoryCard",
  components: { OrderItem },
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
