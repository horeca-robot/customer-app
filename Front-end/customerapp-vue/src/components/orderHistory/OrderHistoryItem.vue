<template>
  <div class="container OrderHistoryItem">
    <b-row
      class="border-bottom border-secondary border-large pt-3"
      @click="$root.$emit('bv::toggle::collapse', 'order-details' + number)"
    >
      <b-col>Bestelling #{{ number }}</b-col>
      <b-col>
        <b-row class="justify-content-end">
          <b-col class="text-right">
            <span class="align-top me-2">
               € {{ order.subTotal.toFixed(2) }}
               <b-icon v-if="order.paid" icon="check" variant="success"></b-icon>
              <b-icon v-if="!order.paid" icon="x" variant="danger"></b-icon>
               </span>
            <span class="h3"><b-icon icon="arrow-down-square-fill" font-scale="0.8"/> </span>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-collapse :id="'order-details' + number">
      <b-container class="order-details text-start">
        <b-row>
          <p>Geplaatst op: {{checkDateDigits(new Date(order.createdAt).getHours())}}:{{checkDateDigits(new Date(order.createdAt).getMinutes())}}</p>
          <p v-if="order.paid">Betaling: U heeft betaald</p>
          <p v-if="!order.paid">Betaling: Nog niet betaald</p>
          <p>{{CountDeliveredProducts(order)}} / {{ order.productOrders.length }} producten afgeleverd</p>
          <b-button @click="goToOrderDetail(order.id)" class="button-style">Bekijk producten</b-button>
        </b-row>
      </b-container>
    </b-collapse>
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
    CountDeliveredProducts(order){
      var count = 0;
      order.productOrders.forEach(prodorder => {
        if(prodorder.orderStatus === "DELIVERED"){
          count++
        }
      });
      return count;
    },
    checkDateDigits(date){
      if(date === 0){
        return "00";
      }
      if(date < 10){
        return "0" + date;
      }
      return date;
    }
  },
};
</script>

<style scoped>
.OrderHistoryItem {
  background-color: var(--secondary-color-light) !important;
}
.order-details {
  background-color: var(--secondary-color) !important;
  padding: 20px;
}
.text-right {
  text-align: right;
}
.horizontal-center-text {
  margin: auto;
}

.button-style {
  background-color: var(--secondary-color-light) !important;
  margin-bottom: 10px;
  max-width: 40vw;
  margin-left: 50vw;
}

.border-large {
  border-bottom-width: 0.15em !important;
  border-bottom-color: black !important;
}
</style>
