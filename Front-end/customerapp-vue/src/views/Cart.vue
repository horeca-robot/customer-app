<template>
  <div>
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar :col="10" />
          <menu-button :col="2" />
        </b-row>
      </b-container>
    </div>
    <div class="cart">
      <CartItemCard
        v-for="product in orderedItems"
        :key="product.guid"
        :product="product.product"
        :amount="product.amount"
        :price="product.price"
      >
        <template v-slot:action-area="{ product }">
          <b-button
            class="button-style heading"
            v-on:click="addProductToCart(product)"
            >Toevoegen</b-button
          >
          <b-button
            class="button-style heading"
            v-on:click="removeProductFromCart(product)"
            >Verwijderen</b-button
          >
        </template>
      </CartItemCard>
      <p>Aantal items: {{ items.length }}</p>
      <h3>Totaal: € {{ cart_total.toFixed(2) }}</h3>
      <b-container v-if="items.length">
        <b-card class="note" v-if="note">Uw notitie is: {{note}}</b-card>
        <br/>
        <b-row>
          <b-col>
            <b-button v-if="!savedNote" v-b-modal.note class="button-style heading">Notitie toevoegen</b-button>
            <b-button v-if="savedNote" v-b-modal.note v-on:click="cancel" class="button-style heading">Notitie bekijken</b-button>
              <b-modal ref="note" id="note" hide-header hide-footer  centered v-on:hide="cancel">
                <b-form-textarea v-if="!isSaved"
                id="textarea"
                v-model="note"
                :state="note.length <= 350"
                class="noteText"
                placeholder="Voeg hier uw notitie toe..."
                rows="3"
                max-rows="6"
                ></b-form-textarea>
                <b-button class="button-style heading" v-if="!isSaved" v-on:click="cancel">Annuleren</b-button>
                <b-button class="button-style heading" v-if="!isSaved  && note" v-on:click="saveNote">Opslaan</b-button>
                <p v-if="isSaved" class="noteText" id="noteText">{{savedNote}}</p>
                <b-button class="button-style heading changeNote" v-if="isSaved" v-on:click="isSaved = false">
                <b-icon icon="pencil"/>
                </b-button>
                <b-button class="button-style heading" v-if="isSaved" v-on:click="deleteNote">Verwijderen</b-button>
                <b-button class="button-style heading" v-if="isSaved" v-on:click="$refs.note.hide">Sluiten</b-button>
              </b-modal>
          </b-col>
          <b-col>
            <b-button :disabled="ordering" @click="PlaceOrder" class="button-style heading"
              >Bestellen</b-button
            >
          </b-col>
        </b-row>
        <b-alert
          :show="dismissCountDown"
          fade
          :variant="chosenVariant"
          @dismiss-count-down="countDownChanged"
        >
          {{responseMessage}}
        </b-alert>
      </b-container>
    </div>
  </div>
</template>

<script>
import CartItemCard from "../components/OrderItem.vue";
import SearchBar from "../custom-tags/searchbar.vue";
import MenuButton from "../custom-tags/menubutton.vue";

export default {
  components: {
    CartItemCard,
    SearchBar,
    MenuButton,
  },
  data() {
    return {
      dismissSecs: 4,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      ordering: false,
      note: "",
      savedNote: localStorage.getItem('note'),
      isSaved: false,
      responseMessage: "",
      chosenVariant: "",
      orderedItems: [],
    };
  },
  computed: {
    items() {
      this.OrderingItems(this.$store.getters.cartItems);
      return this.$store.getters.cartItems;
    },
    cart_total() {
      return this.$store.getters.cartTotal;
    },
  },
  methods: {
    PlaceOrder() {
      this.ordering = true;
      if(localStorage.getItem('note') == null){
        localStorage.setItem('note', "");
      }
      const order = {
        products: this.items,
        tableId: JSON.parse(localStorage.getItem('table')).tableId,
        notes: localStorage.getItem('note'),
      };
      if (this.items.length > 0) {
        this.$APIService.placeOrder(order).then((response) => {
          if (response.data != null) {
            if(response.data.successful == false){
              this.ordering = false;
              this.responseMessage = response.data.message;
              this.chosenVariant = "danger";
              this.showAlert();
              setTimeout(this.dismissSecs * 1000)
            }
            else{
              this.$store.commit("removeCartFromLocalStorage");
              localStorage.removeItem('note');
              this.responseMessage = response.data.message;
              this.chosenVariant = "success";
              this.showAlert();
              setTimeout(() => {
                this.$router.go();
              }, this.dismissSecs * 1000);
            }
          }
        });
      }
    },
    addProductToCart(product) {
      this.$store.commit("addToCart", product);
    },
    removeProductFromCart(productToDelete) {
      var productsInCart = JSON.parse(localStorage.getItem('cart'));
      productsInCart = productsInCart.reverse();
      var deleted = false;
      productsInCart.forEach((product) =>{
        if (product.id == productToDelete.id && deleted == false){
          this.$store.commit("removeFromCart", product);
          deleted = true;
        }
      })
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs;
    },
    saveNote(){
      if(this.note.length <= 350){
        this.isSaved = true;
        this.savedNote = this.note;
        localStorage.setItem('note', this.savedNote);
      }
    },
    deleteNote(){
      this.savedNote = "";
      this.note = "";
      this.isSaved = false;
      this.$refs.note.hide();
      localStorage.removeItem('note');
    },
    cancel(){
      this.note = this.savedNote;
      if(this.savedNote){
        this.isSaved = true;
      }
      else{
        this.$refs.note.hide();
      }
    },
    OrderingItems(itemList){
      this.orderedItems = [];
      var number = 0;
      itemList.forEach((item1) =>{
        this.orderedItems.forEach((item2) =>{
          //aan if-statement toevoegen item2.product.byproducts == item1.byproducts als je op bijproducten wilt checken.
          if(item2.product.id == item1.id){
            item2.amount++;
            item2.price = item2.price + item1.price;
            number++;
          }
        });
        if(number == 0){
          this.orderedItems.push({amount: 1, product: item1, price: item1.price});
        } else if(this.orderedItems.length == 0){
          this.orderedItems.push({amount: 1, product: item1, price: item1.price});
        }
        number = 0;
      });
    }
  },
  mounted() {
    this.$store.commit("updateCartFromLocalStorage");
  },
};
</script>

<style lang="scss" scoped>
html {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light)
}
.cart {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light)
}
.container .row .col {
  margin-left: 3%;
}
.container {
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light);
  margin: 0;
  max-width: 100% !important;
}
.noteText{
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary);
  border-color: var(--text-color-secondary);
  border-style: solid;
  padding: 5px;
  overflow: scroll;
}
.note{
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary);
}
#noteText{
  max-height: 200px !important;
}
.changeNote{
  width: 50px !important;
}
</style>

<style>
#note .modal-dialog .modal-content{
  background-color: var(--primary-color-light);
  color: var(--text-color-primary);
}
</style>