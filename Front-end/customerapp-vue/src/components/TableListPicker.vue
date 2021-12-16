<template>
  <div class="tablelist">
    <b-list-group>
      Kies een tafel:
      <b-list-group-item
        class="table-item"
        v-for="table in tables"
        :key="table.id"
        @click="ShowConfirmModal(table)"
        >Tafel {{ table.tableNumber }}
      </b-list-group-item>
    </b-list-group>

    <b-modal
      class="confirmtable"
      centered
      hide-header
      hide-footer
      ref="confirmtable"
      id="confirmtable"
    >
      <p>U heeft tafel {{ selectedTable.tableNumber }} geselecteerd</p>
      <b-button class="button-style" @click="redirectToTable(selectedTable)"
        >Bevestigen</b-button
      >
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
  data() {
    return {
      selectedTable: {},
    };
  },
  methods: {
    redirectToTable(table) {
      this.$store.dispatch("tableModule/setTable", table.id);
      this.$router.push({ name: "Menu" });
    },
    ShowConfirmModal(table) {
      this.selectedTable = table;
      this.$refs.confirmtable.show();
    },
  },
};
</script>

<style>
.tablelist {
  overflow: scroll;
  max-height: 60vh;
}
.table-item {
  background-color: var(--secondary-color) !important;
  color: var(--text-color-secondary) !important;
}
#confirmtable .modal-dialog .modal-content {
  border: 0;
  border-radius: 0;
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary-light);
}
</style>
