<template>
 <div>
  <b-row
    class="border-bottom border-secondary border-large pt-3"
    @click="goToOrderDetail(order.id)"
  >
    <b-col>Bestelling #{{number}}</b-col>
    

    <b-col>
      <b-row class="justify-content-end" >
        <b-col class="text-right">
          <span class="align-top me-2">€ {{ order.subTotal.toFixed(2) }}</span>  <span class="h3"><b-icon-eye-fill /> </span>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
 </div>
</template>

<script>
export default {
  name: "OrderHistoryItem",
  props: {
    order: Object,
    number: Number,
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
