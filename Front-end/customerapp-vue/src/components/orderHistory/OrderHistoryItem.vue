<template>
  <b-row
    class="border-bottom border-secondary border-large pt-3"
    @click="goToOrderDetail(order.id)"
  >
    <b-col class="horizontal-center-text">Bestelling {{ new Date(order.createdAt).getHours() }} : {{ new Date(order.createdAt).getMinutes() }}</b-col>

    <b-col>
      <b-row class="justify-content-end">
        <b-col class="text-right">
          <span class="align-top me-2">€ {{ order.subTotal }}</span> <span class="h3"><b-icon-eye-fill /> </span>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "OrderHistoryItem",
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
