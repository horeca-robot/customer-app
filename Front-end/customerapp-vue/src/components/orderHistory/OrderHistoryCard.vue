<template>
  <b-container class="card-background">
    <OrderItem v-for="order in orders" :key="order.id" :order="order" />
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
  created() {
    console.log("AAA");
  },
};
</script>

<style scoped>
.card-background {
  background-color: #e0dccc;
}
</style>
