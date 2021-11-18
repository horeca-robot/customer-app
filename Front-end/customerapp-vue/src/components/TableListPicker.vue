<template>
  <b-list-group>
    <b-list-group-item
      class="table-item"
      v-for="table in tables"
      :key="table.id"
      href="#"
      @click="redirectToTable(table.id)"
      >Tafel {{ table.number }}</b-list-group-item
    >
  </b-list-group>
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
  methods: {
    redirectToTable(tableId) {
      this.$store.dispatch("tableModule/setTable", tableId);
      this.$router.push({ name: "menu" });
    },
  },
};
</script>

<style scoped>
.table-item {
  background: #bdad89;
}
</style>
