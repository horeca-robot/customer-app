<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <back-button :col="4" />
          <menu-button :col="4" />
          <cart-button :col="4" />
        </b-row>
      </b-container>
    </div>
    <div>
      <CartItemCard
        v-for="productOrder in order.productOrders"
        :key="productOrder.id"
        :product="productOrder.product"
      >
        <template v-slot:order-status>
          <b-icon
            v-if="productOrder.orderStatus === 'DELIVERED'"
            icon="check"
            variant="success"
          ></b-icon>
          <b-icon
            v-if="productOrder.orderStatus === 'OPEN_FOR_DELIVERY'"
            icon="x"
            variant="danger"
          ></b-icon>
        </template>
      </CartItemCard>
    </div>
    <br />
    <h5>
      {{ CountDeliveredProducts() }} /
      {{ order.productOrders.length }} producten geleverd
    </h5>
    <h5>Totaal prijs: €{{ order.subTotal && order.subTotal.toFixed(2) }}</h5>
  </div>
</template>

<script>
import CartItemCard from "../components/OrderItem";
import MenuButton from "../custom-tags/menubutton";
import CartButton from "../custom-tags/cartbutton";
import BackButton from "../custom-tags/backbutton";

export default {
  name: "OrderHistory",
  components: { MenuButton, CartButton, CartItemCard, BackButton },
  data() {
    return {
      order: {
        id: "",
        subTotal: 0,
        paid: false,
        createdAt: "",
        table: {},
        productOrders: [],
      },
    };
  },
  methods: {
    CountDeliveredProducts() {
      var count = 0;
      if (this.order.productOrders.length) {
        this.order.productOrders.forEach((productorder) => {
          if (productorder.orderStatus === "DELIVERED") {
            count++;
          }
        });
      }

      return count;
    },
  },
  mounted() {
    this.$APIService
      .getOrderDetails({ params: { id: this.$route.params.id } })
      .then((response) => {
        this.order = response.data;
      });
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
