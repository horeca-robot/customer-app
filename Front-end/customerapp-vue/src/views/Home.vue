<template>
  <div class="container">
    <div class="row mb-3 mt-3">
      <div class="col">
        <b-card class="card-bg">
          <img class="logo" :src="restaurantInfo.restaurantLogo"/>
          <br/>
          <h3>Welkom bij <b>{{restaurantInfo.name}}</b></h3>
          
          <br/>
        </b-card>
        <br/>
        <b-card class="card-bg">
          <TableQrScanner v-if="usingQR"/>
          <br v-if="usingQR"/>
          <b-button class="button-style w-100 border" @click="(usingQR = !usingQR)">{{this.switchbuttontext}}</b-button>
        </b-card>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <b-card v-if="!usingQR" class="card-bg">
          <table-list-picker
            :tables="tables"
          />
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import TableQrScanner from "../components/TableQrScanner";
import TableListPicker from "../components/TableListPicker";
import APIService from "../services/axios.service"

export default {
  name: "Home",
  components: { TableListPicker, TableQrScanner },
  data()
  {
    return {
      tables: [
      ],
      usingQR: true,
      restaurantInfo: {}
    };
  },
  computed:{
    switchbuttontext: function () {
      if(this.usingQR){
        return "Geen camera? klik hier"
      }
      else{
        return "Terug naar QR scannen"
      }
    }
  },
  mounted() {    
    this.$APIService.getRestaurantTables().then(
      (response) => {
        this.tables = response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  created(){
    APIService.getRestaurantInfo().then(response =>{
      this.restaurantInfo = response.data;
    })
  },
};
</script>

<style scoped>
.card-bg {
  background: var(--secondary-color-light);
  color: var(--text-color-secondary);
}
.logo{
  height: 150px;
  max-width: 100%;
  object-fit:contain;
}

</style>
