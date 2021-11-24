<template>
<div class="tablelist">
  <b-list-group>
    Kies een tafel:
    <b-list-group-item
      class="table-item"
      v-for="table in tables"
      :key="table.id"
      @click="ShowConfirmModal(table.tableNumber)"
      >Tafel {{ table.tableNumber }}
      </b-list-group-item>
  </b-list-group>

  <b-modal class="confirmtable" centered="true" hide-header hide-footer ref="confirmtable" id="confirmtable">
    <p>U heeft tafel {{this.selectedTableNumber}} geselecteerd</p>
    <b-button class="button-style" @click="redirectToTable(selectedTableNumber)">Bevestigen</b-button>
  </b-modal>
  </div>
</template>

<script>
export default {
  name: "TableListPicker",
  props: {
    tables: {
      type: Array,
      required: true,
    },
  },
  data(){
    return{
      selectedTableNumber: null,
    }
  },
  methods: {
    redirectToTable(tableId) {
      this.$store.dispatch("tableModule/setTable", tableId);
      this.$router.push({ name: "menu" });
    },
    ShowConfirmModal(tableId){
      this.selectedTableNumber = tableId;
      this.$refs['confirmtable'].show();
    }
  },
};
</script>

<style>
.tablelist{
  overflow: scroll;
  max-height: 60vh;
}
.table-item{
  background-color: #bdad89 !important;
}
#confirmtable .modal-dialog .modal-content{
    border: 0;
    border-radius: 0;
    background-color: #e0dccc;
}
</style>
