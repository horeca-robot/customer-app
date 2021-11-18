<template>
  <div class="container">
    <div class="row mb-3 mt-3">
      <div class="col">
        <b-card class="card-bg">
          <TableQrScanner />
        </b-card>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <b-card class="card-bg">
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
import AxiosService from '../services/axios.service';

export default {
  name: "Home",
  components: { TableListPicker, TableQrScanner },
  data()
  {
    return {
      tables: [],
    };
  },
  mounted() {    
    AxiosService.getRestaurantTables().then(
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
};
</script>

<style scoped>
.card-bg {
  background: #e0dccc;
}
</style>
