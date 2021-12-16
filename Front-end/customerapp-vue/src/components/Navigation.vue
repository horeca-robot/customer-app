<template>
  <b-modal id="modal1" ref="modal1">
    <template v-slot:modal-header>
      <h1></h1>
    </template>
    <template v-slot:modal-footer>
      <h1></h1>
    </template>
    <b-container class="text-center">
      <b-row>
        <b-col>
          <b-button class="button-style heading-2" v-on:click="GoToMenu"
            >Menu</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button class="button-style heading-2" v-on:click="GoToCart"
            >Winkelwagen</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button class="button-style heading-2" v-on:click="GoToYourOrders"
            >Jouw bestellingen</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card-img :src="restaurantInfo.restaurantLogo" class="image"></b-card-img>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button class="button-style heading-2" v-on:click="GoToHelp"
            >Help</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button class="button-style heading-2">Vertaal</b-button>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-button class="button-style heading-2" v-on:click="ChangeTable"
            >Verander tafel</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <p>U zit nu aan tafel ...</p>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <br/>
          <p>{{restaurantInfo.name}}
            <br/>
          Email: {{restaurantInfo.contactPersonEmail}}
          <br/>
          Telefoon: {{restaurantInfo.contactPersonPhone}}
          </p>
        </b-col>
      </b-row>
    </b-container>
  </b-modal>
</template>

<script>
import APIService from "../services/axios.service";

export default {
  data() {
    return {
      restaurantInfo: {},
    };
  },
  methods: {
    GoToMenu() {
      this.$router.push({ name: "Menu" });
      this.hide();
    },
    GoToCart() {
      this.$router.push({ name: "Cart" });
      this.hide();
    },
    GoToYourOrders() {
      this.$router.push({ name: "OrderHistory" });
      this.hide();
    },
    GoToHelp() {
      this.$root.$emit("help");
    },
    ChangeTable() {
      localStorage.removeItem('table');
      this.$router.push("/");
      this.hide();
    },
    show() {
      this.$refs.modal1.show();
    },
    hide() {
      this.$refs.modal1.hide();
    },
  },
  created(){
    APIService.getRestaurantInfo().then(response =>{
      this.restaurantInfo = response.data;
    })
  },
};
</script>

<style>
#modal1 {
  z-index: 10000;
}

#modal1 .modal-open {
  overflow: hidden;
}

#modal1 .modal-dialog {
  max-width: 100%;
  margin: 0;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100vh;
  display: flex;
  position: fixed;
  padding: 0;
}
#modal1 .modal-dialog .modal-content {
  border: 0;
  border-radius: 0;
  background-color: var(--primary-color-light);
  color: var(--text-color-primary-light);
}
</style>

<style scoped>
.button-style {
  height: max-content !important;
  font-size: 1.5rem !important;
  width: 70% !important;
}
.image {
  width: 70%;
  height: auto;
}
</style>
