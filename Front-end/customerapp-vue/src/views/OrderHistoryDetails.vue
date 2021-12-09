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
        v-for="products in orderedItems"
        :key="products.id"
        :product="products.product"
        :amount="products.amount"
        :price="products.price"
      >
        <template v-slot:order-status>
          <b-icon
            v-if="products.orderStatus === 'DELIVERED'"
            icon="check"
            variant="success"
          ></b-icon>
          <b-icon
            v-if="products.orderStatus === 'OPEN_FOR_DELIVERY'"
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
      filtereditemList: [],
      orderedItems: [],
    };
  },
  methods: {
    CountDeliveredProducts() {
      var count = 0;
      console.log(this.order.productOrders)
      if (this.order.productOrders.length) {
        this.order.productOrders.forEach((productorder) => {
          if (productorder.orderStatus === "DELIVERED") {
            count++;
          }
        });
      }

      return count;
    },
    OrderingItems(itemList){
      itemList.forEach((item)=>{
        this.filtereditemList.push(item);
      })
      this.orderedItems = [];
      
      var number = 0;
      this.filtereditemList.forEach((item1) =>{
        this.orderedItems.forEach((item2) =>{
          //aan if-statement toevoegen item2.product.byproducts == item1.byproducts als je op bijproducten wilt checken.
          if(item2.product.id == item1.product.id){
            item2.amount++;
            item2.price = item2.price + item1.product.price;
            number++;
            if(item2.orderStatus == "DELIVERED" && item1.orderStatus == "OPEN_FOR_DELIVERY"){
              item2.orderStatus = item1.orderStatus;
            }
          }
        });
        if(number == 0){
          this.orderedItems.push({amount: 1, product: item1.product, price: item1.product.price, orderStatus: item1.orderStatus});
        } else if(this.orderedItems.length == 0){
          this.orderedItems.push({amount: 1, product: item1, price: item1.product.price, orderStatus: item1.orderStatus});
        }
        number = 0;
      });
    },
  },
  mounted() {
    this.$APIService
      .getOrderDetails({ params: { id: this.$route.params.id } })
      .then((response) => {
        this.order = response.data;
        this.OrderingItems(this.order.productOrders);
      });
  },
};
</script>

<style scoped>
.SearchandStore {
  padding: 10px;
}
</style>
