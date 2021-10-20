<template>
  <b-list-group>
    <b-list-group-item
      v-for="table in tables"
      :key="table.id"
      href="#"
      @click="redirectToTable(table.id)"
      >Tafel {{ table.number }}</b-list-group-item
    >
  </b-list-group>
</template>

<script>
import router from "../router";

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
      router.push({ name: "menu" });
    },
  },
};
</script>

<style scoped></style>
