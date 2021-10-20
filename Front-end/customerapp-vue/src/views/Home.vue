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
            :tables="[
              { id: 'CODES', number: 1 },
              { id: 'CODES', number: 2 },
              { id: 'CODES', number: 3 },
              { id: 'CODES', number: 4 },
            ]"
          />
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import TableQrScanner from "../components/TableQrScanner";
import TableListPicker from "../components/TableListPicker";

export default {
  name: "Home",
  components: { TableListPicker, TableQrScanner },
};
</script>

<style scoped>
.card-bg {
  background: #e0dccc;
}
</style>
